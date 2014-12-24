<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script type="text/javascript" src="<c:url value='/js/My97DatePicker/WdatePicker.js'/>"></script>
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
	        	query(pageNumber);
			}
	  });
	}
});
function query(page){
	if(!page)
		page = 1;
    var qForm = document.getElementById("queryForm");
    qForm.action = "<%=request.getContextPath() %>/agent/worklist.do?qFlag=1&page="+page+"&rows="+gloabObj.rows;
    qForm.submit();
}
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
	   <span class="fr cur" style="margin-right:18px;"><input  type="button" class="jfxx_btn9" onClick="query()" value="查询" name="searchBtn"/></span>
	   <span class="fr cur">&nbsp;&nbsp;&nbsp;</span>
	   <span class="fr cur"><label style="font-size: 15px;">状态：</label><select class="zc_city"  style="width:95px;height:28px;" id="status" name="status">
                             <option value="">请选择状态</option>
                             <option value="0" <c:if test="${status==0}">selected</c:if>>待处理</option>
                             <option value="1" <c:if test="${status==1}">selected</c:if>>已处理</option>
                        </select></span>
	   <span class="fr cur" style="margin-right:5px;"><label style="font-size: 15px;">收费类型：</label><select class="zc_city"  style="width:105px;height:28px;" id="payType" name="payType">
                             <option value="">请选择收费类型</option>
                              <c:forEach var="item" items="${payTypes}" varStatus="status"> 
                                <option value="${item.key}" <c:if test="${item.key==payType}">selected</c:if>>${item.value}费</option>
                             </c:forEach>
                          
                        </select></span>
     <span class="fr cur" style="margin-right:5px;"><label style="font-size: 15px;">日期：</label><input class="on-show" style="width:100px;" type="text" name="startDate" id="startDate" title="请输入开始日期" placeholder="请输入开始日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" value="${startDate}"/>&nbsp;至&nbsp;<input class="on-show" style="width:100px;" type="text" name="endDate" id="endDate" title="请输入结束日期" placeholder="请输入结束日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" value="${endDate}"/></span>
	</form>
    </div>
    <div class="jfzh-bottom clearfix"> 
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;批次单信息</span>
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>标题</td>
    <td>创建时间</td>
    <td>金额</td>
    <td>缴费类型</td>
    <td>状态</td>
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
    <td>&nbsp;<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/>&nbsp;${item.payTypeName}费${item.billNum}笔</td>
    <td>&nbsp;<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>&nbsp;${item.batchMoney}</td>
	<td>&nbsp;${item.payTypeName}费</td>
	<td>&nbsp;<c:if test="${item.batchStatus==0}">待处理</c:if><c:if test="${item.batchStatus==1}">已处理</c:if></td>
	 <td>
	<a class="cur" style="color:#007abd;"  onClick="openWin('<%=request.getContextPath() %>/agent/billlist.do?id=${item.id}')" >查看缴费单</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="downloadbill('${item.id}');">导出缴费单</a>
	<c:if test="${item.batchStatus==0}">&nbsp;<a class="cur" style="color:#007abd;" onclick="openWin('<%=request.getContextPath() %>/agent/statuslist.do?id=${item.id}')">修改办理状态</a></c:if></td>
  </tr>
 </c:forEach>
   
</table>

    </div>
   <div id="pageNav"></div>
  <div class="clear"></div> 
</div>
</div>
</div>
<iframe id="downloadframe" src="" style="display:none;"></iframe>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>