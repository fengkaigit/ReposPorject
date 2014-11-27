<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.ey.dao.entity.SystemManager,com.ey.consts.SystemConst"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
$(document).ready(function() {
    $('ul.sf-menu').sooperfish();
});
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
                     SystemManager sysMan = (SystemManager)session.getAttribute(SystemConst.USER);
                     if(sysMan==null){ %>
                     <a rel="nofollow" style="color:#fc8936" href="<%=request.getContextPath() %>/reg.do">免费注册</a>
                     <a style="color:#a0db00" href="<%=request.getContextPath() %>/sysman/login.do">立即登录</a>
                	<%}else{ %>
                	<a style="color:#a0db00" href="<%=request.getContextPath() %>/sysman/edit/<%=sysMan.getId() %>.do">欢迎你，<%=sysMan.getManagerRealname() %></a>
                	 <a rel="nofollow" style="color:#fc8936" href="<%=request.getContextPath() %>/sysman/logout.do">注销</a>
                	<%} %>
        </div>
        </div>
        <div class="logo">
            <h2><a href="main.do"><img width="450" height="51" src="<%=request.getContextPath() %>/images/common/logo.png" alt="E缴365"></a></h2>
        </div>
        <div class="userbar">

			<ul class="sf-menu" id="nav" >
			<li class="current"><span><a href="<%=request.getContextPath() %>/agent/list.do">信息维护</a></span>
				<ul>
					<li class="current"><a href="<%=request.getContextPath() %>/sysman/list.do">管理员维护</a></li>
					<li><a href="<%=request.getContextPath() %>/agent/list.do">代理商维护</a></li>
					<li><a href="<%=request.getContextPath() %>/charge/list.do">收费单位维护</a></li>
			  </ul></li>
			  <li ><span><a href="<%=request.getContextPath() %>/announce/list.do">系统公告</a></span>
				<ul>
					<li ><a href="<%=request.getContextPath() %>/announce/add.do">公告发布</a></li>
					<li><a href="<%=request.getContextPath() %>/announce/list.do">公告管理</a></li>
			  </ul></li>
			<li class="current"><span><a href="<%=request.getContextPath() %>/bill/servicelist.do">单据查询</a></span>
				<ul>
					<li class="current"><a href="<%=request.getContextPath() %>/bill/servicelist.do">劳务费划款单查询</a></li>
					<li class="current"><a href="<%=request.getContextPath() %>/bill/poundagelist.do">手续费划款单查询</a></li>
					<li class="current"><a href="<%=request.getContextPath() %>/bill/profitlist.do">收益划款单查询</a></li>
					<li class="current"><a href="<%=request.getContextPath() %>/bill/settlelist.do">结算划款单查询</a></li>
					<li class="current"><a href="<%=request.getContextPath() %>/bill/incomelist.do">最终盈利划款单查询</a></li>
			  </ul></li>
			  <li><span><a href="<%=request.getContextPath() %>/sysman/passwd.do">修改密码</a></span></li>
	       </ul>
		</div>
		
    </div>
    <div class="b"></div>
</div>
</body></html>