<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script>
	          function postHandle(){
	        	  var id = document.getElementById("id");
	        	  var form = document.getElementById("forms");
	        	  form.action = "user/add.do";
	        	  if(id.value != "")
	        		  form.action = "user/update.do";
	        	  form.submit();
	        	  
	          }
	</script>
  </head>
  
  <body>
       <form id="forms" method="post">
             <p><input type="hidden" id="id" name="id" value="${user.id}"/></p>
             <p><input type="text" name="loginCode" value="${user.loginCode}"/></p>
             <p><input type="text" name="userName" value="${user.userName}"/></p>
             <p><input type="text" name="realName" value="${user.realName}"/></p>
             <p><input type="password" name="password"/></p>
             <p><input type="button" value="确定 " onclick="postHandle()" /></p>
     </form>
  </body>
</html>
