<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>安全保障</title>
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
                                <td height="33" class="line3"><table width="200" border="0" cellspacing="0" cellpadding="0">
                                        <tbody><tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td width="126" height="22" align="left"><a href="<%=request.getContextPath() %>/ej/security.do">安全保障</a></td>
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
    <span class="tcxx_tt_a"><a href="<%=request.getContextPath() %>/ej/about.do">关于e缴365</a> &gt; <span class="tcxx_tt_b">安全保障</span> </span>
</div>
<div class="pinl">

<table width="680" border="0" cellspacing="0" cellpadding="0">
                                        <tbody>

										  <tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td  align="left" class="font16" >资金安全</td>
                                        </tr>
										  <tr>   <td></td>
										  <td class="font12"> &nbsp;&nbsp;&nbsp;&nbsp;1、平台与银行达成合作关系，银行成为用户缴费资金的第三方账户保管银行，银行的保管账户可以对于资金流出进行管理，由银行对账户资金进行落地审查，确保用户的资金安全。
										  </td>
										  </tr>
									
										  <tr>   <td></td>
										  <td class="font12"> &nbsp;&nbsp;&nbsp;&nbsp;2、平台对于用户的缴费资金向第三方的保险公司进行承保，如果资金出现意外损失由保险公司进行赔付。
										  </td>
										  </tr>
									
										    <tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td  align="left" class="font16" >交易安全</td>
                                        </tr>
										  <tr>   <td></td>
										  <td class="font12"> &nbsp;&nbsp;&nbsp;&nbsp;1、系统在线支付都是通过银联、银行网上银行、第三方支付等专有通道进行，光纤专线接通，而且交易过程中都通过专业安全加密机制进行处理，以保证交易的可靠性、安全性。
										  </td>
										  </tr>
									
										  <tr>   <td></td>
										  <td class="font12"> &nbsp;&nbsp;&nbsp;&nbsp;2、对于代缴的方式，线下服务人员并不接触现金、支票，都是和各地公用事业单位的银行帐户直接转账完成缴费支付，确保交易资金安全。
										  </td>
										  </tr>
									
									  <tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td  align="left" class="font16" >信息安全</td>
                                        </tr>
										  <tr>   <td></td>
										  <td class="font12"> &nbsp;&nbsp;&nbsp;&nbsp;1、用户注册后的所有的隐私信息都经过MD5加密处理，防止任何人包括公司员工获取用户信息。公司在任何情况下都绝不会出售、出租或以任何其他形式泄漏您的信息。您的信息按照《e缴365的服务协议》中的规定被严格保护。
										  </td>
										  </tr>
									
										  <tr>   <td></td>
										  <td class="font12"> &nbsp;&nbsp;&nbsp;&nbsp;2、系统内的所有访问都需经过授权，系统内有严格的日志管理和审计功能以保证任何痕迹都可追溯。
										  </td>
										  </tr>
										  
								
									  <tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td  align="left" class="font16" >系统安全</td>
                                        </tr>
										  <tr>   <td></td>
										  <td class="font12">&nbsp;&nbsp;&nbsp;&nbsp;1、平台部署在阿里云（中国最大的云计算基地），其多层防御体系、严格的安全防护措施、安全审计手段、确保了平台轻松应对各种攻击、安全漏洞问题，确保平台运行稳定正常。有效的容灾备份措施确保交易数据安全无虞。
										  </td>
										  </tr>
									
										 
										
                                    </tbody></table>
    
</div>

        </div>
        <!--右侧主页面-->
    </div>

<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>