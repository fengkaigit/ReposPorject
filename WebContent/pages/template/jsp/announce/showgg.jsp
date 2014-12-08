<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<style>
html {
	OVERFLOW-Y: scroll; BACKGROUND-COLOR: #ffffff
}
body {
	LINE-HEIGHT: 1.5; MIN-HEIGHT: 500px; FONT-FAMILY: Arial; BACKGROUND: #ffffff; COLOR: #333; FONT-SIZE: 12px
}
.read_h2 {
	PADDING-BOTTOM: 0px; LINE-HEIGHT: 24px; MARGIN-TOP: 15px; PADDING-LEFT: 15px; PADDING-RIGHT: 15px; FONT-FAMILY: simhei; BACKGROUND: url(images/alipay/read/tph1.png) #53ba52 no-repeat 0px 0px; FLOAT: left; HEIGHT: 24px; COLOR: #fff; FONT-SIZE: 20px; FONT-WEIGHT: 100; PADDING-TOP: 0px
}
.readContent {
	WIDTH: 100%; FLOAT: left; OVERFLOW: hidden
}
.readContent A {
	COLOR: #014c90
}
.tpc_content {
	PADDING-BOTTOM: 20px; LINE-HEIGHT: 1.8em; MARGIN: 0px; PADDING-LEFT: 2px; PADDING-RIGHT: 2px; FONT-FAMILY: Arial; PADDING-TOP: 0px
}
.f14 {
	FONT-SIZE: 14px
}
.f12 {
	FONT-SIZE: 12px
}
.mb10 {
	MARGIN-BOTTOM: 10px
}
.operTips {
	LINE-HEIGHT: 2; BACKGROUND: #fffeed
}
.s6 {
	COLOR: #666
}
.tac {
	TEXT-ALIGN: center
}
.c {
	WIDTH: 0px; FONT: 0px/0px Arial; HEIGHT: 0px; CLEAR: both; OVERFLOW: hidden
}
</style>
</head>
<body>
    <c:if test="${announce.announcementGroup==0}"><%@include file="/pages/template/jsp/common/sysheader.jsp"%></c:if>
	<c:if test="${announce.announcementGroup==1}"><%@include file="/pages/template/jsp/common/header.jsp"%></c:if>
	<div style="MARGIN-BOTTOM: 10px; HEIGHT: auto" id=subject_tpc
		class=read_h2>${announce.title}</div>
	<div class=readContent>
		<!--content_read-->
		<div class=tpc_content>
			<div id=mag_tpc class="operTips tac f12 mb10 s6">— 本帖被 社区发言人
				执行加亮操作(<fmt:formatDate value="${announce.createTime}" pattern="yyyy-MM-dd"/>) —</div>
			<div id=p_tpc class=c></div>
			<div style="WIDTH: 100%; WORD-WRAP: break-word; OVERFLOW: hidden; WORD-BREAK: break-all" id="read_tpc" class="f14 mb10"><c:out value="${announce.content}" escapeXml="false"/></div>
		</div>
		<!--content_read-->
	</div>
	 <div class="clear"></div>
	<%@include file="/pages/template/jsp/common/footer.jsp"%>
	<%@include file="/pages/template/jsp/common/links.jsp"%>
	<script src="js/funs.js" type="text/javascript"></script>
</body>
</html>