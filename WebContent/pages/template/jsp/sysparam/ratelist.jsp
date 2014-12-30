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
	 $("#limitMoney").formValidator().inputValidator({min:1,onError:"最高限额不能为空"}).regexValidator({regExp:"^[0-9a-zA-Z,，\.。；;\/、]+$",onError:"属性英文名格式不正确,请重新输入"});
	 $("#rate").formValidator().inputValidator({min:1,onError:"转账费率不能为空"});
});
function getData(id){
	jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/sysparam/rateedit.do",{id:id},"get","json",function(data){
		$("#bankId").val(data.bankId);
		$("#limitMoney").val(data.limitMoney);
		$("#rate").val(data.rate);
		$("#id").val(data.id);
		$("#cityFlag").attr("checked",data.cityFlag);
		$("#peerFlag").attr("checked",data.peerFlag);
		$("#operFlag").val('修改');
	    showDivWin('addRate',400,280,'');
    });
	
}
function add(){
	$("#id").val('');
	$("#bankId").val('');
	$("#limitMoney").val('');
	$("#rate").val('');
	$("#cityFlag").attr("checked",false);
	$("#peerFlag").attr("checked",false);
	$("#operFlag").val('添加');
	showDivWin('addRate',400,280,'');
}
function postHandle(){
	 if($.formValidator.pageIsValid()){
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
    <td>银行名称</td>
    <td>最高限额(元)</td>
     <td>转账费率(%)</td>
      <td>是否同城</td>
     <td>是否同行</td>
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
    <td>&nbsp;${item.bankName}</td>
    <td>&nbsp;${item.limitMoney}</td>
    <td>&nbsp;${item.rate}</td>
    <td>&nbsp;<c:if test="${item.cityFlag==true}">是</c:if><c:if test="${item.cityFlag==false}">否</c:if></td>
    <td>&nbsp;<c:if test="${item.peerFlag==true}">是</c:if><c:if test="${item.peerFlag==false}">否</c:if></td>
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
<div id="addRate" class="divWin" style="display:none;">
<div class="close cur"  onclick="javascript:hiddenDiv('addRate')">关闭</div>
            <h1></h1>
			 <p >添加转账费率</p> 
            <table style="width:100%;" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td width="28%" bgcolor="#f1f8ff" align="right" style="vertical-align: middle;">银行名称</td>
                <td bgcolor="#FFFFFF">
                         <select class="zc_city"  style="width:190px;height:28px;" id="bankId" name="bankId">
                             <option value="">请选择银行</option>
                             <c:forEach var="item" items="${banks}" varStatus="status">
                                <option value="${item.bankCode}" >${item.bankName}</option>
                             </c:forEach>    
                          
                        </select>                
                        </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">最高限额</td>
                <td bgcolor="#FFFFFF">
                        <input type="text" class="on-show" id="limitMoney" name="limitMoney"/>&nbsp;<label>元</label>               
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">转账费率</td>
                <td bgcolor="#FFFFFF">
                <input type="text" class="on-show" id="rate" name="rate"/>&nbsp;<label>%</label>
                </td>
            </tr>
            <tr>
                <td bgcolor="#FFFFFF" align="center" colspan="2">
                    <div class="input_middle"><input type="checkbox" id="cityFlag" name="cityFlag" style="height:15px;line-height:15px;"/><label>是否同城</label>&nbsp;&nbsp;&nbsp;<input type="checkbox" id="peerFlag" name="peerFlag" style="height:15px;line-height:15px;"/><label>是否同行</label></div>
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