<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div style="height: 810px;" class="frameBox" id="frameBox">
	<iframe cyberarticle_ext_name="Frame_0" id="rightFrame" name="right" allowtransparency="true" marginheight="0" marginwidth="0" scrolling="no" style="overflow: hidden; z-index: -999; height: 798px;" src="<%=request.getContextPath() %>/sysman/iframe.do" frameborder="0" width="100%"></iframe>
</div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>