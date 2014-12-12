<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/aboutUs.css">
<style>
* {
	font-family: '微软雅黑', 'weiruanyahei';
}
html {
	OVERFLOW-Y: scroll;
	BACKGROUND-COLOR: #ffffff
}

body {
	LINE-HEIGHT: 1.5;
	MIN-HEIGHT: 500px;
	FONT-FAMILY: Arial;
	BACKGROUND: #ffffff;
	COLOR: #333;
	FONT-SIZE: 12px
}

.read_h2 {
	PADDING-BOTTOM: 0px;
	LINE-HEIGHT: 24px;
	MARGIN-TOP: 15px;
	PADDING-LEFT: 15px;
	PADDING-RIGHT: 15px;
	FONT-FAMILY: simhei;
	BACKGROUND: url(images/alipay/read/tph1.png) #53ba52 no-repeat 0px 0px;
	FLOAT: left;
	HEIGHT: 24px;
	COLOR: #fff;
	FONT-SIZE: 20px;
	FONT-WEIGHT: 100;
	PADDING-TOP: 0px
}

.readContent {
	WIDTH: 100%;
	FLOAT: left;
	OVERFLOW: hidden
}

.readContent A {
	COLOR: #014c90
}

.tpc_content {
	PADDING-BOTTOM: 20px;
	LINE-HEIGHT: 1.8em;
	MARGIN: 0px;
	PADDING-LEFT: 2px;
	PADDING-RIGHT: 2px;
	FONT-FAMILY: Arial;
	PADDING-TOP: 0px
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
	LINE-HEIGHT: 2;
}

.s6 {
	COLOR: #666
}

.tac {
	TEXT-ALIGN: center
}

.c {
	WIDTH: 0px;
	FONT: 0px/0px Arial;
	HEIGHT: 0px;
	CLEAR: both;
	OVERFLOW: hidden
}
</style>
</head>
<body>
	<c:if test="${announce.announcementGroup==0}"><%@include
			file="/pages/template/jsp/common/agentheader.jsp"%></c:if>
	<c:if test="${announce.announcementGroup==1}"><%@include
			file="/pages/template/jsp/common/header.jsp"%></c:if>
	<div class="jf_main clearfix">
		<!--左侧菜单导入-->
		<!--关于我们 左侧菜单-->
		<div class="jf_lefts">
		
			<div class="jfztc_nav">
				<div class="left_tt">
					<a href="#"><span
						style="line-height: 35px;">文章分类</span></a>
				</div>
				<div class="left_lists">
					<table width="193" border="0" bgcolor="#D1E6ED" align="center"
						cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td valign="top" height="200" bgcolor="#FFFFFF"><table
										width="200" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<tbody>
											<tr>
												<td height="33" class="line3" id="aboutUs" style="padding-top:4px;"><table
														width="200" border="0" cellspacing="0" cellpadding="0">
														<tbody>
														    <tr>
																<td width="24" height="25" align="center" class="bg10">&nbsp;</td>
																<td width="126" align="left"><a
																	href="<%=request.getContextPath() %>/announce/more.do?group=${announce.announcementGroup}">系统公告</a></td>
															</tr>
															<c:if test="${announce.announcementGroup==1}">
															<tr>
																<td width="24" height="25" align="center" class="bg10">&nbsp;</td>
																<td width="126" align="left"><a
																	href="<%=request.getContextPath() %>/ej/ieda.do">意见反馈</a></td>
															</tr>
															<tr>
																<td width="24" height="25" align="center" class="bg10">&nbsp;</td>
																<td width="126" align="left"><a
																	href="<%=request.getContextPath() %>/ej/about.do">关于我们</a></td>
															</tr>
					                                        </c:if>
														</tbody>
													</table></td>
											</tr>

										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table>
				</div>
                	

			</div>
		
			<div class="jfztc_nav" style="border-top:0px;border-right:1px solid #ddd;border-left:1px solid #ddd;border-bottom:1px solid #ddd">
				<div class="left_tt">
					<a href="#"><span
						style="line-height: 35px;">最新文章</span></a>
				</div>
				<div class="left_lists">
					<table width="193" border="0" bgcolor="#D1E6ED" align="center"
						cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td valign="top" height="200" bgcolor="#FFFFFF"><table
										width="200" border="0" align="center" cellspacing="0"
										cellpadding="0">
										<tbody>
											<tr>
												<td height="33" class="line3" id="aboutUs"><table
														width="200" border="0" cellspacing="0" cellpadding="0">
														<tbody>
														   <c:forEach var="item" items="${announces}" varStatus="status">
														 
															<tr>
																<td width="24" height="22" align="center" class="bg10">&nbsp;</td>
																<td width="126" height="22" align="left"><a title="${item.title}"
																	href="<%=request.getContextPath() %>/announce/showgg.do?id=${item.id}">${item.title}</a></td>
															</tr>
															</c:forEach>
														</tbody>
													</table></td>
											</tr>

										</tbody>
									</table></td>
							</tr>
						</tbody>
					</table>
				</div>
                	

			</div>
		</div>
        

		<!--右侧主页面-->
		<div class="znx_r">

			<div class="tcxx_tt">
				<span class="tcxx_tt_a"><a
					href="#">最新文章</a> &gt;
					<span class="tcxx_tt_b">公告内容</span> </span>
			</div>
			<div class="pinl">
				<h1 class="font20"></h1>
				<div>

					<table width="680" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td width="24" height="22" align="center"></td>
								<td><div
										style="MARGIN-BOTTOM: 10px; HEIGHT: auto; width: 100%; text-align: center;"
										id=subject_tpc class=read_h2>${announce.title}</div></td>

							</tr>
							<tr>
								<td></td>
								<td class="font12">
									<div class=readContent>
										<!--content_read-->
										<div class=tpc_content>
											<div id=mag_tpc class="operTips tac f12 mb10 s6">
												— 发布时间&nbsp;&nbsp;(
												<fmt:formatDate value="${announce.createTime}"
													pattern="yyyy-MM-dd" />
												) —
											</div>
											<div id=p_tpc class=c></div>
											<div style="WIDTH: 100%; WORD-WRAP: break-word; OVERFLOW: hidden; WORD-BREAK: break-all" id="read_tpc" class="f14 mb10">
												<c:out value="${announce.content}" escapeXml="false" />
											</div>
										</div>
										<!--content_read-->
									</div>
								</td>
							</tr>

						</tbody>
					</table>

				</div>

			</div>

		</div>
		<!--右侧主页面-->
	</div>
	<%@include file="/pages/template/jsp/common/footer.jsp"%>
	<%@include file="/pages/template/jsp/common/links.jsp"%>
	<script src="js/funs.js" type="text/javascript"></script>
</body>
</html>