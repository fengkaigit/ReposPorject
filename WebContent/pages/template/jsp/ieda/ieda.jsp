<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>意见反馈</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.10.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/aboutUs.css">
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
                                            <td width="126" height="22" align="left"><a href="<%=request.getContextPath() %>/ej/ieda.do">意见反馈</a></td>
                                        </tr>
                                    </tbody></table></td>
                            </tr>
                        </tbody></table></td>
                </tr>
            </tbody></table>
        </div>


    </div>
</div>


        <!--右侧主页面-->
        <div class="znx_r">
            
<div class="tcxx_tt">
    <span class="tcxx_tt_a"><a href="<%=request.getContextPath() %>/ej/about.do">关于e缴365</a> &gt; <span class="tcxx_tt_b">意见反馈</span> </span>
</div>
<div class="pinl">
<div class="tabBlBtn"style="margin-top:10px;clear:both;">
            <input type="radio" onclick="choose_bug_type(1)" id="item_bug_type_propose" value="1" name="item_bug_type" checked="checked"><span onclick="choose_bug_type(1)" style="cursor:pointer" class="leftSpace">&nbsp;改进建议&nbsp;&nbsp;</span>
            <input type="radio" onclick="choose_bug_type(2)" id="item_bug_type_recovery" value="2" name="item_bug_type"><span onclick="choose_bug_type(2)" style="cursor:pointer" class="leftSpace">&nbsp;内容纠错&nbsp;&nbsp;</span>
            <input type="radio" onclick="choose_bug_type(3)" id="item_bug_type_bug" value="3" name="item_bug_type"><span onclick="choose_bug_type(3)" style="cursor:pointer" class="leftSpace">&nbsp;BUG提交&nbsp;&nbsp;</span>
        </div>
        
        <div class="wantItem"style="margin-top:30px;clear:both;">
            <div class="titleItem" id="titleItem" >改进建议：</div>
            <div class="perDataItem" >
                <textarea style="height: 126px; border-color: rgb(214, 214, 214);" onpropertychange="if(value.length&gt;10000)value=value.substring(0,10000)" name="item_bug" class="inputBox text text-empty" id="un-item-bug" data-default=""></textarea>
                  <span class="grey">如果您发现网站有功能或者逻辑性的bug，请在此处向我们报告，我们的工程师会尽快核实并修复bug，不断提升我们的用户体验。
            </div>
            <div class="clear"></div>
        </div>
      
    <div style="display: block;" id="bl_email_div" class="wantItem">
            <div class="titleItem"><span class="red">*</span>邮箱地址：</div>
            <div class="perDataItem"><input type="text" name="email" id="un-email" class="inputBox text-empty text" value="请填写邮件地址便于与我们互动" data-default="请填写邮件地址便于与我们互动"></div>
            <div class="clear"></div>
        </div>
        <div class="jfxx_btns"><input  type="button"class="jfxx_btn3" onClick="openWin('jiaofei_qr.html')" value="提交反馈" name="searchBill"></div>
</div>

        </div>
        <!--右侧主页面-->
    </div>

<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>