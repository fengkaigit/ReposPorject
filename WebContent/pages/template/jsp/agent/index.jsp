<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html>
<!--<![endif]-->
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
</head>
<body>
<%@include file="/pages/template/jsp/common/agentheader.jsp"%>
 <div style="height: 810px;" class="frameBox" id="frameBox">
	<iframe cyberarticle_ext_name="Frame_0" id="rightFrame" name="right" allowtransparency="true" marginheight="0" marginwidth="0" scrolling="no" style="overflow: hidden; z-index: -999; height: 798px;" src="<%=request.getContextPath() %>/agent/iframe.do" frameborder="0" width="100%"></iframe>
</div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>