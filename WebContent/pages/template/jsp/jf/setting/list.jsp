<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>账号设置</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
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
	$("#entId").formValidator().inputValidator({min:0,onError:"请选择公用事业单位"});
	$("#billNumber").formValidator().inputValidator({min:1,max:30,onError:"请输入用户编号"});
	$("#billMoney").formValidator().regexValidator({regExp:"decmal1",dataType:"enum",onError:"请输入合法缴费金额"});
	$("#poundage").formValidator().regexValidator({regExp:"decmal1",dataType:"enum",onError:"代缴劳务费金额非法"});

	
});
function addSetting(){
	var iWidth =480;    
    var iHeight = 432;  
    var iTop = (window.screen.height-iHeight)/2;
    var iLeft = (window.screen.width-iWidth)/2;
	window.showModalDialog("<%=request.getContextPath() %>/setting/editframe.do","","dialogLeft="+iLeft+"px;dialogTop="+iTop+"px;dialogHeight="+iHeight+"px;dialogWidth="+iWidth+"px;status=no;scroll=no;resizable=no;help=no");
	window.location.href="<%=request.getContextPath() %>/setting/list.do";
}
function edit(id){
	var iWidth =480;    
    var iHeight = 432;  
    var iTop = (window.screen.height-iHeight)/2;
    var iLeft = (window.screen.width-iWidth)/2;
	window.showModalDialog("<%=request.getContextPath() %>/setting/editframe.do?id="+id,"","dialogLeft="+iLeft+"px;dialogTop="+iTop+"px;dialogHeight="+iHeight+"px;dialogWidth="+iWidth+"px;status=no;scroll=no;resizable=no;help=no");
	window.location.href="<%=request.getContextPath() %>/setting/list.do";
}
function del(id){
	if(confirm("删除数据不可恢复，确定要删除么？")){
		$.ajax({
	         type: "post",
	         url: chgUrl("<%=request.getContextPath() %>/setting/del.do"),
	         dataType: "html",
	         data: {
				id: id
	         },
	         success: function(data, textStatus) {
	        	 //alert(data);
	        	if(data){
	        		alert(data);
	        		if(data.indexOf("数据已删")>-1){
	        			window.location.href="<%=request.getContextPath() %>/setting/list.do";
		        	}
	        	}
	         }
	     });
		
	}
}
function jf(settingId,paymentType){
	var url="";
	var site="<%=request.getContextPath() %>";
	if(paymentType==0){
		url = "/sf/first.do";
	}
	if(paymentType==1){
		url = "/df/first.do";
	}
	if(paymentType==2){
		url = "/rqf/first.do";
	}
	if(paymentType==3){
		url = "/ghf/first.do";
	}
	if(paymentType==4){
		url = "/ydtx/first.do";
	}
	if(paymentType==5){
		url = "/jtfk/first.do";
	}
	if(paymentType==6){
		url = "/wyf/first.do";
	}
	if(paymentType==7){
		url = "/yxds/first.do";
	}
	if(paymentType==8){
		url = "/cnf/first.do";
	}
	if(url==""){
		return;
	}
	url=site+url;
	window.location.href=url+"?settingId="+settingId;
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/header.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  缴费账号设置 &nbsp;&nbsp;(&nbsp;一次设置，便捷使用&nbsp;)
  </div>
<div class="jfzh-con">
	 
   
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;缴费详细设置</span>
	
	<span class="fr cur" onClick="addSetting();"><span class="fcr">新建帐户&nbsp;&nbsp;&nbsp;&nbsp;</span></span>	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;text-align: center" align="center">
  <tr class="add">
    <td style="text-align: center;width: 4%">序号</td>
    <td style="text-align: center;width: 6%">组名</td>
    <td style="text-align: center;width: 8%">地区</td>
    <td style="text-align: center;width: 8%">缴费项目</td>
    <td style="text-align: center;width: 20%">收款单位</td>
    <td style="text-align: center;width: 10%">缴费号码</td>
    <td style="text-align: center;width: 8%">户主</td>
    <td style="text-align: center;width: 21%">住址</td>
    <td style="text-align: center;width: 15%">操作</td>
  </tr>
  <c:forEach var="item" items="${paymentSettings}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
     <td style="text-align: center">${status.index+1}</td>
     <td style="text-align: center">${item.groupName}</td>
     <td style="text-align: center">${item.areaName}</td>
     <td style="text-align: center">${item.paymentTypeName}</td>
     <td style="text-align: left">${item.entName}</td>
     <td style="text-align: center">${item.billNumber}</td>
     <td style="text-align: center">${item.hoster}</td>
     <td style="text-align: left">${item.payAddress}</td>
     <td style="text-align: center">
     <a style="color:#007abd;" href="javascript:edit('${item.id}');">修改</a>&nbsp;&nbsp;
	<a href="javascript:jf('${item.id}','${item.paymentType}');">缴费</a>&nbsp;&nbsp;
	<a href="javascript:del('${item.id}');">删除</a>
     </td>
     </tr>
    </c:forEach>  
</table>

    </div>
    
</div>

<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>