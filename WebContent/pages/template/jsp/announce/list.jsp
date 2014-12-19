<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
var gloabObj = {page:<c:out value="${page}"/>,rows:<c:out value="${rows}"/>,total:<c:out value="${total}"/>};
function delAgent(id){
	if(confirm("确实要删除该信息吗?")){
		   if(gloabObj.total%gloabObj.rows==1&&gloabObj.page!=1)
			   gloabObj.page = gloabObj.page-1;
	   jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/announce/del.do",{ids:id},"get","json",function(data){
		    alert(data.message);
		    window.location.href = "<%=request.getContextPath() %>/announce/list.do?page="+gloabObj.page+"&rows="+gloabObj.rows;
	    });
	}
}
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
			    window.location.href = "<%=request.getContextPath() %>/announce/list.do?page="+pageNumber+"&rows="+gloabObj.rows;
			}
	  });
	}
});
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  公告维护
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;系统公告信息</span>
	
	<span class="fr cur" onClick="openWin('<%=request.getContextPath() %>/announce/add.do')"><span class="fcr">发布公告</span></span>	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>标题</td>
    <td>范围</td>
    <td>分组</td>
    <td>状态</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${announces}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.title}</td>
    <td><c:choose>
       <c:when test="${item.announcementScope == 0}">
                  全国
       </c:when>
       <c:when test="${item.announcementScope == 1}">
                  省
       </c:when>
       <c:otherwise>
                  市
       </c:otherwise>
     </c:choose></td>
    <td><c:choose>
       <c:when test="${item.announcementGroup == 0}">
                  代理商
       </c:when>
       <c:otherwise>
                  用户
       </c:otherwise>
     </c:choose></td>
    <td><c:choose>
       <c:when test="${item.status == 0}">
                 未失效
       </c:when>
       <c:otherwise>
                  已失效
       </c:otherwise>
     </c:choose></td>
    <td>
	<a class="cur" style="color:#007abd;"  onClick="openWin('<%=request.getContextPath() %>/announce/edit/${item.id}.do')" >修改</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="delAgent(${item.id})">删除</a></td>

  </tr>
 </c:forEach>
   
</table>

    </div>
  <div id="pageNav"></div>
  <div class="clear"></div>
</div>
</div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>