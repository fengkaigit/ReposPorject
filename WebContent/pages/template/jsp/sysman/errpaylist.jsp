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
			    window.location.href = windowl.location.pathname+"?id=${param.id}&errflag=${param.errflag}&page="+pageNumber+"&rows="+gloabObj.rows;
			}
	  });
	}
});
function downloadbill(id){
	document.getElementById("downloadframe").src = "<%=request.getContextPath() %>/agent/downbatch.do?id="+id;
}
function showReplyDiv(id){
	jQuery.shfftAjaxHandler.ajaxRequest("<%=request.getContextPath() %>/sysman/showErrNotice.do",{billId:id},"get","json",function(data){
		   if(data!=null&&data!=''){
			  $("#serverContent").val(data.serverContent);
			  showDivWin('jfhfwin',520,250,'');
		   }
	 });
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
	<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  异常缴费单
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;异常缴费单信息</span>
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>缴费单单号</td>
    <td>缴费时间</td>
    <td>应缴金额</td>
    <td>实缴金额</td>
    <td>缴费用户</td>
    <td>收费单位</td>
    <td>缴费类型</td>
    <td>状态</td>
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
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.id}</td>
    <td>&nbsp;<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <td>&nbsp;${item.payMoney}</td>
    <td>&nbsp;${item.paidMoney}</td>    
	<td>&nbsp;${item.userName}</td>
	<td>&nbsp;${item.entName}</td>
	<td>&nbsp;${item.paymentTypeName}费</td>
	<td>&nbsp;${paystatus[item.paymentStatus]}</td>
	<td><a class="cur" style="color:#007abd;" onclick="showReplyDiv(${item.id});">查看异常信息</a></td>
  </tr>
 </c:forEach>
   
</table>

    </div>
   <div id="pageNav"></div>
  <div class="clear"></div> 
</div>
</div>
<div id="jfhfwin" class="divWin" style="display:none;">
<div class="close cur"  onclick="javascript:hiddenDiv('jfhfwin')">关闭</div>
            <h1></h1>
			 <p >缴费异常信息</p> 
            <table style="width:100%;" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td bgcolor="#f1f8ff" align="right">异常内容</td>
                 <td bgcolor="#FFFFFF">
                       <textarea style="height: 105px;border-color: rgb(214, 214, 214);" onpropertychange="if(value.length&gt;10000)value=value.substring(0,10000)" class="textareafeedBox text text-empty" name="serverContent" id="serverContent"></textarea>
				 </td>
            </tr>
             <tr>
                <td bgcolor="#FFFFFF" align="center" colspan="2">
				<input  type="button" class="jfxx_btn3" onClick="hiddenDiv('jfhfwin')" value="关闭" name="searchBill"/>
				</td>
            </tr>
            </tbody></table>
 </div>
 <div id="bg"></div>
<iframe id="downloadframe" src="" style="display:none;"></iframe>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>