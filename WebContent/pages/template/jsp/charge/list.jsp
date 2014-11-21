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
<script>
function delCharge(id){
	if(confirm("确实要删除该信息吗?")){
	   
	   jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/charge/del.do",{ids:id},"get","json",function(data){
		    alert(data.message);
		    window.location.href = "<%=request.getContextPath() %>/charge/list.do";
	    });
	}
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  收费单位设置 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;收费单位信息</span>
	
	<span class="fr cur" onClick="openWin('<%=request.getContextPath() %>/charge/add.do')"><span class="fcr">新建收费单位</span></span>	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>收费单位名称</td>
    <td>地区</td>
    <td>收费类型</td>
    <td>有效银行账户</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${charges}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.enterpriseName}</td>
    <td>&nbsp;${item.areaPathName}</td>
    <td>&nbsp;${item.payTypeName}</td>
    <td>&nbsp;${item.careNumber}</td>
    <td>
	<a class="cur" style="color:#007abd;"  onClick="openWin('<%=request.getContextPath() %>/charge/edit/${item.id}.do')" >修改</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="delCharge(${item.id})">删除</a></td>

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