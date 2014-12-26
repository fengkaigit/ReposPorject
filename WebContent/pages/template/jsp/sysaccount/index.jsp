<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
function delManager(id){
	if(confirm("确实要删除该信息吗?")){
	   jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/sysman/del.do",{ids:id},"get","json",function(data){
		    alert(data.message);
		    window.location.href = "<%=request.getContextPath() %>/sysman/list.do";
	    });
	}
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
 系统账户维护 
  </div>
	<div class="jfzh-con">
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;系统账户信息</span>
	</div>
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
	  <tr class="add">
	    <td>序号</td>
	    <td>账户账号</td>
	    <td>账户名称</td>
	    <td>账户余额</td>
	    <td>操作</td>
	  </tr>
	  <c:forEach var="item" items="${syslist}" varStatus="status">
	   <tr <c:choose>
	       <c:when test="${(status.index+1) % 2 == 0}">
	         class="add"
	       </c:when>
	       <c:otherwise>
	         class="even"
	       </c:otherwise>
	     </c:choose> >
	    <td>&nbsp;${status.index+1}</td>
	    <td>&nbsp;${item.acctNo}</td>
	    <td>&nbsp;${item.acctName}</td>
	    <td>&nbsp;${item.acctBalance}</td>
	    <td>
		<a class="cur" style="color:#007abd;"  onClick="openWin('<%=request.getContextPath() %>/sysaccount/edit/${item.bankAccountId}.do')" >修改</a>&nbsp;
		</td>
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