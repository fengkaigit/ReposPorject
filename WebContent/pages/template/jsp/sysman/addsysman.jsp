<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>会员注册</title>
<head>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script>
$(document).ready(function(){
	$.formValidator.initConfig({formID:"sysManForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			$("#errorlist").empty();
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	<c:if test="${sysMan.id==null}">
	$("#managerName").formValidator({onShow:"请输入登录名",onFocus:"登录名至少2个字符,最多20个字符",onCorrect:"该登录名可以注册"}).inputValidator({min:2,max:20,onError:"登录名非法,请确认"});
	</c:if>
	$("#managerRealname").formValidator({onShow:"请输入真实姓名",onFocus:"用户名至少2个字符,最多20个字符",onCorrect:"输入合法"}).inputValidator({min:2,max:20,onError:"真实姓名非法,请确认"});
	$("#EMail").formValidator({empty:true,onShow:"请输入邮箱，可以为空哦",onFocus:"邮箱6-100个字符,请确认",onCorrect:"输入合法"}).inputValidator({min:6,max:100,onError:"你输入的邮箱长度非法,请确认"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"你输入的邮箱格式不正确"});
	$("#mobilePhone").formValidator({empty:true,onShow:"请输入你的手机号码，可以为空哦",onFocus:"请输入11位手机号码",onCorrect:"输入合法",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"^[1][0-9]{10}$",onError:"你输入的手机格式不正确"});
	//<c:if test="${sysMan.id==null}">
	//$("#passwd").formValidator({onShow:"请输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码合法"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不合法,请确认"});
	//$("#confirmPassword").formValidator({onShow:"输再次输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码一致"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码密码不合法,请确认"}).compareValidator({desID:"passwd",operateor:"=",onError:"两次密码不一致,请确认"});
	//</c:if>
	<c:if test="${sysMan.id!=null}">
	       $("#hiddenManagerName").val($("#managerName").val());
	</c:if>
});
function loginChk(){
	if($("#hiddenManagerName").val()!=$("#managerName").val()){
	$("#managerName").ajaxValidator({
		    type:"post",
			dataType : "json",
			async : false,
			data: {
				loginCode: function(){
					     return $("#managerName").val();
					}
	         },
			url : "<%=request.getContextPath() %>/sysman/checkreg.do",
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
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div>
<div class="jf_main">
<form method="post" action="<%=request.getContextPath() %>/sysman/add.do?id=${sysMan.id}" id="sysManForm">
    <div class="zc_zone">
        <div class="zc_title">
            <div class="card_title1">
                <ul>
                <li style="float:left;"><span>添加管理员信息</span></li> 
                 </ul>
    
            </div>
        </div>
        <div class="fm-item1">
            <table width="88%" border="0" align="center" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr <c:if test="${sysMan.id!=null}">style="display:none;"</c:if> >
                        <td height="40" width="100" align="right">登录名：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="hidden" id="hiddenManagerName"/><input type="text" maxlength="40" name="managerName" class="on-show" autocomplete="off" id="managerName" onblur="loginChk()"/><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="managerNameTip" style="width:250px"></div></td>
                    </tr>

                   


                    <tr>
                        <td height="40" align="right">真实姓名：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="managerRealname" class="on-show" autocomplete="off" maxlength="50" id="managerRealname" value="${sysMan.managerRealname}"> <span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="managerRealnameTip" style="width:250px"></div></td>
                    </tr>
                    <tr style="display:none;">
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
                   
                    <tr style="display:none;">
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
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="EMail" class="on-show" autocomplete="off" maxlength="50" id="EMail" value="${sysMan.EMail}"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="EMailTip" style="width:250px"></div></td>
                    </tr>
                   
                    <tr>
                        <td height="40" align="right">手机：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text"  name="mobilePhone" class="on-show" autocomplete="off" maxlength="11" id="mobilePhone" value="${sysMan.mobilePhone}"><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td><div id="mobilePhoneTip" style="width:250px"></div></td>
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