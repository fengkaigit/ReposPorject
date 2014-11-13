<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<title>e缴365</title>
<meta content="" name="description">
<link rel="stylesheet" type="text/css" href="css/global.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/dh.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script src="js/common/formValidator.js" language="Javascript"></script>
<script src="js/common/regularExpression.js" language="Javascript"></script>
<link href="css/jquery/transfer_new.css" type="text/css"
	rel="stylesheet">
<link href="css/jquery/validator.css" type="text/css" rel="stylesheet">
<link href="css/login/register.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/ui/ui.css">  
<link rel="stylesheet" type="text/css" href="css/login/login.css"> 
<link rel="stylesheet" type="text/css" href="css/zzsc.css">
 <link type="text/css" rel="stylesheet" href="css/validator.css"></link>
 
<script type="text/javascript" src="js/home.js"></script>
<script language="javascript">
var keywordtitle="输入验证码";
function SearchFocus(obj){
	if(obj.value==keywordtitle){
		obj.value="";
		
	}
}
</script> 
</head>
<body>
<%@include file="/pages/template/jsp/common/header.jsp"%>
<div>
<div class="jf_main">
<form method="post" action="dologin.do" id="loginForm">
<div class="zc_zone">
<div class="zc_title">
<div class="card_title1">
<ul>
	<li style="float: left;"><span>会员登录</span></li>

</ul>

</div>
</div>
<div class="fm-item1">
<table width="88%" border="0" align="center" cellspacing="0"
	cellpadding="0">
	<tbody>
		<tr>
			<td class="icon-user" width="42" height="42"></td>
			<td align="left"><input type="text"
				data-placeholder="请输入用户名" autocomplete="false" tabindex="1" title=""
				class="input in295  js-username" value="" name="loginCode"
				id="loginCode"></td>
		</tr>
		<tr>
			<td colspan="2" height="10"></td>
		</tr>
		<tr>
		<td class="icon-pass" width="42" height="42"></td>
			<td align="left"><input
				type="password" autocomplete="off" value="" accesskey="p"
				tabindex="2" title="密码" class="input in295  " maxlength="32"
				name="password" id="password"> <a href="#" class="thickbox"
				style="line-height: 15px;">忘记密码?</a>
			</td>
		</tr>
		<tr>
			<td colspan="2" height="10"></td>
		</tr>
		<tr>

			<td colspan="2"><input type="text" value="输入验证码" tabindex="3"
				class="code js-code fft-fl " maxlength="4" name="verify" id="verify"
				onfocus="SearchFocus(this)"> <img src="getVerify.do"
				style="width: 100px; height: 40px; "
				id="loginValidateImg"> <font class="orange">
			<span class="alllink"><a href="javascript:changeVerifyFP();">
			看不清，换一张 </a></span> </font></td>
		</tr>
		<tr>
			<td colspan="2" height="10"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="checkbox" name="remember" id="checkbox"
				value="1" class="inputys">&nbsp;记住用户名</td>
		</tr>
		<tr>
			<td colspan="2" height="10"></td>
		</tr>
		<tr>
			<td colspan="2"><span onClick="login()" class="fft-loginbtn">登&nbsp;录</span>&nbsp;&nbsp;<a
				target="_blank" href="reg.do" class="fft-fr cblue f12px fn">免费注册</a>
			</td>
		</tr>
		<tr>
            <td style="padding-left: 87px;" colspan="2">
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