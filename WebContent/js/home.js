function changeVerifyFP(url){
	 var imgSrc = $("#loginValidateImg");     
	 //var src = imgSrc.attr("src");     
	 imgSrc.attr("src",chgUrl(url));
}
function chgUrl(url){     
    var timestamp = (new Date()).valueOf();     
    urlurl = url.substring(0,17);     
    if((url.indexOf("&")>=0)){     
        urlurl = url + "×tamp=" + timestamp;     
    }else{     
        urlurl = url + "?timestamp=" + timestamp;     
    }
    //alert(urlurl);
    return urlurl;     
}
function login(){
	var loginCode = $("#loginCode").val();
	var loginPass = $("#password").val();
	var loginVerify = $("#verify").val();
	if(loginCode==""){
		alert("请输入登录账号");
		 $("#loginId").focus();
		return;
	}
	if(loginPass==""){
		alert("请输入登录密码");
		$("#loginWord").focus();
		return;
	}
	if(loginVerify==""||loginVerify=="输入验证码"){
		alert("请输入验证码");
		$("#verify").focus();
		return;
	}
	if(loginVerify.length!=4){
		alert("验证码不正确");
		$("#verify").focus();
		return;
	}
	document.getElementById("loginForm").submit();
}
function doreg(){
	var accountNumber = $("#accountNumber").val();
	
	var realName = $("#realName").val();
	
	var registType = $("#registType").val();
	
	var areaId = $("#areaId").val();
	
	var passwd = $("#passwd").val();
	
	var confirmPassword = $("#confirmPassword").val();
	
	var email = $("#email").val();
	
	var mobilePhone = $("#mobilePhone").val();
	
	var verify = $("#verify").val();
	
	if(accountNumber==""){
		alert("请输入登录账号");
		 $("#accountNumber").focus();
		return;
	}
	if(realName==""){
		alert("请输入真实姓名");
		 $("#realName").focus();
		return;
	}
	if(passwd==""){
		alert("请输入密码");
		 $("#passwd").focus();
		return;
	}
	if(passwd.length<4){
		alert("密码长度至少为4位");
		 $("#passwd").focus();
		return;
	}
	if(confirmPassword==""){
		alert("请输入确认密码");
		 $("#confirmPassword").focus();
		return;
	}
	if(passwd!=confirmPassword){
		alert("密码和确认密码不一致");
		 $("#confirmPassword").focus();
		return;
	}
	if(verify==""){
		alert("请输入验证码");
		 $("#verify").focus();
		return;
	}
	if(verify.length!=4){
		alert("验证码不正确");
		$("#verify").focus();
		return;
	}
	document.getElementById("registerForm").submit();
}
function refreshCity(obj,site,targetObjId){
	if(obj&&obj.value){
		if(obj.value!=""){
			
			document.getElementById(targetObjId).options.length = 0;
			$.ajax({
		         type: "post",
		         url: chgUrl(site+"/refeshCity.do"),
		         dataType: "html",
		         data: {
					areaId: obj.value
		         },
		         success: function(data, textStatus) {
		        	 //alert(data);
		        	if(data){
		        		var city = data.split(";");
		        		
		        		for(var i=0;i<city.length;i++){
		        			var _opt = city[i];
		        			var _opts = _opt.split(",");
		        			var option = new Option(_opts[1],_opts[0]);
			        		document.getElementById(targetObjId).options.add(option);
		        		}
		        		
		        	}
		         }
		     });
		}
	}
}
function refreshAreaId(obj){
	var tcity = document.getElementById("_areaId");
	var area = document.getElementById("parentAreaId");
	var city = document.getElementById("areaId");
	if(area&&obj){
		area.value=obj.value;
	}
	if(tcity&&city){
		city.value=tcity.value;
	}
}
function refreshCityId(obj){
	var tarea = document.getElementById("_parentAreaId");
	var area = document.getElementById("parentAreaId");
	var city = document.getElementById("areaId");
	if(area&&tarea){
		area.value=tarea.value;
	}
	if(obj&&city){
		city.value=obj.value;
	}
}