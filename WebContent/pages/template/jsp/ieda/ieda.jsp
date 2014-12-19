<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>评论区</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/aboutUs.css">
<script>
var keywordtitle="请填写邮件地址便于与我们互动";
function SearchFocus(obj){
	if(obj.value==keywordtitle){
		obj.value="";
		
	}
}
function SearchBlur(obj){
	if(obj.value==""){
		obj.value=keywordtitle;
		
	}
}
function postHandle(){
	var userId = '${sessionScope.USER.id}';
	if(userId == ''){
		alert('您还没有登录，请登录后再评论');
		return false;
	}
	if($("#userIdea").val() ==''){
		alert("请输入评论内容");
		return false;
	}
	 $('#ideaForm').ajaxSubmit(function(data){
		    if(data.result){
		    	alert('评论成功！谢谢您的参与，我们会尽快给您回复。');
		    	window.location.href = "<%=request.getContextPath() %>/ej/ieda.do";
		    }
	 });
}
</script>
</head>
<body class="index">
<%@include file="/pages/template/jsp/common/header.jsp"%>
<div class="jf_main clearfix">
        <!--左侧菜单导入-->
<!--关于我们 左侧菜单-->
<div class="jf_lefts">
    <div class="jfztc_nav">
        <div class="left_tt">
            <a href="<%=request.getContextPath() %>/ej/about.do"><span style="line-height: 35px;">关于e缴365</span></a>
        </div>
        <div class="left_lists">
            <table width="193" border="0" bgcolor="#D1E6ED" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td valign="top" height="200" bgcolor="#FFFFFF"><table width="200" border="0" align="center" cellspacing="0" cellpadding="0">
                            <tbody>
                            <!--
                            <tr>
                                <td height="33" class="line2" id="aboutUs"><table width="200" border="0" cellspacing="0" cellpadding="0">
                                        <tbody><tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td width="126" height="22" align="left"><a href="#">关于我们</a></td>
                                        </tr>
                                    </tbody></table></td>
                            </tr>
                            -->
                            <tr>
                                <td height="33" class="line3" id="aboutUs"><table width="200" border="0" cellspacing="0" cellpadding="0">
                                        <tbody><tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td width="126" height="22" align="left"><a href="<%=request.getContextPath() %>/ej/ieda.do">评论区</a></td>
                                        </tr>
                                    </tbody></table></td>
                            </tr>
                        </tbody></table></td>
                </tr>
            </tbody></table>
        </div>


    </div>
</div>
<form id="ideaForm" action="<%=request.getContextPath() %>/ej/saveFeed.do" method="post">
        <!--右侧主页面-->
        <div class="znx_r">
            
<div class="tcxx_tt">
    <span class="tcxx_tt_a"><a href="<%=request.getContextPath() %>/ej/about.do">关于e缴365</a> &gt; <span class="tcxx_tt_b">评论区</span> </span>
</div>
<div class="pinl">


<div class="tabBlBtn"style="margin-top:10px;clear:both;">
            <input type="radio"  id="propose" value="1" name="backType" checked="checked"><span  style="cursor:pointer" class="leftSpace">&nbsp;改进建议&nbsp;&nbsp;</span>
            <input type="radio"  id="recovery" value="2" name="backType"><span  style="cursor:pointer" class="leftSpace">&nbsp;内容纠错&nbsp;&nbsp;</span>
            <input type="radio"  id="bug" value="3" name="backType"><span  style="cursor:pointer" class="leftSpace">&nbsp;BUG提交&nbsp;&nbsp;</span>
        </div>
        
        <div class="wantItem" style="margin-top:30px;clear:both;">
            <div class="titleItem" id="titleItem" >改进建议：</div>
            <div class="perDataItem" >
                <textarea style="height: 126px; border-color: rgb(214, 214, 214);" onpropertychange="if(value.length&gt;10000)value=value.substring(0,10000)" name="userIdea" class="inputBox text text-empty" id="userIdea" data-default=""></textarea>
                  <span class="grey">如果您发现网站有功能或者逻辑性的bug，请在此处向我们报告，我们的工程师会尽快核实并修复bug，不断提升我们的用户体验。
            </div>
            <div class="clear"></div>
        </div>
      
        <div  id="bl_email_div" class="wantItem">
            <div class="titleItem"><span class="red">*</span>邮箱地址：</div>
            <div class="perDataItem"><input type="text" name="eMail" id="eMail" class="inputBox text-empty text" style="width:300px;" value="请填写邮件地址便于与我们互动" data-default="请填写邮件地址便于与我们互动" onfocus="SearchFocus(this)" onblur="SearchBlur(this)"></div>
            <div class="clear"></div>
        </div>
        <div class="jfxx_btns"><input  type="button" class="jfxx_btn3" onClick="postHandle()" value="提交反馈" name="searchBill"></div>
        
   </div>

        </div>
        </form>
        <!--右侧主页面-->
    <%@include file="/pages/template/jsp/common/about.jsp"%> 
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
    </div>


<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>