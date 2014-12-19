<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.ey.dao.entity.UserBase,com.ey.consts.SystemConst"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
var url="";
    $(document).ready(function() {
      $('ul.sf-menu').sooperfish();
    });
    function chgPass(){
    	//show('passwordwin');
    	showDivWin('passwordwin',380,260,'');
    }
    function chgMobile(){
    	//show('chgmobilewin');
    	//document.getElementById("newmobile").value="";
    	showDivWin('chgmobilewin',380,260,'');
    }
   
</script>
</head>
<body>
<div id="header" class="bg17">
    <div class="inner">
        <ul class="service">
            <li class="hotline"><i class="icons"></i><b>客服热线</b><em>400 888 888</em></li>
             <li >服务时间7×24小时</li>
              <li class="concerns"><b>关注我们</b><a rel="nofollow" class="icons weibo" target="_blank" href="http://e.weibo.com/yoolicom">新浪官方微博</a><a class="icons weixin" target="_blank" href="javascript:;">官方微信</a></li>
            
        </ul>
      
        <div class="loginbar">
            
                
                     <div class="for">
                    
                     <%
                     UserBase user = (UserBase)session.getAttribute(SystemConst.USER);
                     if(user==null){ %>
                     <a rel="nofollow" style="color:#fc8936" href="<%=request.getContextPath() %>/reg.do">免费注册</a>
                     <a style="color:#a0db00" href="<%=request.getContextPath() %>/login.do">立即登录</a>
                	<%}else{ %>
                	<a style="color:#a0db00" href="javascript:chgMobile()" title="点击重新绑定手机号">欢迎你，<%=user.getRealName() %></a>
                	<a style="color:#a0db00" href="javascript:chgPass()">修改密码</a>
                	 <a rel="nofollow" style="color:#fc8936" href="<%=request.getContextPath() %>/logout.do">注销</a>
                	<%} %>
                     
                     </div>
                
                
        
        </div>
        <div class="logo">
            <h2><a href="<%=request.getContextPath() %>/main.do"><img width="450" height="51" src="<%=request.getContextPath() %>/images/common/logo.png" alt="E缴365"></a></h2>
        </div>
        <div class="userbar">

			<ul class="sf-menu" id="nav" >
			<li class="current"><a href="<%=request.getContextPath() %>/main.do">我的e缴</a>				<ul>
					<li class="current"><a href="<%=request.getContextPath() %>/setting/list.do">缴费账号设置</a></li>
					<!--  <li><a href="jiaofei_jftx.html">账单代扣\提醒设置</a></li>-->
					<li><a href="<%=request.getContextPath() %>/jf/query.do">记录查询</a></li>
			  </ul></li>
			
			  <li><span><a href="<%=request.getContextPath() %>/ej/security.do">安全保障</a></span></li>
			   <li><span><a href="<%=request.getContextPath() %>/ej/feedback.do">意见反馈</a></span></li>
		   <li><span><a href="<%=request.getContextPath() %>/ej/ieda.do">评论区</a></span></li>
		   <li><span><a href="<%=request.getContextPath() %>/ej/about.do">关于我们</a></span></li>
	       </ul>
		</div>
		
    </div>
    <div class="b"></div>
</div>



<div id="passwordwin" class="divWin">
<div class="close cur" onClick="hiddenDiv('passwordwin')">关闭</div>
    <h1 id="title"></h1>
	<div class="jfzh-con" style="width:460px;height:350px;padding:10px;border:0px;">
		<div class="jfzh-top clearfix">
		<form id="chgpassform" onSubmit="return false;"> 
		<table id="login-table" style=" border:0px;width:300px;" cellpadding="0" cellspacing="0">  
        <tr> 
<td width="60" style=" border:1px;text-align:right;">原密码：</td> 
<td style=" border:0px;"> <input type="password" style="width:160px;" class="on-show" id="oldpass" name="oldpass" maxlength="32"/><span class="fcr">&nbsp;&nbsp;*</span>
</td> 
</tr>
<tr> 
<td width="60" style=" border:1px; text-align:right;">新密码：</td> 
<td style=" border:0px;">
<input type="password"  class="on-show"  style="width:160px;" id="newepass1" name="newepass1"  maxlength="32"/><span class="fcr">&nbsp;&nbsp;*</span>
</td> 
</tr> 
<tr> 
<td width="60" style=" border:1px;text-align:right;" >确认密码：
</td> 
<td style=" border:0px; ">
<input type="password" style="width:160px;" class="on-show" id="newepass2" name="newepass2" maxlength="32"/>
<span class="fcr">&nbsp;&nbsp;*</span></td> 

</tr>  
<tr> 
<td width="300" style=" border:0px;" colspan="2" class="save">
<input  type="button" class="jfxx_btn3"  onClick="resetPassword('<%=request.getContextPath() %>')" value="确认">
</td> 
</tr> 


</table> 
		</form> 

        </div>
    
   
    </div>
	 
    
</div>
<div id="chgmobilewin" class="divWin">
<div class="close cur" onClick="hiddenDiv('chgmobilewin')">关闭</div>
    <h1 id="title"></h1>
	<div class="jfzh-con" style="width:460px;height:350px;padding:10px;border:0px;">
		<div class="jfzh-top clearfix">
		<form id="chgmobileform" onSubmit="return false;"> 
		<table id="login-table" style=" border:0px;width:300px;" cellpadding="0" cellspacing="0">  
        <tr> 
<td width="60" style=" border:1px;text-align:right;">手机号：</td> 
<td style=" border:0px;"> <input type="text" style="width:160px;" class="on-show" id="newmobile" name="newmobile" maxlength="11"/><span class="fcr">&nbsp;&nbsp;*</span>
</td> 
</tr>
<tr> 
<td width="300" style=" border:0px;" colspan="2" class="save">
<input  type="button" class="jfxx_btn3"  onClick="resetMobile('<%=request.getContextPath() %>');" value="确认">
</td> 
</tr> 


</table> 
		</form> 

        </div>
    
   
    </div>
	 
    
</div>
<div id="bg"></div>
</body></html>