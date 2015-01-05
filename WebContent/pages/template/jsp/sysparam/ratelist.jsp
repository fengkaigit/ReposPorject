<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<style>
.input_middle{
   font-size:13px;
}
.input_middle label{vertical-align: middle;line-height:12px;height:13px;
}
.input_middle input{vertical-align: middle;margin-right:3px;
}
</style>
<script>
var gloabObj = {page:<c:out value="${page}"/>,rows:<c:out value="${rows}"/>,total:<c:out value="${total}"/>};
function delRate(id){
	if(confirm("确实要删除该信息吗?")){
		if(gloabObj.total%gloabObj.rows==1&&gloabObj.page!=1)
			gloabObj.page = gloabObj.page-1;
	   jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/sysparam/delrate.do",{id:id},"post","json",function(data){
		    if(data.result){
		    	alert("信息删除成功");
		        window.location.href = "<%=request.getContextPath() %>/sysparam/ratelist.do?page="+gloabObj.page+"&rows="+gloabObj.rows;
		    }
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
			    window.location.href = "<%=request.getContextPath() %>/sysparam/ratelist.do?page="+pageNumber+"&rows="+gloabObj.rows;
			}
	  });
	}
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
	 $("#bankCode").formValidator().inputValidator({min:1,onError:"支付机构代码不能为空"});
	 $("#mobileRate").formValidator().inputValidator({min:1,onError:"手机端转账费率不能为空"});
	 $("#mobileLimit").formValidator().inputValidator({min:1,onError:"手机端最高限额不能为空"});
	 $("#computerRate").formValidator().inputValidator({min:1,onError:"PC端转账费率不能为空"});
	 $("#computerLimit").formValidator().inputValidator({min:1,onError:"PC端最高限额不能为空"});
});
function getData(id){
	jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/sysparam/rateedit.do",{id:id},"get","json",function(data){
		$("#bankCode").val(data.bankCode);
		$("#bankName").val(data.bankName);
		$("#id").val(data.id);
		$("#mobileRate").val(data.mobileRate);
		$("#computerRate").val(data.computerRate);
		$("#mobileLimit").val(data.mobileLimit);
		$("#computerLimit").val(data.computerLimit);
		$("#operFlag").val('修改');
	    showDivWin('addRate',400,320,'');
    });
	
}
function add(){
	$("#id").val('');
	$("#bankCode").val('');
	$("#bankName").val('');
	$("#mobileRate").val('');
	$("#computerRate").val('');
	$("#mobileLimit").val('');
	$("#computerLimit").val('');
	$("#operFlag").val('添加');
	showDivWin('addRate',400,320,'');
}
function postHandle(){
	 if($.formValidator.pageIsValid()){
	   $("#bankName").val($("select option:selected").text());
	   $('#saveForm').ajaxSubmit(function(data){
		    if(data.result){
		    	alert('转账费率'+$("#operFlag").val()+'成功');
		    	hiddenDiv('addRate');
			    window.location.href = "<%=request.getContextPath() %>/sysparam/ratelist.do?page="+gloabObj.page+"&rows="+gloabObj.rows;
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
  转账费率维护 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;转账费率信息</span>
	
	<span class="fr cur" onClick="add()"><span class="fcr">新建</span></span>	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>支付机构名称</td>
    <td>手机端转账费率(%)</td>
    <td>手机端最高限额(元)</td>
    <td>PC端转账费率(%)</td>
    <td>PC端最高限额(元)</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${rates}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${payOrgs[item.bankCode]}</td>
    <td>&nbsp;${item.mobileRate}</td>
     <td>&nbsp;${item.mobileLimit}</td>
     <td>&nbsp;${item.computerRate}</td>
     <td>&nbsp;${item.computerLimit}</td>
    <td>
	<a class="cur" style="color:#007abd;"  onClick="getData(${item.id})" >修改</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="delRate(${item.id})">删除</a></td>

  </tr>
 </c:forEach>
   
</table>

    </div>
   <div id="pageNav"></div>
  <div class="clear"></div> 
</div>
</div>
<form id="saveForm" method="post" action="<%=request.getContextPath() %>/sysparam/saverate.do">
<input type="hidden" id="operFlag" />
<input type="hidden" id="id" name="id"/>
<input type="hidden" id="bankName" name="bankName"/>
<div id="addRate" class="divWin" style="display:none;">
<div class="close cur"  onclick="javascript:hiddenDiv('addRate')">关闭</div>
            <h1></h1>
			 <p >添加转账费率</p> 
            <table style="width:100%;" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td width="28%" bgcolor="#f1f8ff" align="right" style="vertical-align: middle;">支付机构代码</td>
                <td bgcolor="#FFFFFF">
                          <select class="zc_city"  style="width:190px;height:28px;" id="bankCode" name="bankCode">
                             <option value="">请选择支付机构</option>
                             <c:forEach var="item" items="${payOrgs}" varStatus="status">
                                <option value="${item.key}" >${item.value}</option>
                             </c:forEach>    
                          
                        </select>           
                        </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">手机端转账费率</td>
                <td bgcolor="#FFFFFF">
                <input type="text" class="on-show" id="mobileRate" name="mobileRate"/>&nbsp;<label>%</label>
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">手机端最高限额</td>
                <td bgcolor="#FFFFFF">
                <input type="text" class="on-show" id="mobileLimit" name="mobileLimit"/>&nbsp;<label>元</label>
                </td>
            </tr>
             <tr>
                <td bgcolor="#f1f8ff" align="right">PC端转账费率</td>
                <td bgcolor="#FFFFFF">
                <input type="text" class="on-show" id="computerRate" name="computerRate"/>&nbsp;<label>%</label>
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">PC端最高限额</td>
                <td bgcolor="#FFFFFF">
                <input type="text" class="on-show" id="computerLimit" name="computerLimit"/>&nbsp;<label>元</label>
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