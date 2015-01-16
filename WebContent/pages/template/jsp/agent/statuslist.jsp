<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>e缴365</title>
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
	var id = $("#id");
	if(id.val()==''){
		alert("请输入批次单号或缴费单号");
		id.focus();
		return false;
	}
	var qForm = document.getElementById("queryForm");
	qForm.action = "<%=request.getContextPath() %>/agent/statuslist.do";
	qForm.submit();
}
function postHandle(){
	  var noticeType = $("#noticeType");
	  var noticeMode = $("#noticeMode");
	  var content = $("#serverContent");
	  if(noticeType==''){
		  alert("请选择通知类型");
		  noticeType.focus();
		  return false;
	  }
	  if(noticeMode==''){
		  alert("请选择通知方式");
		  noticeMode.focus();
		  return false;
	  }
	  if(content.val()==''){
		  alert("内容不能为空");
		  content.focus();
		  return false;
	  }
	  $('#replyForm').ajaxSubmit(function(data){
		  if(data.result){
			    alert("回复成功");
			    hiddenDiv('jfhfwin');
			    window.location.href = "<%=request.getContextPath() %>/agent/statuslist.do?id="+$("#batchId").val();
		   }
	   });
}
function showReplyDiv(userId,id,paidMoney){
	$("#userId").val(userId);
	$("#billId").val(id);
	$("#paidMoney").val(paidMoney);
	showDivWin('jfhfwin',520,415,'');
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
    <div class="name">
     <form id="queryForm" method="post">
       <span class="fr cur" style="margin-right:19px;"><input  type="button" class="jfxx_btn9" onClick="complate()" value="办理成功" name="complatehBtn"/></span>
	   <span class="fr cur" style="margin-right:3px;"><input  type="button" class="jfxx_btn9" onClick="query()" value="查询" name="searchBtn"/></span>
	   <span class="fr cur">&nbsp;&nbsp;&nbsp;</span>
	   <span class="fr cur"><input class="on-show" autocomplete="off" type="text" name="id" id="id" title="请输入批次单号或缴费单号" placeholder="请输入批次单号或缴费单号"/></span>
	</form>
    </div>
    <div class="jfzh-bottom clearfix">
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;缴费单信息</span>
	</div>
    <form id="statusForm" action="<%=request.getContextPath() %>/agent/complate.do" method="post">
	 <input type="hidden" id="batchId" name="batchId" value="${batchId}" />
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
     <td><input type="checkbox" id="chkall" onclick="selectall(this,'chkSel')"/></td>
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
    <td><input type="checkbox" name="chkSel" value="${item.id}"/></td>
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.id}</td>
    <td>&nbsp;<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    <td>&nbsp;${item.payMoney}</td>
    <td>&nbsp;${item.paidMoney}</td>    
	<td>&nbsp;${item.userName}</td>
	<td>&nbsp;${item.entName}</td>
	<td>&nbsp;${item.paymentTypeName}费</td>
	<td>&nbsp;${paystatus[item.paymentStatus]}</td>
	<td><a class="cur" style="color:#007abd;" onclick="showReplyDiv(${item.userId},${item.id},${item.paidMoney});">缴费异常回复</a></td>
  </tr>
 </c:forEach>
   
</table>
  </form>
    </div>
  
</div>
</div>
<form id="replyForm" method="post" action="<%=request.getContextPath() %>/agent/notice.do">
<input type="hidden" name="userId" id="userId" />
<input type="hidden" name="billId" id="billId" />
 <input type="hidden" id="batchId" name="batchId" value="${batchId}" />
  <input type="hidden" id="paidMoney" name="paidMoney"/>
<div id="jfhfwin" class="divWin" style="display:none;">
<div class="close cur"  onclick="javascript:hiddenDiv('jfhfwin')">关闭</div>
            <h1></h1>
			 <p >添加通知信息</p> 
            <table style="width:100%;" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td width="28%" bgcolor="#f1f8ff" align="right" style="vertical-align: middle;">通知状态</td>
                <td bgcolor="#FFFFFF">
                          <select class="zc_city"  style="width:190px;height:28px;" id="sendStatus" name="sendStatus">
                                <option value="0" >有效</option>
                                <option value="1" >失效</option>
                        </select>            
                        </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">通知类型</td>
                <td bgcolor="#FFFFFF">
                        <select class="zc_city"  style="width:190px;height:28px;" id="noticeType" name="noticeType">
                             <option value="">请选择类型</option>
                             <c:forEach var="item" items="${noticeTypes}" varStatus="status">
                                <option value="${item.key}" <c:if test="${item.key==3}">selected</c:if>>${item.value}</option>
                             </c:forEach>    
                          
                        </select>                
                        </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">通知方式</td>
                <td bgcolor="#FFFFFF">
                        <select class="zc_city"  style="width:190px;height:28px;" id="noticeMode" name="noticeMode">
                             <option value="">请选择方式</option>
                             <c:forEach var="item" items="${noticeModes}" varStatus="status">
                                <option value="${item.key}" <c:if test="${item.key==2}">selected</c:if>>${item.value}</option>
                             </c:forEach>    
                          
                        </select>                
                        </td>
            </tr>
           <tr>
                <td bgcolor="#f1f8ff" align="right">异常状态</td>
                <td bgcolor="#FFFFFF">
                        <select class="zc_city"  style="width:190px;height:28px;" id="payerrStatus" name="payerrStatus">
                             <option value="">请选择状态</option>
                             <c:forEach var="item" items="${payerrs}" varStatus="status">
                                <option value="${item.key}">${item.value}</option>
                             </c:forEach>    
                          
                        </select>                
                        </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">通知内容</td>
                 <td bgcolor="#FFFFFF">
                       <textarea style="height: 105px;border-color: rgb(214, 214, 214);" onpropertychange="if(value.length&gt;10000)value=value.substring(0,10000)" class="textareafeedBox text text-empty" name="serverContent" id="serverContent"></textarea>
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
 <div id="bg"></div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>