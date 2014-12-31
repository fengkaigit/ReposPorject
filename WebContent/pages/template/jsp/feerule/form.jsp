<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>劳务费信息维护</title>
<head>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
$(document).ready(function(){
	$.formValidator.initConfig({formID:"ruleForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	
	$("#paymentType").formValidator({onShow:"请选择缴费类型",onFocus:"请选择缴费类型",onCorrect:"选择缴费类型合法"}).inputValidator({min:1,onError:"请选择缴费类型"});
	$("#areaId").formValidator({onShow:"请选择城市",onFocus:"请选择城市",onCorrect:"选择城市合法"}).inputValidator({min:1,onError:"请选择城市"});
	$("#personalPoundage").formValidator({onShow:"请输入个人缴费劳务费",onFocus:"请输入个人缴费劳务费",onCorrect:"个人缴费劳务费合法"}).regexValidator({regExp:"decmal1",dataType:"enum",onError:"请输入合法个人缴费劳务费"});
	$("#unitPoundage").formValidator({onShow:"请输入对公缴费劳务费",onFocus:"请输入对公缴费劳务费",onCorrect:"对公缴费劳务费合法"}).regexValidator({regExp:"decmal1",dataType:"enum",onError:"请输入合法对公缴费劳务费"});
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
<div>
<div class="jf_main">
<form method="post" action="<%=request.getContextPath() %>/feerule/save.do" id="ruleForm" name="ruleForm">
    <div class="zc_zone">
        <div class="zc_title">
            <div class="card_title1">
                <ul>
                <li style="float:left;"><span>劳务费信息维护</span></li> 
                 </ul>
    
            </div>
        </div>
        <div class="fm-item1">
            <table width="88%" border="0" align="center" cellspacing="0" cellpadding="0">
                <tbody>
             
                   <tr>
                        <td height="40" align="right">缴费类型：</td>
                        <td align="left"><select class="zc_city"  style="width:190px;" id="paymentType" name="paymentType" onmousewheel="return false">
                             <option value="">请选择缴费类型</option>
                           <c:forEach var="pt" items="${paymentTypes}" varStatus="status"> 
                                <option value="${pt.id.dataValue}" <c:if test="${pt.id.dataValue==baseRuleFee.paymentType}">selected</c:if>>${pt.propChName}</option>
                             </c:forEach>
                        </select>
                        </td>
                        <td><div id="paymentTypeTip" style="width:250px"></div></td>
                    </tr>
                   <tr>
                        <td height="40" align="right">所属省份：</td>
                        <td align="left" colspan="2"l><select class="zc_city"  style="width:190px;" id="pareaId" name="pareaId" onchange="getArea(this.value,'areaId','请选择城市')" onmousewheel="return false">
                             <option value="">请选择省份</option>
                            <c:forEach var="area" items="${areas}" varStatus="status"> 
                            <option value="${area.id}" <c:if test="${area.id==pareaId}">selected</c:if>>${area.province}</option>
                            </c:forEach>
                        </select>
                        </td>
                        
                    </tr>
                    <tr>
                        <td height="40" align="right">所属城市：</td>
                        <td align="left"> 
                        <select class="zc_city"  style="width:190px;" id="areaId" name="areaId" onmousewheel="return false">
                              <option value="">请选择城市</option>
                               <c:forEach var="area" items="${citys}" varStatus="status"> 
                            <option value="${area.id}" <c:if test="${area.id==baseRuleFee.areaId}">selected</c:if>>${area.province}</option>
                            </c:forEach>
                        </select></td>
                       <td><div id="areaIdTip" style="width:250px"></div></td>
                        
                    </tr>

                    <tr>
                        <td height="40" align="right">个人缴费劳务费：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" id="personalPoundage" name="personalPoundage" class="on-show" autocomplete="off" maxlength="10" value="${baseRuleFee.personalPoundage}"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="personalPoundageTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">对公缴费劳务费：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" id="unitPoundage" name="unitPoundage" class="on-show" autocomplete="off" maxlength="10" value="${baseRuleFee.unitPoundage}"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="unitPoundageTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 87px;" colspan="3"><input id="btnadd" name="btnadd"  type="submit" class="dk_pay1" value="确定"/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 87px;" colspan="3">
                        <div class="onError">${message}</div>
                       </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</form>

        <!--右侧主页面-->
    </div>

</div>   
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>