<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/allQuery.css">
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
 劳务费划款单查询
  </div>
<div class="jfzh-con">
    <div class="jfzh-bottom clearfix">
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;划款单信息</span>
<%-- 	<c:if test="${flag!=1}"><form id="queryForm" method="post" action="<%=request.getContextPath() %>/bill/servicelist.do">
	<div class="jy_date">
	<span class="fr cur" >
		生成日期： 从&nbsp;
	    <input type="text" onClick="WdatePicker()" size="10" class="Wdate" id="startDate" value="2014-07-31" name="startDate">&nbsp;&nbsp;到&nbsp;&nbsp;<input type="text" size="10" onClick="WdatePicker()" class="Wdate" id="endDate" value="2014-12-31" name="endDate">
		&nbsp;
		<input type="submit" class="jycx" value="查 询" id="btnSearch">
	</span>
	</div>
	</form>
	</c:if> --%>
	</div>
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>划款单单号</td>
    <td>金额</td>
    <td>生成时间</td>
    <td>划款时间</td>
    <td>状态</td>
  </tr>
  <c:forEach var="item" items="${servicelist}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;<a title="查看对应缴费单" class="cur" style="color:#007abd;"  onClick="openWin('<%=request.getContextPath() %>/bill/showPayment/${item.id}.do')">${item.id}</a></td>
    <td>&nbsp;${item.profitMoney}</td>
    <td>&nbsp;${item.createDate}</td>
    <td>&nbsp;${item.confirmDate}</td>
    <td>&nbsp;${item.strStatus}</td>

  </tr>
 </c:forEach>
   
</table>

    </div>
    
</div>
</div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>