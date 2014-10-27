<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
	           function ajaxTest(id){
	        	var userNames = [];
	        	   $.ajax({
						url: "show.do",
						dataType: "json",
						async:false,
						data:{id:id},
					    success: function(data, textStatus) {
						     alert(data.password);
					     }
					});
				//alert(userNames.join(","));
	           }
	</script>
  </head>
  
  <body>
      <p><a href="user/show/add.do">新增</a>&nbsp;<a href="user/uploadform.do">上传文件</a></p>
      <c:forEach var="item" items="${key1}" varStatus="status">
            <p><a href="user/show/${item.id}.do">${item.realName}</a></p>
       </c:forEach>
  </body>
</html>
