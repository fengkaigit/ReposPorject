<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
$(document).ready(function(){
   $.formValidator.initConfig({formID:"modifyPassForm",debug:false,submitOnce:false,
		onError:function(msg,obj,errorlist){
			$("#errorlist").empty();
			alert(msg);
		},
		onSuccess:function(){
			return false;
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
   });
  $("#oldpasswd").formValidator({onShow:"请输入原密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码合法"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"原密码不合法,请确认"});
  $("#passwd").formValidator({onShow:"请输入新密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码合法"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"新密码不合法,请确认"});
  $("#confirmPassword").formValidator({onShow:"再次确认密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码一致"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不合法,请确认"}).compareValidator({desID:"passwd",operateor:"=",onError:"两次密码不一致,请确认"});
});
function modifypass(){
  if($.formValidator.pageIsValid()){
	  $('#modifyPassForm').ajaxSubmit(function(data){
		    alert(data.message);
	  }); 
  }
}
</script> 
</head>
<body>
<%@include file="/pages/template/jsp/common/agentheader.jsp"%>
<div>
<div class="jf_main">
<form method="post" action="<%=request.getContextPath() %>/agent/modpass.do" id="modifyPassForm">
<div class="zc_zone">
<div class="zc_title">
<div class="card_title1">
<ul>
	<li style="float: left;"><span>修改密码</span></li>

</ul>

</div>
</div>
<div class="fm-item1">
<table width="88%" border="0" align="center" cellspacing="0"
	cellpadding="0">
	<tbody>
		<tr>
		<td class="icon-pass" width="42" height="42"></td>
			<td align="left"><input
				type="password" autocomplete="off" value="" accesskey="p"
				tabindex="2" title="密码" class="input in295  " maxlength="32"
				name="oldpasswd" id="oldpasswd">
			</td>
			<td><div id="oldpasswdTip" style="width:250px"></div></td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>
		<tr>
		<td class="icon-pass" width="42" height="42"></td>
			<td align="left"><input
				type="password" autocomplete="off" value="" accesskey="p"
				tabindex="2" title="密码" class="input in295  " maxlength="32"
				name="passwd" id="passwd">
			</td>
			<td><div id="passwdTip" style="width:250px"></div></td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>
		<tr>
		<td class="icon-pass" width="42" height="42"></td>
			<td align="left"><input
				type="password" autocomplete="off" value="" accesskey="p"
				tabindex="2" title="密码" class="input in295  " maxlength="32"
				name="confirmPassword" id="confirmPassword">
			</td>
		   <td><div id="confirmPasswordTip" style="width:250px"></div></td>
		</tr>
		<tr>
			<td colspan="3" height="10"></td>
		</tr>
		<tr>
			<td colspan="3"><input style="border:0px" type="button" class="fft-loginbtn" value="确定" onclick="modifypass()"/>
			</td>
		</tr>
		<tr>
            <td style="padding-left: 87px;" colspan="3">
            <div class="onError">${message}</div>
           </td>
        </tr>
	</tbody>
</table>
</div>
</div>
</form>
</div>

</div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body>
</html>