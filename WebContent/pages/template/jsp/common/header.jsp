<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.ey.dao.entity.UserBase,com.ey.consts.SystemConst"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<body>
<div id="header">
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
                	<a style="color:#a0db00" href="javascript:void(0)">欢迎你，<%=user.getRealName() %></a>
                	 <a rel="nofollow" style="color:#fc8936" href="<%=request.getContextPath() %>/logout.do">注销</a>
                	<%} %>
        </div>
        </div>
        <div class="logo">
            <h2><a href="<%=request.getContextPath() %>/main.do"><img width="450" height="51" src="<%=request.getContextPath() %>/images/common/logo.png" alt="E缴365"></a></h2>
        </div>
        <div class="userbar">

			<ul id="navv" >
			<li><span><a href="index.html">我的e缴</a></span>
				<ul>
					<li><a href="jiaofei_zhsz.html">缴费账号设置</a></li>
					<li><a href="jiaofei_jftx.html">账单代扣\提醒设置</a></li>
					<li><a href="jiaofei_jlcx.html">记录查询</a></li>
					<li><a href="jiaofei_tjfx.html">统计分析 </a></li>
			  </ul></li>
			
			  <li><span><a href="aqbz.html">安全保障</a></span></li>
		   <li><span><a href="yjfk.html">意见反馈</a></span></li>
		   <li><span><a href="gywm.html">关于我们</a></span></li>
	       </ul>
		</div>
		
    </div>
    <div class="b"></div>
</div>
</body></html>