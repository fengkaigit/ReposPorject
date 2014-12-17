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
	
	var checked = false;
	var childChks = document.getElementsByName("chkSel");
	for(var i=0;i<childChks.length;i++){
		if(childChks[i].checked)
			checked = true;
	}
	if(!checked){
		alert("请选择缴费单");
		return false;
	}
	$('#statusForm').ajaxSubmit(function(data){
        if(data.result){
    	   alert('办理成功');
    	  window.location.href = "<%=request.getContextPath() %>/agent/statuslist.do?id="+$("#batchId").val();
        }
   });
	//document.getElementById("statusForm").submit();
}
function query(){
	var qForm = document.getElementById("queryForm");
	qForm.action = "<%=request.getContextPath() %>/agent/statuslist.do";
	qForm.submit();
}
function selectall(obj){
	var check = false;
	if(obj.checked){
		check = true;
	}else{
		check = false;
	}
	var childChks = document.getElementsByName("chkSel");
	for(var i=0;i<childChks.length;i++){
		childChks[i].checked = check;
	}
}
function saveReply(){
	  if($("#replyContent").val()==''){
		  alert("内容不能为空");
		  $("#replyContent").focus();
		  return false;
	  }
	  var valueObj = {billId:$("#billId").val(),userId:$("#userId").val(),serverContent:$("#replyContent").val(),noticeType:3,noticeMode:1};
	  jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/notice.do",valueObj,"post","json",function(data){
		  if(data.result){
		    alert("回复成功");
		    hiddenDiv('jfhfwin');
		    window.location.href = "<%=request.getContextPath() %>/agent/statuslist.do?id="+$("#batchId").val();
		  }
		   
	  });
}
function showReplyDiv(userId,id){
	$("#userId").val(userId);
	$("#billId").val(id);
	showDivWin('jfhfwin',380,260,'');
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/agentheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
    修改办理状态
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;缴费单信息</span>
	<span class="fr cur" onClick="complate()"><span class="fcr">办理成功</span></span>
	<span class="fr cur"><span class="fcr">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span>
	<form id="queryForm" method="post"><span class="fr cur"><input class="on-show" autocomplete="off" type="text" name="id" title="请输入批次单号或缴费单号" placeholder="请输入批次单号或缴费单号"/><input  type="button" class="jfxx_btn9" onClick="query()" value="查询" name="searchBtn"/></span></form>
	</div>
    <form id="statusForm" action="<%=request.getContextPath() %>/agent/complate.do" method="post">
	 <input type="hidden" id="batchId" name="batchId" value="${batchId}" />
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
    <td><input type="checkbox" name="chkSel" value="${item.id}"/></td>
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.id}</td>
    <td>&nbsp;<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <td>&nbsp;${item.payMoney}</td>
    <td>&nbsp;${item.paidMoney}</td>    
	<td>&nbsp;${item.userName}</td>
	<td>&nbsp;${item.entName}</td>
	<td>&nbsp;${item.paymentTypeName}费</td>
	<td><a class="cur" style="color:#007abd;" onclick="showReplyDiv(${item.userId},${item.id});">缴费异常回复</a></td>
  </tr>
 </c:forEach>
   
</table>
  </form>
    </div>
  
</div>
</div>
<form id="replyForm" method="post">
<input type="hidden" name="userId" id="userId" />
<input type="hidden" name="billId" id="billId" />
<input type="hidden" name="noticeType" id="noticeType" value="3"/>
<input type="hidden" name="noticeMode" id="noticeMode" value="1"/>
<div id="jfhfwin" class="divWin">
<div class="close cur" onClick="hiddenDiv('jfhfwin')">关闭</div>
    <h1 id="title"></h1>
	<div class="jfzh-con" style="width:460px;height:350px;padding:5px;border:0px;">
		<div class="jfzh-top clearfix">
		<table id="login-table" style=" border:0px;width:400px;" cellpadding="0" cellspacing="0">  
        <tr> 
<td style="border:1px;text-align:left;width:60px;">内容：</td> 
<td style=" border:0px;width:340px;" align="left"> <textarea style="height: 145px;border-color: rgb(214, 214, 214);" onpropertychange="if(value.length&gt;10000)value=value.substring(0,10000)" name="replyContent" class="textareajfBox text text-empty" id="replyContent" data-default=""></textarea>
</td> 
</tr> 
<tr> 
<td style=" border:0px;" colspan="2" class="save">
<input  type="button" class="jfxx_btn3"  onClick="saveReply()" value="确认">
</td> 
</tr> 


</table> 
	

        </div>
    
   
    </div>
	 
    
</div>
</form>
<div id="bg"></div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>