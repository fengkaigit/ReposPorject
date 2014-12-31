<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.ey.dao.entity.UserBase,com.ey.consts.SystemConst"%>
<html>
<head>
<title>e缴365</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script language="javascript">
var keywordtitle="输入验证码";
function SearchFocus(obj){
	if(obj.value==keywordtitle){
		obj.value="";
		
	}
}
jQuery(document).ready(
		function() {
			<%
			UserBase _user = (UserBase)session.getAttribute(SystemConst.USER);
			if(_user!=null){ %>
				document.getElementById("loginmain").style.display="none";
			<%}else{%>
				var cookieLoginName = getCookie("365LoginName");
				var cookieLoginPwd = getCookie("365LoginPwd");
				if(cookieLoginName!=""){
	   				document.getElementById("loginCode").value=cookieLoginName;
				}
				if(cookieLoginPwd!=""){
	   				document.getElementById("password").value=cookieLoginPwd;
				}
			<%}%>
			
			
			//获取公告
			 jQuery.shfftAjaxHandler.ajaxRequest("<%=request.getContextPath() %>/announce/gglist.do",{group:1,page:1,rows:4},"get","json",function(data){
				   if(data.length>0){
					   var ggHtml = [];
					   var gglist = document.getElementById("ggul");
					   for(var i=0;i<data.length;i++){
						   var obj = data[i];
						   ggHtml.push('<li class="clearfix"><b></b>');
						   ggHtml.push('<a href="javascript:getgg('+obj.id+');">');
						   ggHtml.push('<span title="'+obj.title+'" class="fcg2">'+obj.title+'...</span><span class="fcg fr">['+obj.createTime +']</span></a>');
						   ggHtml.push('</li>');
						 }
					   gglist.innerHTML = ggHtml.join("");
				   }
			 });
			
		});
function getgg(id){
	window.location.href = "<%=request.getContextPath() %>/announce/showgg.do?id="+id;
}
function more(){
	window.location.href = "<%=request.getContextPath() %>/announce/more.do?group=1";
}

</script> 
</head>
<body>
<%@include file="/pages/template/jsp/common/header.jsp"%>
<div class="feat-area">
	  <div style="position:relative; height:300px;">
	<div class="banner" style="position:relative; z-index:1">
	</div>
	<div class="1bannerWrap">
		<ul class="showList">
			<li class="hand special" img="images/banner1.png"></li>
			<li class="hand" img="images/banner2.png"></li>
		</ul>
	</div>
	<p class="banner2" style="height:300px; position:absolute; z-index:0; top:0px; left:auto; width:100%">
	</p>
</div>
<div id="loginmain">
    <div class="fft-login">
		<!-- 	 <div class="fft-login-fixed"></div> -->
			
				<form id="loginForm" method="post" action="<%=request.getContextPath() %>/dologin.do" class="clearfix">
				        <input type="hidden" id="loginSercuWord" name="loginSercuWord">	                    	
						<input type="hidden" name="MFM">
						<input type="hidden" value="login" name="reqfrom">
					<h2><a href="<%=request.getContextPath() %>/reg.do" class="fft-fr cblue f12px fn">免费注册&nbsp;&nbsp;</a>&nbsp;&nbsp;&nbsp;欢迎登录</h2>
					<p class="error"></p>
					<p class="js-tips">
						<i class="ico-username"></i>
						<input type="text" data-placeholder="请输入用户名" autocomplete="false" tabindex="1" title="" class="input in295  js-username" value="" name="loginCode" id="loginCode">
					</p>
					<p class="js-tips">
						<i class="ico-password"></i>
						<input type="password" autocomplete="off" value="" accesskey="p" tabindex="2" title="密码" class="input in295  "  maxlength="32" name="password" id="password">
					</p>
					
					
                    <!-- 非单点登录验证码 -->                     
                    <div id="NoSSOVerify" style="display:block">
					<p class="js-tips">
						<input type="text" data-placeholder="输入验证码" autocomplete="off" value="输入验证码" tabindex="3"  class="code js-code fft-fl " maxlength="4" name="verify" id="verify" onfocus="SearchFocus(this)">
                        	<img src="<%=request.getContextPath() %>/getVerify.do" style="width:100px;height:40px;float:right;" id="loginValidateImg" title="看不清？点击图片刷新验证码" onClick="changeVerifyFP('<%=request.getContextPath() %>/getVerify.do')"></p>
					</div>
					<div style="width:204px;height:20px;float:left;text-align:left;margin-top: 0px;">
                            
                            
                            	<span style="float:left;margin-right:5px; height:15px;">
                            		<input type="checkbox" name="remember" id="checkbox" value="1" class="inputys">
                            	</span>
                            
                            <label style="float:left; color:#333333; height:15px; line-height:15px; display:block;" for="checkbox" class="rem_name_tip">记住用户名</label>
                            <a href="#" class="thickbox" style=" float:right;margin-right:20px; line-height:15px;">忘记密码?</a>                    </div>
					
					<div>
					    <label style="_width:85px;"></label>
					    	<div id="NoSercuInput">
								<span onClick="login()" class="fft-loginbtn">登&nbsp;录</span>							</div>
							<!-- 密码控件登录 -->
                           
					</div>
				</form>
			</div>
            </div>
</div>
    

<div class="yol-entry">
    <div class="yol-top">
        <div class="sidebar" style="position: static; right: 0px; top: 0px;">
            <div class="bulletin">
                   <div class="title-items">
                    <h2>公告栏</h2>
                    <a class="more" href="javascript:more()">更多&gt;</a></div>
                <ul id="ggul">  
          
                    
                        
                </ul>
               
            </div>
            
            
             <div class="bulletin h198">
                <div class="title-items">
                    <h2>手机缴费更便捷</h2>
                 </div>
                 <div class="yyxz clearfix">
                 	<div class="icon iphone"></div>
                    <div class="icon android"></div>
                 </div>
               
            </div>
            
            
             
        </div>
        <div class="main">
            <!--main nav -->
            
             <div class="wealth ">
               
         		<div class="u-main">
    <div class="headtitle"><em><i class="icons icon-novice"></i></em> 缴费专区</div>
    <ul class="clearfix">
      <li>
        <a href="<%=request.getContextPath() %>/sf/first.do">
          <div style="color:#f85f20" class="title">水费</div>
          <div style="background-position:-62px 0px" class="pic"></div>
          <div class="note">7*24小时服务</div>
        </a>
      </li>
      <li>
        <a href="<%=request.getContextPath() %>/df/first.do">
          <div style="color:#ac73c1" class="title">电费</div>
          <div style="background-position:0px 0px" class="pic"></div>
          <div class="note">无需排队</div>
        </a>
      </li>
      <li>
        <a href="<%=request.getContextPath() %>/rqf/first.do">
          <div style="color:#3aabda" class="title">燃气费</div>
          <div style="background-position:-124px 0px" class="pic"></div>
          <div class="note">足不出户</div>
        </a>
      </li>
      <li>
        <a href="<%=request.getContextPath() %>/ghf/first.do">
          <div style="color:#88b838" class="title">固话费</div>
          <div style="background-position:-186px 0px" class="pic"></div>
          <div class="note">动动拇指</div>
        </a>
      </li>
    </ul>
    <ul class="clearfix">
      <li>
        <a href="<%=request.getContextPath() %>/ydtx/first.do">
          <div style="color:#55c6c6" class="title">移动通讯</div>
          <div style="background-position:-248px 0px" class="pic"></div>
          <div class="note">一站式缴费</div>
        </a>
      </li>
      <li>
        <a href="<%=request.getContextPath() %>/jtfk/first.do">
          <div style="color:#f94c03" class="title">交通罚款</div>
          <div style="background-position:-62px  -62px" class="pic"></div>
          <div class="note">第一时间 推送提醒</div>
        </a>
      </li>

      <li>
        <a href="<%=request.getContextPath() %>/wyf/first.do">
          <div class="title" style="color:#5d83ce">物业费</div>
          <div class="pic" style="background-position:0px -62px"></div>
          <div class="note">无障碍沟通</div>
        </a>
      </li>
   
         <li>
        <a href="<%=request.getContextPath() %>/yxds/first.do">
          <div class="title" style="color:#5d83ce">有线电视</div>
          <div class="pic" style="background-position:-124px -62px"></div>
          <div class="note">轻松缴费</div>
        </a>
      </li>
   
    </ul>
     
       </div>         
     </div>
    </div>
    </div>
    </div>
   <%@include file="/pages/template/jsp/common/about.jsp"%>
   <%@include file="/pages/template/jsp/common/footer.jsp"%>
   <%@include file="/pages/template/jsp/common/links.jsp"%>
<script>
var w=$("body").width();
if(w>1000){
	$("#loginmain .fft-login").css("right",(w-1000+20)/2);
}
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/zzsc.js"></script> 
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>