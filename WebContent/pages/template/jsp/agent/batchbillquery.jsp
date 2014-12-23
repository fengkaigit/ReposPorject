<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
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
			    window.location.href = "<%=request.getContextPath() %>/agent/worklist.do?status=0&page="+pageNumber+"&rows="+gloabObj.rows;
			}
	  });
	}
});
function downloadbill(id){
	document.getElementById("downloadframe").src = "<%=request.getContextPath() %>/agent/downbatch.do?id="+id;
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/agentheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  我的工作 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">
        <div class="name">
     <form id="queryForm" method="post">
	   <span class="fr cur" style="margin-right:3px;"><input  type="button" class="jfxx_btn9" onClick="query()" value="查询" name="searchBtn"/></span>
	   <span class="fr cur">&nbsp;&nbsp;&nbsp;</span>
	   <span class="fr cur"><label style="font-size: 15px;">状态：</label><select class="zc_city"  style="width:100px;height:28px;" id="status" name="status">
                             <option value="">请选择状态</option>
                             <option value="0" <c:if test="${status==0}">selected</c:if>>待处理</option>
                             <option value="1" <c:if test="${status==1}">selected</c:if>>已处理</option>
                        </select></span>
	   <span class="fr cur" style="margin-right:5px;"><label style="font-size: 15px;">收费类型：</label><select class="zc_city"  style="width:100px;height:28px;" id="payType" name="payType">
                             <option value="">请选择收费类型</option>
                              <c:forEach var="area" items="${areas}" varStatus="status"> 
                                <option value="${area.id}" <c:if test="${area.id==parentAreaId}">selected</c:if>>${area.province}</option>
                             </c:forEach>
                          
                        </select></span>
	</form>
    </div>
    <div class="jfzh-bottom clearfix"> 
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;待办任务</span>
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>标题</td>
    <td>创建时间</td>
    <td>金额</td>
    <td>缴费类型</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${works}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/>${item.payTypeName}费${item.billNum}笔</td>
    <td>&nbsp;<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>&nbsp;${item.batchMoney}</td>
	<td>&nbsp;${item.payTypeName}费</td>
	 <td>
	<a class="cur" style="color:#007abd;"  onClick="openWin('<%=request.getContextPath() %>/agent/billlist.do?id=${item.id}')" >查看缴费单</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="downloadbill('${item.id}');">导出缴费单</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="openWin('<%=request.getContextPath() %>/agent/statuslist.do?id=${item.id}')">修改办理状态</a></td>
  </tr>
 </c:forEach>
   
</table>

    </div>
   <div id="pageNav"></div>
  <div class="clear"></div> 
</div>
</div>
<iframe id="downloadframe" src="" style="display:none;"></iframe>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>