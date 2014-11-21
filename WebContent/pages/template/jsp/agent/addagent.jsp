<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>会员注册</title>
<head>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
$(document).ready(function(){
	$.formValidator.initConfig({formID:"agentForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			$("#errorlist").empty();
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
  
	$("#registAccount").formValidator({onShow:"请输入登录名",onFocus:"登录名至少2个字符,最多20个字符",onCorrect:"该登录名可以注册"}).inputValidator({min:2,max:20,onError:"登录名非法,请确认"});
	$("#registRealName").formValidator({onShow:"请输入名称",onFocus:"名称至少5个字符,最多200个字符",onCorrect:"输入合法"}).inputValidator({min:5,max:200,onError:"名称非法,请确认"});
	<c:if test="${agent.id==null}">
	$("#passwd").formValidator({onShow:"请输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码合法"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不合法,请确认"});
	$("#confirmPassword").formValidator({onShow:"输再次输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码一致"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码密码不合法,请确认"}).compareValidator({desID:"passwd",operateor:"=",onError:"两次密码不一致,请确认"});
	</c:if>
	<c:if test="${agent.id!=null}">
	       $("#hiddenRegistAccount").val($("#registAccount").val());
	       var codeStr = '${agent.areaPath}';
	       var codeStrs = codeStr.split("/");
	      $("#provinc").val(codeStrs[0]);
	      if(codeStrs.length==2)
	         getArea(codeStrs[0],'city','请选择城市',codeStrs[1]);
	      if(codeStrs.length==3){
	    	  getArea(codeStrs[0],'city','请选择城市',codeStrs[1]);
	    	  getArea(codeStrs[1],'area','请选择市区',codeStrs[2]);
	      }
	</c:if>
});
function loginChk(){
	if($("#hiddenRegistAccount").val()!=$("#registAccount").val()){
	$("#registAccount").ajaxValidator({
		    type:"post",
			dataType : "json",
			async : false,
			data: {
				loginCode: function(){
					     return $("#registAccount").val();
					}
	         },
			url : "<%=request.getContextPath() %>/agent/checkreg.do",
			success : function(data){
	            if(!data.result) return true;
	            if(data.result) return false;
				return false;
			},
			buttons: $("#btnadd"),
			error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
			onError : "该登录名不可用，请更换登录名",
			onWait : "正在对登录名进行合法性校验，请稍候..."
		});
	}
	
}
function getArea(id,elementId,initMessage,selectedValue){
	if(id!="-1"){
		var obj = {id:id};
		jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/getarea.do",obj,"get","json",function(data){
	    	if(data!=null&&data.length>0){
	    		var eleObj = document.getElementById(elementId);
	    		eleObj.options.length = 0;
	    		eleObj.options.add(new Option(initMessage,"-1"));
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
	if(area!="-1"){
		areaId.value = area;
		return true;
	}
	if(city!="-1"){
		areaId.value = city;
		return true;
	}
	if(pro!="-1"){
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
<form method="post" action="<%=request.getContextPath() %>/agent/add.do?id=${agent.id}" id="agentForm" onsubmit="return initArea()">
    <div class="zc_zone">
        <div class="zc_title">
            <div class="card_title1">
                <ul>
                <li style="float:left;"><span>添加代理商信息</span></li> 
                 </ul>
    
            </div>
        </div>
        <div class="fm-item1">
            <table width="88%" border="0" align="center" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td height="40" width="100" align="right">登录名：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="hidden" id="hiddenRegistAccount"/><input type="text" maxlength="40" name="registAccount" class="on-show" autocomplete="off" id="registAccount" value="${agent.registAccount}" onblur="loginChk()"/><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="registAccountTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">名称：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="registRealName" class="on-show" autocomplete="off" maxlength="50" id="registRealName" value="${agent.registRealName}"> <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="registRealNameTip" style="width:250px"></div></td>
                    </tr>
                   <tr>
                        <td height="40" align="right">所属省份：</td>
                        <td align="left" colspan="2"> <input type="hidden" name="areaId" id="areaId" value="${agent.areaId}"/><select class="zc_city"  style="width:190px;" id="provinc" name="provinc" onchange="getArea(this.value,'city','请选择城市')">
                             <option value="-1">请选择省份</option>
                            <c:forEach var="area" items="${areas}" varStatus="status"> 
                            <option value="${area.id}">${area.province}</option>
                            </c:forEach>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td height="40" align="right">所属城市：</td>
                        <td align="left" colspan="2"> 
                        <select class="zc_city"  style="width:190px;" id="city" name="city" onchange="getArea(this.value,'area','请选择市区')">
                              <option value="-1">请选择城市</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">所属市区：</td>
                        <td align="left" colspan="2"> 
                  <select class="zc_city"  style="width:190px;" id="area" name="area">
                             <option value="-1">请选择市区</option>
                        </select></td>
                    </tr>
                    <tr style='<c:if test="${agent.id!=null}">display:none;</c:if>' >
                        <td height="40" align="right">密码：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="password" maxlength="32" name="passwd" class="on-show" autocomplete="off" id="passwd"> 
                                       <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="passwdTip" style="width:250px"></div></td>
                    </tr>
                    <tr style='<c:if test="${agent.id!=null}">display:none;</c:if>'>
                        <td height="40" align="right">确认密码：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="password" class="on-show" maxlength="32" name="confirmPassword" autocomplete="off" id="confirmPassword">
                                            <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="confirmPasswordTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">E-mail：</td>
                        <td align="left" colspan="2">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="EMail" class="on-show" autocomplete="off" maxlength="50" id="EMail" value="${agent.EMail}"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                   
                    <tr>
                        <td height="40" align="right">手机：</td>
                        <td align="left" colspan="2">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text"  name="mobile" class="on-show" autocomplete="off" maxlength="11" id="mobile" value="${agent.mobile}"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
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