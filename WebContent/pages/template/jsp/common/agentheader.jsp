<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.ey.bo.AgentBo,com.ey.consts.SystemConst"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
$(document).ready(function() {
    $('ul.sf-menu').sooperfish();
});
function createBatch(){
	jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/mcbatch.do",null,"get","json",function(data){
	    if(confirm("批次单已生成，是否进行办理?")){
	       window.location.href = "<%=request.getContextPath() %>/agent/worklist.do?status=0";
	    }
	    return false;
    });
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
                     AgentBo agent = (AgentBo)session.getAttribute(SystemConst.AGENT);
                     if(agent==null){ %>
                     <a rel="nofollow" style="color:#fc8936" href="<%=request.getContextPath() %>/reg.do">免费注册</a>
                     <a style="color:#a0db00" href="<%=request.getContextPath() %>/agent/login.do">立即登录</a>
                	<%}else{ %>
                	<a style="color:#a0db00" href="<%=request.getContextPath() %>/agent/edit/<%=agent.getId() %>.do">欢迎你，<%=agent.getRegistRealName() %></a>
                	 <a rel="nofollow" style="color:#fc8936" href="<%=request.getContextPath() %>/agent/logout.do">注销</a>
                	<%} %>
        </div>
        </div>
        <div class="logo">
            <h2><a href="main.do"><img width="450" height="51" src="<%=request.getContextPath() %>/images/common/logo.png" alt="E缴365"></a></h2>
        </div>
        <div class="userbar">

			<ul class="sf-menu" id="nav" >
			<li class="current"><span><a href="<%=request.getContextPath() %>/agent/index.do">首页</a></span>
				</li>
			  <li><span><a href="###">我的工作</a></span>
			  <ul>
					<li class="current"><a href="<%=request.getContextPath() %>/agent/worklist.do?status=0">待办任务</a></li>
					<li class="current"><a href="<%=request.getContextPath() %>/agent/worklist.do?status=1">已办任务</a></li>
					<li class="current"><a href="<%=request.getContextPath() %>/agent/statuslist.do">修改办理状态</a></li>
					<li class="current"><a href="javascript:createBatch()">生成批次单</a></li>
					
			  </ul></li>
			  <li><span><a href="###">个人设置</a></span>
			  <ul>
					<li class="current"><a href="<%=request.getContextPath() %>/agent/passwd.do">修改密码</a></li>
					
					
			  </ul></li>
	       </ul>
		</div>
		
    </div>
    <div class="b"></div>
</div>
</body></html>