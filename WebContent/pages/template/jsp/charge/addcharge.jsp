<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>会员注册</title>
<head>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
$(document).ready(function(){
	$.formValidator.initConfig({formID:"chargeForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			$("#errorlist").empty();
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
  
	$("#enterpriseName").formValidator({onShow:"请输入收费单位名称",onFocus:"登录名至少5个字符,最多50个字符",onCorrect:"输入合法"}).inputValidator({min:5,max:50,onError:"名称非法,请确认"});
	$("#provinc").formValidator({onShow:"请选择省份",onFocus:"省份必须选择",onCorrect:"选择合法"}).inputValidator({min:1,onError: "省份未选择,请选择!"});
	$("#city").formValidator({onShow:"请选择城市",onFocus:"城市必须选择",onCorrect:"选择合法"}).inputValidator({min:1,onError: "城市未选择,请选择!"});
	$("#area").formValidator({empty:true,onShow:"请选择市区",onFocus:"请选择市区",onCorrect:"选择合法"}).inputValidator({min:1,onError: "市区未选择,请选择!"});
	$("#payType").formValidator({onShow:"请选择收费类型",onFocus:"收费类型必须选择",onCorrect:"选择合法"}).inputValidator({min:1,onError: "收费类型未选择,请选择!"});
	$("#careNumber").formValidator({onShow:"请输入有效银行账户",onFocus:"名称至少16个字符,最多20个字符",onCorrect:"输入合法"}).inputValidator({min:16,max:20,onError:"有效银行账户非法,请确认"});
	$("#bankId").formValidator({onShow:"请选择所属银行",onCorrect:"选择合法"}).inputValidator({min:1,onError: "银行未选择,请选择!"});
	$("#bankDeposit").formValidator({onShow:"请输入开户行",onFocus:"开户行至少5个字符,最多200个字符",onCorrect:"输入合法"}).inputValidator({min:5,max:200,onError:"开户行非法,请确认"});
	$("#cardNumber").formValidator({onShow:"请输入银行账号",onFocus:"银行账号至少16个字符,最多32个字符",onCorrect:"输入合法"}).inputValidator({min:16,max:32,empty:{leftEmpty:false,rightEmpty:false,emptyError:"账号两边不能有空符号"},onError:"银行账号非法,请确认"});
	$("#cardName").formValidator({onShow:"请输入户名",onFocus:"户名至少2个汉字,最多5个汉字",onCorrect:"输入合法"}).inputValidator({min:4,max:10,onError:"户名非法,请确认"});
	//$("#passwd").formValidator({onShow:"请输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码合法"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不合法,请确认"});
	//$("#confirmPassword").formValidator({onShow:"输再次输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码一致"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码密码不合法,请确认"}).compareValidator({desID:"passwd",operateor:"=",onError:"两次密码不一致,请确认"});
	<c:if test="${charge.id!=null}">
	       var codeStr = '${charge.areaPath}';
	       var codeStrs = codeStr.split("/");
	      $("#provinc").val(codeStrs[0]);
	      getArea(codeStrs[0],'city','请选择城市',codeStrs.length==2||codeStrs.length==3 ? codeStrs[1]:undefined);
	      if(codeStrs.length>=2)
	      getArea(codeStrs[1],'area','请选择市区',codeStrs.length==3 ? codeStrs[2]:undefined);
	      
	</c:if>
});
function getArea(id,elementId,initMessage,selectedValue){
	if(id!=""){
		var obj = {id:id};
		jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/getarea.do",obj,"get","json",function(data){
	    	if(data!=null&&data.length>0){
	    		var eleObj = document.getElementById(elementId);
	    		eleObj.options.length = 0;
	    		eleObj.options.add(new Option(initMessage,""));
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
function initArea(){
	var pro = $("#provinc").val();
	var city = $("#city").val();
	var area = $("#area").val();
	var areaId = document.getElementById("areaId");
	if(area!=""){
		areaId.value = area;
		return true;
	}
	if(city!=""){
		areaId.value = city;
		return true;
	}
	if(pro!=""){
		areaId.value = pro;
		return true;
	}
	return true;
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div>
<div class="jf_main">
<form method="post" action="<%=request.getContextPath() %>/charge/add.do?id=${charge.id}&accId=${bankAcc.id}" id="chargeForm" onsubmit="return initArea()" enctype="multipart/form-data">
    <div class="zc_zone">
        <div class="zc_title">
            <div class="card_title1">
                <ul>
                <li style="float:left;"><span>添加收费单位</span></li> 
                 </ul>
    
            </div>
        </div>
        <div class="fm-item1">
            <table width="88%" border="0" align="center" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td height="40" width="100" align="right">收费单位：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="enterpriseName" class="on-show" autocomplete="off" id="enterpriseName" value="${charge.enterpriseName}" /><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="enterpriseNameTip" style="width:250px"></div></td>
                    </tr>
                   <tr>
                        <td height="40" align="right">所属省份：</td>
                        <td align="left"> <input type="hidden" name="areaId" id="areaId" value="${charge.areaId}"/><select class="zc_city"  style="width:190px;" id="provinc" name="provinc" onchange="getArea(this.value,'city','请选择城市')">
                             <option value="">请选择省份</option>
                            <c:forEach var="area" items="${areas}" varStatus="status"> 
                            <option value="${area.id}">${area.province}</option>
                            </c:forEach>
                        </select>
                        </td>
                        <td><div id="provincTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">所属城市：</td>
                        <td align="left"> 
                        <select class="zc_city"  style="width:190px;" id="city" name="city" onchange="getArea(this.value,'area','请选择市区')">
                              <option value="">请选择城市</option>
                        </select></td>
                        <td><div id="cityTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">所属市区：</td>
                        <td align="left"> 
                  <select class="zc_city"  style="width:190px;" id="area" name="area">
                             <option value="">请选择市区</option>
                        </select></td>
                        <td><div id="areaTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">收费类型：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td> <select class="zc_city"  style="width:190px;" id="payType" name="payType">
                                        <option value="">请选择收费类型</option>
                                         <c:forEach var="cus" items="${cusvalues}" varStatus="status">
                                              <option value="${cus.id.dataValue}" <c:if test="${cus.id.dataValue==charge.payType}">selected</c:if>>${cus.propChName}费</option>
                                         </c:forEach>
                                         </select></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="payTypeTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">示例图片：</td>
                        <td align="left" colspan="2">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="file"  style="width:190px;" name="file" class="on-show" id="file"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                     <tr>
                        <td height="40" align="right">所属银行：</td>
                        <td align="left"> 
                        <select class="zc_city"  style="width:190px;" id="bankId" name="bankId">
                              <option value="">请选择所属银行</option>
                              <c:forEach var="bank" items="${banks}" varStatus="status"> 
                                <option value="${bank.bankCode}" <c:if test="${bank.bankCode==bankAcc.bankId}">selected</c:if>>${bank.bankName}</option>
                              </c:forEach>
                        </select><input type="hidden" name="cardType" value="0"/></td>
                       <td><div id="bankIdTip" style="width:250px"></div></td>
                        
                    </tr>
                     <tr>
                        <td height="40" align="right">开户行：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="bankDeposit" class="on-show" autocomplete="off" id="bankDeposit" value="${bankAcc.bankDeposit}"> <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="bankDepositTip" style="width:250px"></div></td>
                    </tr>
                     <tr>
                        <td height="40" align="right">银行账号：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="cardNumber" class="on-show" autocomplete="off" maxlength="50" id="cardNumber" value="${bankAcc.cardNumber}"> <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="cardNumberTip" style="width:250px"></div></td>
                    </tr>
                     <tr>
                        <td height="40" align="right">银行户名：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="cardName" class="on-show" autocomplete="off" maxlength="50" id="cardName" value="${bankAcc.cardName}"> <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="cardNameTip" style="width:250px"></div></td>
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