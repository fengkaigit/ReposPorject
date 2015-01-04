<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/global.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/common.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/aboutUs.css">
<style>
* {
	font-family: '微软雅黑', 'weiruanyahei';
}
.list {
	float: left;
	display: inline;
	margin: 0 10px;
	width: 100%;
	height: 28px;
	border-bottom: 1px dashed #ddd;
}
.listName {
	float: left;
	display: inline;
	padding: 0 10px;
	width: 580px;
	height: 28px;
	line-height: 28px;
	overflow: hidden;
	color: #555555;
	text-align: left;
	white-space: nowrap;
}
.desktopTime {
	width: 70px;
	height: 28px;
	line-height: 28px;
	color: #999999;
	float: right;
	display: inline;
	margin: 0 2px;
	text-align: right;
}
.listName a:hover{
	color: red!important;
}
</style>
<script>
var gloabObj = {page:<c:out value="${page}"/>,rows:<c:out value="${rows}"/>,total:<c:out value="${total}"/>};
$(document).ready(function(){
	if(gloabObj.total!=0){
	   $("#pageNav").pagination({
	        items: gloabObj.total,
	        itemsOnPage:gloabObj.rows,
	        currentPage:gloabObj.page,
	        cssStyle: 'light-theme',
			prevText:'上一页',
			nextText:'下一页',
	        onPageClick:function(pageNumber, event){
			    window.location.href = "<%=request.getContextPath()%>/announce/more.do?page="+pageNumber+"&rows="+gloabObj.rows;
			}
	  });
	}
});
</script>
</head>
<body>
	<c:if test="${param.group==0}"><%@include
			file="/pages/template/jsp/common/agentheader.jsp"%></c:if>
	<c:if test="${param.group==1}"><%@include
			file="/pages/template/jsp/common/header.jsp"%></c:if>
	<div class="jf_main clearfix">
		<!--左侧菜单导入-->
		<!--关于我们 左侧菜单-->
		<div class="jf_lefts">
		<div class="jfztc_nav">
				<div class="left_tt">
					<a href="#"><span
						style="line-height: 35px;">最新文章</span></a>
				</div>
				<div class="left_lists">
				  
					<table width="193" border="0" bgcolor="#D1E6ED" align="center"
						cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td valign="top" height="200" bgcolor="#FFFFFF"> <c:if test="${newSize>0}"><table
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
																	href="<%=request.getContextPath() %>/announce/showgg.do?id=${item.id}"><span class="fcg2">${item.title}</span><span class="fcg fr">[<fmt:formatDate value="${item.createTime}"
													pattern="yyyy-MM-dd" />]</span></a></td>
															</tr>
															</c:forEach>
														</tbody>
													</table></td>
											</tr>

										</tbody>
									</table></c:if></td>
							</tr>
						</tbody>
					</table>
					
				</div>
                	

			</div>
			<div class="jfztc_nav" style="border-top:0px;border-right:1px solid #ddd;border-left:1px solid #ddd;border-bottom:1px solid #ddd">
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
																	href="<%=request.getContextPath() %>/announce/more.do?group=${param.group}">系统公告</a></td>
															</tr>
															<c:if test="${param.group==1}">
															<tr>
																<td width="24" height="25" align="center" class="bg10">&nbsp;</td>
																<td width="126" align="left"><a
																	href="<%=request.getContextPath()%>/ej/ieda.do">意见反馈</a></td>
															</tr>
															<tr>
																<td width="24" height="25" align="center" class="bg10">&nbsp;</td>
																<td width="126" align="left"><a
																	href="<%=request.getContextPath()%>/ej/about.do">关于我们</a></td>
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
			
		</div>
        

		<!--右侧主页面-->
		<div class="znx_r">

			<div class="tcxx_tt">
				<span class="tcxx_tt_a"><a
					href="#">文章分类</a> &gt;
					<span class="tcxx_tt_b">系统公告</span> </span>
			</div>
			<div class="pinl">
				<h1 class="font20"></h1>
				<div style="width:705px;">
					
							
								<c:forEach var="item" items="${announceall}" varStatus="status">
									<div class="list">
										<div class="listName">
											<a
												href="<%=request.getContextPath() %>/announce/showgg.do?id=${item.id}"
												title="${item.title}">${item.title}</a>
										</div>
										<div class="desktopTime">
											<fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd" />
										</div>
									</div>
								</c:forEach>
							
				
					
				</div>
                <div id="pageNav"></div>
                <div class="clear"></div>
			</div>

		</div>
		<!--右侧主页面-->
	</div>
	<%@include file="/pages/template/jsp/common/footer.jsp"%>
	<%@include file="/pages/template/jsp/common/links.jsp"%>
	<script src="js/funs.js" type="text/javascript"></script>
</body>
</html>