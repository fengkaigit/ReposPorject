<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
function complate(){
	$('#statusForm').ajaxSubmit(function(data){
	    if(data.result){
	    	alert('意见反馈成功！谢谢您的参与，我们会尽快给你回复。');
	    	window.location.href = "<%=request.getContextPath() %>/ej/ieda.do";
	    }
    });
}
function selectall(obj){
	var check = false;
	if(obj.checked){
		check = true;
	}else{
		check = false;
	}
	var childChks = document.getElementsByName("chksel");
	for(var i=0;i<childChks.length;i++){
		childChks[i].checked = check;
	}
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/agentheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  
  </div>
<div class="jfzh-con">

	 <form id="statusForm" action="" method="post">
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;缴费单信息</span>
	<span class="fr cur" onClick="complate()"><span class="fcr">办理成功</span></span>
	</div>
   
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
     <td><input type="checkbox" id="chkall" onclick="selectall(this)"/></td>
    <td>序号</td>
    <td>缴费单单号</td>
    <td>缴费时间</td>
    <td>应缴金额</td>
    <td>实缴金额</td>
    <td>缴费用户</td>
    <td>收费单位</td>
    <td>缴费类型</td>
      <td>操作</td>
  </tr>
  <c:forEach var="item" items="${bills}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td><input type="checkbox" name="chksel"/></td>
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.id}</td>
    <td>&nbsp;<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <td>&nbsp;${item.payMoney}</td>
    <td>&nbsp;${item.paidMoney}</td>    
	<td>&nbsp;${item.userName}</td>
	<td>&nbsp;${item.entName}</td>
	<td>&nbsp;${item.paymentTypeName}费</td>
	<td><a class="cur" style="color:#007abd;" onclick="downloadbill('${item.id}');">缴费回复</a></td>
  </tr>
 </c:forEach>
   
</table>

    </div>
    </form>
</div>
</div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>