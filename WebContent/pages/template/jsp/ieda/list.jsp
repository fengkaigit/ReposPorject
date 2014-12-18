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
function delIeda(id){
	if(confirm("确实要删除该信息吗?")){
		if(gloabObj.total%gloabObj.rows==1&&gloabObj.page!=1)
			   loabObj.page = loabObj.page-1;
	   jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/ej/del.do",{ids:id},"get","json",function(data){
		    alert(data.message);
		    window.location.href = "<%=request.getContextPath() %>/ej/list.do?page="+loabObj.page+"&rows="+gloabObj.rows;
	    });
	}
}
function getData(id){
	jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/ej/getFeed.do",{id:id},"get","json",function(data){
		if(data.result){
	      $("#id").val(id);
		  $("#userIdea").val(data.feed.userIdea);
		  $("#replyContent").val(data.feed.systemFeedback);
		  show('replyfeed');
		}
	   
    });
}
function postHandle(){
	  if($("#replyContent").val()==''){
		  alert("回复内容不能为空");
		  $("#replyContent").focus();
		  return false;
	  }
	  var valueObj = {id:$("#id").val(),replyContent:$("#replyContent").val()};
	  jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/ej/reply.do",valueObj,"post","json",function(data){
		  if(data.result){
		    alert("意见回复成功");
		    turnoff('replyfeed');
		    window.location.href = "<%=request.getContextPath() %>/ej/list.do";
		  }
		   
	  });
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
			    window.location.href = "<%=request.getContextPath() %>/ej/list.do?page="+pageNumber+"&rows="+gloabObj.rows;
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
  意见回复 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;反馈信息</span>
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>类型</td>
    <td width="50%">内容</td>
    <td>是否回复</td>
    <td>时间</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${feedbacks}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td><c:choose>
       <c:when test="${item.backType == 1}">
                  改进建议
       </c:when>
       <c:when test="${item.backType == 2}">
                  内容纠错
       </c:when>
       <c:otherwise>
       BUG提交
       </c:otherwise>
     </c:choose></td>
    <td>&nbsp;${item.userIdea}</td>
    <td>&nbsp;<c:if test="${item.backFlag==0}">未回复</c:if><c:if test="${item.backFlag==1}">已回复</c:if></td>
    <td>&nbsp;<fmt:formatDate value="${item.viewTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <td>
	<a class="cur" style="color:#007abd;"  onClick="getData(${item.id});">回复</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="delIeda(${item.id})">删除</a></td>

  </tr>
 </c:forEach>
   
</table>

    </div>
  <div id="pageNav"></div>
  <div class="clear"></div> 
</div>
</div>
<form id="replyForm" action="<%=request.getContextPath() %>/ej/reply.do" method="post">
<div id="replyfeed" class="divWin" style="display:none;width:500px;">
<div class="close cur"  onclick="javascript:turnoff('replyfeed')">关闭</div>
            <h1></h1>
			 <p >意见回复</p> 
			 <input type="hidden" id="id" name="id" />
            <table width="400" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td width="28%" bgcolor="#f1f8ff" align="right" style="vertical-align: middle;">反馈内容</td>
                <td bgcolor="#FFFFFF">
                     <textarea style="height: 105px;border-color: rgb(214, 214, 214);" onpropertychange="if(value.length&gt;10000)value=value.substring(0,10000)" name="userIdea"  class="textareafeedBox text text-empty" id="userIdea" data-default=""></textarea>
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">回复</td>
                <td bgcolor="#FFFFFF">
                     <textarea style="height: 105px;border-color: rgb(214, 214, 214);" onpropertychange="if(value.length&gt;10000)value=value.substring(0,10000)" name="replyContent" class="textareafeedBox text text-empty" id="replyContent" data-default=""></textarea>
                </td>
            </tr>
            <tr>
                <td bgcolor="#FFFFFF" align="center" colspan="2">
				<input  type="button" class="jfxx_btn3" onClick="postHandle()" value="保存" name="searchBill"/>
				
				</td>
            </tr>
            </tbody></table>
        </div>
        </form>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>