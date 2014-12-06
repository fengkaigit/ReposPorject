<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>账号设置</title>
<style type="text/css">
body {
	margin: 0px;
	padding:0px;
	font-size:12px;
	border: 0px;
	overflow: hidden
}
</style>
<script language="javascript">

</script>
</head>
<body>
<iframe id="frame1" name="frame1" src="<%=request.getContextPath() %>/setting/edit.do?id=${param.id}&paymentType=${param.paymentType}" width="480px" height="550px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" ></iframe>
</body>
</html>