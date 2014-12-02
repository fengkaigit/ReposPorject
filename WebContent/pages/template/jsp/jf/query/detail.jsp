<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>缴费记录</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/aboutUs.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/allQuery.css">

<link href="<%=request.getContextPath() %>/css/chart/chart.css" type="text/css" rel="stylesheet">
<style>
.queryhead{
 color: #515151;
    font-weight: bold;
    height: 27px;
    line-height: 27px;
    text-align: center;
	font-size:12px;
	}
</style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ofc/swfobject.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ofc/json2.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			swfobject.embedSWF("<%=request.getContextPath() %>/js/ofc/open-flash-chart.swf", "my_chart", "760", "300", "9.0.0", 
					"expressInstall.swf",
					{"data-file":"<%=request.getContextPath() %>/jf/queryAreachart.do"});
		});
		
		</script>
</head>
<body class="index" style="overflow-x:hidden;overflow-y:auto;">

            

<div>
<div id="searchpages">
		<table width="760" height="35" border="0" align="left" cellspacing="0" cellpadding="0">
		<tbody>
		 <tr class="add">
		 <td width="20%" class="queryhead">交易时间</td>
		 <td width="10%" class="queryhead">业务类型</td>
		 <td width="13%" class="queryhead">交易内容</td>
        <td width="13%" class="queryhead">账期</td>      
        <td width="13%" class="queryhead">支付方式</td>
        <td width="15%" class="queryhead">金额（元）</td>
        <td width="16%" class="queryhead">状态</td>
        
        </tr>
        <c:forEach var="item" items="${details}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
     	<td >${item.payTime}</td>
     	<td >${item.business}</td>
     	<td >${item.payTypeStr}</td>
        <td >${item.year}年${item.month}月</td>        
        <td>${item.payMode}</td>
        <td>${item.money+item.sucessMoney}</td>
        <td>${item.payStatuStr}</td>
        </tr>
 		</c:forEach>
 		
 		
		</tbody>
        </table>
	</div>


<!--合计图形 -->

</div> 
<div id="my_chart"></div>

</body></html>