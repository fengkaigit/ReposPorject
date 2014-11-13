$(document).ready(function(){
	$.formValidator.initConfig({formID:"registerForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			$("#errorlist").empty();
			
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});

	$("#accountNumber").formValidator({onShow:"请输入登录名",onFocus:"登录名至少2个字符,最多20个字符",onCorrect:"该登录名可以注册"}).inputValidator({min:2,max:20,onError:"登录名非法,请确认"})
	    .ajaxValidator({
	    type:"post",
		dataType : "html",
		async : true,
		data: {
	    	accountNumber: $("#accountNumber").val()
         },
		url : "checkreg.do",
		success : function(data){
            if( data.indexOf("此登录名可以注册!") >= 0 ) return true;
            if( data.indexOf("此登录名已存在,请填写其它用户名!") >= 0 ) return false;
			return false;
		},
		buttons: $("#btnreg"),
		error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
		onError : "该登录名不可用，请更换登录名",
		onWait : "正在对登录名进行合法性校验，请稍候..."
	});
	$("#realName").formValidator({onShow:"请输入真实姓名",onFocus:"用户名至少2个字符,最多20个字符",onCorrect:"输入合法"}).inputValidator({min:2,max:20,onError:"真实姓名非法,请确认"});
	$("#passwd").formValidator({onShow:"请输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码合法"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不合法,请确认"});
	$("#confirmPassword").formValidator({onShow:"输再次输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码一致"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码密码不合法,请确认"}).compareValidator({desID:"passwd",operateor:"=",onError:"两次密码不一致,请确认"});
	$("#verify").formValidator({onShow:"请输入验证码",onFocus:"验证码4个字符",onCorrect:"输入合法"}).inputValidator({min:4,max:4,onError:"验证码非法,请确认"});
});