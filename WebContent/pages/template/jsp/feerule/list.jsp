<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>劳务费信息维护</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
var gloabObj = {page:<c:out value="${page}"/>,rows:<c:out value="${rows}"/>,total:<c:out value="${total}"/>};
function delRuleFee(areaId,paymentType){
	if(confirm("删除数据不可恢复，确定要删除么？")){
		$.ajax({
	         type: "post",
	         url: chgUrl("<%=request.getContextPath() %>/feerule/del.do"),
	         dataType: "html",
	         data: {
				areaId: areaId,
				paymentType: paymentType
	         },
	         success: function(data, textStatus) {
	        	 //alert(data);
	        	if(data){
	        		alert(data);
	        		if(data.indexOf("数据已删")>-1){
	        			document.getElementById("searchBtn").click();
		        	}
	        	}
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
	         	document.getElementById("page").value=pageNumber;
	         	document.getElementById("rows").value=gloabObj.rows;
	            document.getElementById("searchBtn").click();
			}
	  });
	}
});
function getArea(id,elementId,initMessage,selectedValue){
	if(id!=""){
		var obj = {id:id};
		var eleObj = document.getElementById(elementId);
	    eleObj.options.length = 0;
	    eleObj.options.add(new Option(initMessage,""));
		jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/getarea.do",obj,"get","json",function(data){
	    	if(data!=null&&data.length>0){
	    		for(var i = 0 ; i < data.length ;i++){
	    			var obj = data[i];
	    			var option = new Option(obj.province,obj.id);
	    			eleObj.options.add(option);
	    		}
	    		if(selectedValue){
	    			eleObj.value = selectedValue;
	    		}
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
  劳务费设置 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;劳务费信息</span>

     <form id="queryForm" method="post" action="<%=request.getContextPath() %>/feerule/list.do">
     <span class="fr cur" onClick="openWin('<%=request.getContextPath() %>/feerule/edit.do')"><span class="fcr">新建</span></span>	
	   <span class="fr cur">&nbsp;&nbsp;&nbsp;</span>
	   <span class="fr cur" style="margin-right:3px;"><input  type="submit" class="jfxx_btn9" value="查询" id="searchBtn" name="searchBtn"/></span>
	   <span class="fr cur">&nbsp;&nbsp;&nbsp;</span>
	   <span class="fr cur"><label style="font-size: 15px;">缴费类型：</label><select class="zc_city"  style="width:100px;height:28px;" id="paymentType" name="paymentType">
                             <option value="-1">请选择</option>
                             <c:forEach var="pt" items="${paymentTypes}" varStatus="status"> 
                                <option value="${pt.id.dataValue}" <c:if test="${pt.id.dataValue==paymentType}">selected</c:if>>${pt.propChName}</option>
                             </c:forEach>
                        </select>
                        <input type="hidden" id="page" name="page" value="${page}"/>
                         <input type="hidden" id="rows" name="rows" value="${rows}"/>
                        </span>
	   <span class="fr cur" style="margin-right:5px;"><label style="font-size: 15px;">地区：</label><select class="zc_city"  style="width:100px;height:28px;" id="areaId" name="areaId" onchange="getArea(this.value,'cityId','请选择城市')">
                             <option value="">请选择地区</option>
                              <c:forEach var="area" items="${areas}" varStatus="status"> 
                                <option value="${area.id}" <c:if test="${area.id==areaId}">selected</c:if>>${area.province}</option>
                             </c:forEach>
                          
                        </select>&nbsp;<select class="zc_city"  style="width:100px;height:28px;" id="cityId" name="cityId">
                             <option value="">请选择城市</option>
                              <c:forEach var="childarea" items="${childareas}" varStatus="status"> 
                                <option value="${childarea.id}" <c:if test="${childarea.id==cityId}">selected</c:if>>${childarea.province}</option>
                             </c:forEach>
                        </select></span>
	</form>
	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td width="10%">序号</td>
    <td width="15%">缴费类型</td>
    <td width="20%">地区</td>
    <td width="20%">个人缴费劳务费</td>
    <td width="20%">对公缴费劳务费</td>
    <td width="15%">操作</td>
  </tr>
  <c:forEach var="item" items="${ruleList}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.paymentTypeName}</td>
    <td>&nbsp;${item.areaName}</td>
    <td>&nbsp;${item.personalPoundage}</td>
    <td>&nbsp;${item.unitPoundage}</td>
    <td>
	<a class="cur" style="color:#007abd;"  onClick="openWin('<%=request.getContextPath() %>/feerule/edit.do?areaId=${item.areaId}&paymentType=${item.paymentType}')" >修改</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="delRuleFee('${item.areaId}','${item.paymentType}')">删除</a></td>

  </tr>
 </c:forEach>
   
</table>

    </div>
   <div id="pageNav"></div>
  <div class="clear"></div> 
</div>
</div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>