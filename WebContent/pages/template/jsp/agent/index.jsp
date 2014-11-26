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
function delAgent(id){
	if(confirm("确实要删除该信息吗?")){
	   
	   jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/del.do",{ids:id},"get","json",function(data){
		    alert(data.message);
		    window.location.href = "<%=request.getContextPath() %>/agent/list.do";
	    });
	}
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/agentheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  代理商设置 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;代理商信息</span>
	
	<span class="fr cur" onClick="openWin('<%=request.getContextPath() %>/agent/add.do')"><span class="fcr">新建代理商</span></span>	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>登录名</td>
    <td>名称</td>
    <td>地区</td>
    <td>邮箱</td>
    <td>联系电话</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${agentList}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.registAccount}</td>
    <td>&nbsp;${item.registRealName}</td>
    <td>&nbsp;${item.areaPathName}</td>
    <td>&nbsp;${item.EMail}</td>
    <td>&nbsp;${item.mobile}</td>
    <td>
	<a class="cur" style="color:#007abd;"  onClick="openWin('<%=request.getContextPath() %>/agent/edit/${item.id}.do')" >修改</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="delAgent(${item.id})">删除</a></td>

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