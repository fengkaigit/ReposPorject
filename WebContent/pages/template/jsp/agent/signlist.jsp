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
function delAgent(id){
	if(confirm("确实要删除该信息吗?")){
		if(gloabObj.total%gloabObj.rows==1&&gloabObj.page!=1)
			gloabObj.page = gloabObj.page-1;
	   jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/del.do",{ids:id},"get","json",function(data){
		    alert(data.message);
		    window.location.href = "<%=request.getContextPath() %>/agent/list.do?page="+gloabObj.page+"&rows="+gloabObj.rows;
	    });
	}
}
$(document).ready(function(){
	 $.formValidator.initConfig({formID:"saveForm",debug:false,submitOnce:false,
			onError:function(msg,obj,errorlist){
				$("#errorlist").empty();
				alert(msg);
			},
			onSuccess:function(){
				return false;
			},
			submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	   });
	 $("#signRate").formValidator().inputValidator({min:1,onError:"签约比例不能为空"});
});
function getData(id){
	//jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/sysparam/rateedit.do",{id:id},"get","json",function(data){
		$("#id").val(id);
	    showDivWin('addRate',400,170,'');
    //});
	
}
function postHandle(){
	 if($.formValidator.pageIsValid()){
		 var valueObj = {id:$("#id").val(),rate:$("#signRate").val()};
		 jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/urate.do",valueObj,"get","json",function(data){
			 if(data.result){ 
			    alert('签约比例修改成功');
		    	hiddenDiv('addRate');
			    window.location.href = "<%=request.getContextPath() %>/agent/signlist.do?id=${param.id}";
		    }
		 });
	 }
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  代理商签约信息设置 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;代理商签约信息</span>	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>开始日期</td>
    <td>结束日期</td>
    <td>比例(%)</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${signs}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;<fmt:formatDate value="${item.beginTime}" pattern="yyyy-MM-dd"/></td>
    <td>&nbsp;<fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd"/></td>
    <td>&nbsp;${item.signRate}</td>
    <td>
	<a class="cur" style="color:#007abd;"  onClick="getData(${item.id})" >修改</a>&nbsp;</td>

  </tr>
 </c:forEach>
   
</table>

    </div>
</div>
</div>
<form id="saveForm" method="post">
<input type="hidden" id="id" name="id"/>
<div id="addRate" class="divWin" style="display:none;">
<div class="close cur"  onclick="javascript:hiddenDiv('addRate')">关闭</div>
            <h1></h1>
			 <p >修改签约比列</p> 
            <table style="width:100%;" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td bgcolor="#f1f8ff" align="right">比例</td>
                <td bgcolor="#FFFFFF">
                        <input type="text" class="on-show" id="signRate" name="signRate"/>&nbsp;<label>%</label>               
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