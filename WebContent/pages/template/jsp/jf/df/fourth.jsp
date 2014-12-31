<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>电费缴费支付成功 - 生活助手</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">


</head>
<body>
<%@include file="/pages/template/jsp/common/header.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="ui-content fn-clear">
        <div coor="side" class="ui-grid-5">
			<div id="J_app_sider">
<div class="ui-box   appcenter-list">
					<div title="亲~点击可以收放应用列表" class="ui-box-title fn-clear">
						<div class="ui-box-title-border fn-clear sl-linear">
							<h3>缴费列表</h3>
						</div>
					</div>
					<div class="ui-box-container">
						<ul>
								<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000016" href="<%=request.getContextPath() %>/sf/first.do">
									<span data-id="10016" class="myapp-icon icon-apps24-10016">自来水缴费</span>
									<span class="myapp-item-name">自来水缴费</span>															
									                                   </a>
								</li>
								<li class="myapp-item  myapp-item-selected  fn-clear">
								<a seed="myapp-item-1000000048" href="<%=request.getContextPath() %>/df/first.do">
									<span data-id="10048" class="myapp-icon icon-apps24-10048">电费缴费</span>
									<span class="myapp-item-name">电费缴费</span><span class="my-app-item-hot" title="热门应用">热门应用</span>	
																		</a>
															</li>
															<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000048" href="<%=request.getContextPath() %>/rqf/first.do">
									<span data-id="10051" class="myapp-icon icon-apps24-10051">燃气缴费</span>
									<span class="myapp-item-name">燃气费</span>
																										</a>
															</li>
															<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000056" href="<%=request.getContextPath() %>/ydtx/first.do">
									<span data-id="10056" class="myapp-icon icon-apps24-10056">移动通信</span>
									<span class="myapp-item-name">移动通信</span>
																										</a>
															</li>
															<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000063" href="<%=request.getContextPath() %>/ghf/first.do">
									<span data-id="10063" class="myapp-icon icon-apps24-10063">固话宽带</span>
									<span class="myapp-item-name">固话宽带</span>
																										</a>
															</li>
																<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000086" href="<%=request.getContextPath() %>/jtfk/first.do">
									<span data-id="10073" class="myapp-icon icon-apps24-10073">交通罚款</span>
									<span class="myapp-item-name">交通罚款</span>
																										</a>
															</li>
															<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000108" href="<%=request.getContextPath() %>/yxds/first.do">
									<span data-id="10075" class="myapp-icon icon-apps24-10075">有线电视缴费</span>
									<span class="myapp-item-name">有线电视缴费</span>
																										</a>
															</li>
															<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000113" href="<%=request.getContextPath() %>/wyf/first.do">
									<span data-id="10108" class="myapp-icon icon-apps24-10108">物业缴费</span>
									<span class="myapp-item-name">物业缴费</span>
																										</a>
															</li>
															
															
															
													<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000113" href="<%=request.getContextPath() %>/cnf/first.do">
									<span data-id="10108" class="myapp-icon icon-apps24-10108">采暖费缴费</span>
									<span class="myapp-item-name">采暖费缴费</span>
																										</a>
															</li>
													</ul>
					</div>
				</div>
									 					<div class="ui-box ui-box-follow ui-box-nocontainer appcenter-list more-app">
			<a seed="myapp-more" href="#">
				<span class="ui-box-title fn-clear">
					<span class="ui-box-title-border fn-clear sl-linear">
						<h3>更多应用</h3>
					</span>
				</span>
			</a>
		</div>
	</div><!-- #J_app_sider -->
    </div>      
     <div coor="default-content" class=" ui-grid-20">
      <div style="overflow: hidden;" class="znx_r clear">
    	<div class="tcxx_tt">
    		<input type="hidden" value="0000" id="divId">
    		<input type="hidden" value="" id="isCommunica">
    		<input type="hidden" value="" id="fromCart">
    		<input type="hidden" value="" id="catalogEntryId">

        	<!--<span class="tcxx_tt_a">
        	 <a href="/shanghai/shuifei">电费</a>
        	  &gt; <span class="tcxx_tt_b">填写付费信息</span></span>-->
        	  
        </div>
        <div class="clear"></div>
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">支付结果<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs"  onClick="quickSetting('<%=request.getContextPath() %>',1)">缴费账号设置</a>
		  <a style="color:#007abd;float:left;">|</a>
		  
		  <a class="ywjs00" target="_blank" href="<%=request.getContextPath() %>/jf/query.do">缴费记录查询</a>
		  </span></div> 
        <div class="clear"></div>
      <div class="tx_step4">
        	<span>1、填写表单</span>
            <span>2、确认信息</span>
            <span>3、线上支付</span>
            <span class="on">4、支付成功</span>
  
        </div>
        <div class="clear"></div>
        <div class="xxqr" style="margin-top:0px; position:relative;" >
       <fieldset>
    <legend>支付结果</legend>
    <ul >
	 	<li><span>支付结果：</span><span class="bigfont">成功支付</span></li>
  	<li><span>订单编号：</span>${ELEC_BILL.billNo}</li>
   
    <li><span>订单日期：</span>${ELEC_BILL.billDate}</li>
    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：</span>${ELEC_BILL.totalMoney}元（包含代缴服务费${ELEC_BILL.poundage}元）</li>

	
   </ul>
  </fieldset>


  </div>
  <div class="note1">
  <p>亲，您的缴费支付成功</p>
  </div>
  <div class="mtb10 clearfix" style="width:100%;">
  	<div style="float:left;width:100px;">
    	<span class="fc-blue mt5" style="cursor:pointer" onClick="show('zfpz')">查看支付凭证</span>
       </div>
     <div class="jfxx_btns" style="float:right;padding-right:30px; display:inline-block; height:30px;"><input  type="button" class="jfxx_btn3" value="下载到本地" name="searchBill" onclick="expWord(1,${ELEC_BILL.billId},'<%=request.getContextPath() %>');"></div>
  </div>
  <div class="zfcg clear" id="zfpz" style="display:none;">
  <table class="tab1"  cellpadding="0" cellspacing="0">
  <tr>
  <td>订单号：${ELEC_BILL.billNo}
  </td>
  <td>流水号：${ELEC_BILL.billId}
  </td>
  </tr>
  </table>
   <div class="div1">
   本次：电费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账单总金额：${ELEC_BILL.totalMoney}元
   </div>
   <table width="90%" border="0" cellspacing="0" cellpadding="0" class="tab2 tab-style">
  <tr bgcolor="#e6edfb">
    <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	电费-账单明细</td>
    </tr>
  <tr >
    <td colspan="4">&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	${ELEC_BILL.endName}</td>
    </tr>
  <tr>
    <td width="15%">&nbsp;分户账号</td>
    <td width="30%">&nbsp;${ELEC_BILL.billNumber}</td>
    <td width="15%">&nbsp;缴费地址</td>
    <td width="30%">&nbsp;${ELEC_BILL.payAddress}</td>
  </tr>
  <tr>
    <td>&nbsp;账期</td>
    <td>&nbsp;${ELEC_BILL.year}年${ELEC_BILL.month}月</td>
    <td>&nbsp;金额</td>
    <td>&nbsp;${ELEC_BILL.billMoney}元</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;代缴服务费</td>
    <td>&nbsp;${ELEC_BILL.poundage}元</td>
  </tr>
</table>
<table width="90%" border="0" cellspacing="0" cellpadding="0" class="tab3 tab-style" style="border-top:0px;">
   <tr bgcolor="#e6edfb">
    <td width="70%">&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合计：</td>
    <td>&nbsp;${ELEC_BILL.totalMoney}元</td>
  </tr>
  <tr>
    <td>&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大写金额：</td>
    <td>&nbsp;${ELEC_BILL.moneycn}</td>
  </tr>
</table>

<table width="90%" border="0" cellspacing="0" cellpadding="0" class="tab-style tab2">
   <tr bgcolor="#e6edfb">
    <td colspan="6">&nbsp;支付信息（累计支付1张账单，支付总金额：${ELEC_BILL.totalMoney}元）</td>
  </tr>
   <tr >
    <td>&nbsp;支付机构</td>
    <td>&nbsp;${ELEC_BILL.bankName}</td>
    <td>&nbsp;卡号/账号</td>
    <td>&nbsp;${ELEC_BILL.bankAccount}</td>
    <td>&nbsp;支付金额</td>
    <td>&nbsp;${ELEC_BILL.totalMoney}元</td>
  </tr>
</table>
<div class="note"><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;感谢您使用E缴365生活缴费支付服务，使用中的任何疑问，可拨打客户专线
：400-888-8888 或在线留言，我们第一时间为您服务。
</p>
</div>

  </div>
  
  
  
  
  
  </div>
 </div>
 
 
		</div><!-- .ui-content -->
		
	</div>
<iframe id="downFrame" name="downFrame" src="" style="display:none"></iframe>
<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>