<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>账号设置</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<style>
.divWin {
    position:absolute;
    font-size: 12px;
padding:10px;
	width:460px;
	height:380px;
	z-index:999;
	top:0px;
	left:0px;
	border:0px solid #ddd;
	background:#fff;
	display:block;
}
</style>
<script language="javascript">
$(document).ready(function(){
	refreshAreaId(document.getElementById("_parentAreaId"));
	refreshCityId(document.getElementById("_areaId"));
	$.formValidator.initConfig({formID:"form1",alertMessage:true,debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#areaId").formValidator().inputValidator({min:1,onError:"请选择城市"});
	$("#payAddress").formValidator().inputValidator({min:1,max:100,onError:"请输入不多于100个字的详细地址"});
	$("#entId").formValidator().inputValidator({min:0,onError:"请选择公共事业单位"});
	$("#billNumber").formValidator().inputValidator({min:1,max:30,onError:"请输入不多于30个字的缴费号"});
	$("#hoster").formValidator().inputValidator({min:1,max:30,onError:"请输入不多于30个字的户主"});
	$("#groupId").formValidator().inputValidator({min:0,onError:"请选择分组"});
	processJtfk();
	
});
function processJtfk(){
	var pv = document.getElementById("paymentType").value;
	if(pv=="5"){
		document.getElementById("vehicleNumbertr").style.display="";
		document.getElementById("carframeNumbertr").style.display="";
		document.getElementById("engineNumbertr").style.display="";
	}else{
		document.getElementById("vehicleNumbertr").style.display="none";
		document.getElementById("carframeNumbertr").style.display="none";
		document.getElementById("engineNumbertr").style.display="none";
	}
}
</script>
</head>
<body>
<div id="addbills" class="divWin" style="display:block">
<div class="close cur">&nbsp;</div>
            <h1></h1>
			 <p >缴费详细信息设置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（一次设置，便捷使用）</p> 
			 <form method="post" id="form1" action="<%=request.getContextPath() %>/setting/save.do">
            <table width="400" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td width="28%" bgcolor="#f1f8ff" align="right">账单类型: </td>
                <td bgcolor="#FFFFFF">
					 <select class="selectCss"  id="paymentType" name="paymentType" onchange="refreshEntByPayType(this,'<%=request.getContextPath() %>','areaId','entId');processJtfk();" onmousewheel="return false">
				  	<c:forEach var="item" items="${paymentTypes}" varStatus="status">
                      <option value="${item.id.dataValue}" <c:if test="${item.id.dataValue==paymentSetting.paymentType}">selected="selected"</c:if>>${item.propChName}</option>
                    </c:forEach>
				 </select>
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">缴费区域: </td>
                <td bgcolor="#FFFFFF">
                     <select class="selectCss" onchange="refreshCity(this,'<%=request.getContextPath() %>','_areaId');refreshAreaId(this);" id="_parentAreaId" name="_parentAreaId" onmousewheel="return false">
				  	<c:forEach var="item" items="${areaList}" varStatus="status">
                      <option value="${item.id}" <c:if test="${item.id==parent}">selected="selected"</c:if>>${item.province}</option>
                    </c:forEach>
				 </select>
				  <select class="selectCss" id="_areaId" name="_areaId" onmousewheel="return false" onchange="refreshCityId(this);refreshEntByCity(this,'<%=request.getContextPath() %>','paymentType','entId');">
				  	<c:forEach var="item" items="${cityList}" varStatus="status">
                      <option value="${item.id}" <c:if test="${item.id==areaId}">selected="selected"</c:if>>${item.province}</option>
                    </c:forEach>
				  </select>
				  <input type="hidden" id="parentAreaId" name="parentAreaId"/>
				  <input type="hidden" id="areaId" name="areaId" value="${paymentSetting.areaId }"/>
                </td>
            </tr>
          
			<tr>
                <td bgcolor="#f1f8ff" align="right">详细地址：</td>
                <td bgcolor="#FFFFFF">
                  
                     	<input type="text" name="payAddress"  class="billInput" id="payAddress" maxlength="100" value="${paymentSetting.payAddress }">
						<span id="billNoWhere"><a style="font-family:'宋体';color:#FF0000;font-size:12px;" href="javascript:void(0);">缴费时信息核对使用！</a></span>
					
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">公共事业单位<br>/收款单位：</td>
                <td bgcolor="#FFFFFF">
                   <select class="selectCss"  id="entId" name="entId" onmousewheel="return false">
		                <c:forEach var="item" items="${charges}" varStatus="status">
                      		<option value="${item.id}" <c:if test="${item.id==paymentSetting.areaId}">selected="selected"</c:if>>${item.enterpriseName}</option>
                    	</c:forEach>
		                </select>
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">缴费号：</td>
                <td bgcolor="#FFFFFF">
                    <input type="text" class="billInput" id="billNumber" name="billNumber" maxlength="30" value="${paymentSetting.billNumber }">
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">户主：</td>
                <td bgcolor="#FFFFFF">
                    <input type="text" class="billInput" id="hoster" name="hoster" maxlength="30" value="${paymentSetting.hoster}">
                </td>
            </tr>
            <tr id="vehicleNumbertr" style="display: none">
                <td bgcolor="#f1f8ff" align="right">行驶证号：</td>
                <td bgcolor="#FFFFFF">
                    <input type="text" class="billInput" id="vehicleNumber" name="vehicleNumber" maxlength="30" value="${paymentSetting.vehicleNumber}">
                </td>
            </tr>
            <tr id="carframeNumbertr" style="display: none">
                <td bgcolor="#f1f8ff" align="right">车驾号：</td>
                <td bgcolor="#FFFFFF">
                    <input type="text" class="billInput" id="carframeNumber" name="carframeNumber" maxlength="30" value="${paymentSetting.carframeNumber}">
                </td>
            </tr>
            <tr id="engineNumbertr" style="display: none">
                <td bgcolor="#f1f8ff" align="right">发动机号：</td>
                <td bgcolor="#FFFFFF">
                    <input type="text" class="billInput" id="engineNumber" name="engineNumber" maxlength="30" value="${paymentSetting.engineNumber}">
                </td>
            </tr>
            <tr>	
                <td bgcolor="#f1f8ff" align="right">分 组：</td>
                <td bgcolor="#FFFFFF">
					 <select class="selectCss"  id="groupId" name="groupId">
				  	<c:forEach var="item" items="${groups}" varStatus="status">
                      <option value="${item.id.dataValue}" <c:if test="${item.id.dataValue==paymentSetting.groupId}">selected="selected"</c:if>>${item.propChName}</option>
                    </c:forEach>
				 </select>
					
                </td>
            </tr>
            <tr>
                <td bgcolor="#FFFFFF" align="center" colspan="2">
                <input type="hidden" id="id" name="id" value="${paymentSetting.id }"/>
				<input type="submit" class="jfxx_btn3" value="保存" name="searchBill">
				</td>
            </tr>
            </tbody></table>
            </form>
        </div>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>