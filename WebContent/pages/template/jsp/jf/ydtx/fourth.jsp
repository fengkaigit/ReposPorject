<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>移动缴费支付成功 - 生活助手</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link href="<%=request.getContextPath() %>/css/apps.css" rel="stylesheet" charset="utf-8" media="screen" type="text/css">
<link href="<%=request.getContextPath() %>/css/jiaofei.css" rel="stylesheet" charset="utf-8" media="screen" type="text/css">
<link charset="utf-8" rel="stylesheet" href="<%=request.getContextPath() %>/css/appaside.css" media="all">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/billCenter.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/loginin/loginin.css"></link>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/zhsz.css"></link>


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
									<span class="myapp-item-name">自来水缴费</span>	<span class="my-app-item-hot" title="热门应用">热门应用</span>																</a>
								</li>
								<li class="myapp-item   fn-clear">
								<a seed="myapp-item-1000000048" href="<%=request.getContextPath() %>/df/first.do">
									<span data-id="10048" class="myapp-icon icon-apps24-10048">电费缴费</span>
									<span class="myapp-item-name">电费缴费</span>
																		</a>
															</li>
															<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000048" href="<%=request.getContextPath() %>/rqf/first.do">
									<span data-id="10051" class="myapp-icon icon-apps24-10051">燃气缴费</span>
									<span class="myapp-item-name">燃气费</span>
																										</a>
															</li>
															<li class="myapp-item myapp-item-selected  fn-clear">
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
        	 <a href="/shanghai/shuifei">水费</a>
        	  &gt; <span class="tcxx_tt_b">填写付费信息</span></span>-->
        	  
        </div>
        <div class="clear"></div>
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">支付结果<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" target="_blank" onClick="show('addbills')">缴费账号设置</a>
		  <a style="color:#007abd;float:left;">|</a>
		  <a class="ywjs00" target="_blank" href="jiaofei_jftx.html">账单提醒设置</a>
		  <a style="color:#007abd;float:left;">|</a>
		  <a class="ywjs00" target="_blank" href="jiaofei_jlcx.html">缴税记录查询</a>
		  </span></div> 
        <div class="clear"></div>
      <div class="tx_step4">
        	<span>1、填写表单</span>
            <span>2、确认信息</span>
            <span>3、上线支付</span>
            <span class="on">4、支付成功</span>
  
        </div>
        <div class="clear"></div>
        <div class="xxqr" style="margin-top:0px; position:relative;" >
       <fieldset>
    <legend>支付结果</legend>
    <ul >
	 	<li><span>支付结果：</span><span class="bigfont">成功支付</span></li>
  	<li><span>订单编号：</span>2014082201005000000001</li>
   
    <li><span>订单日期：</span>2014年8月25日</li>
    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：</span>123.4元（包含代缴服务费1元）</li>

	
   </ul>
  </fieldset>


  </div>
  <div class="note1">
  <p>亲，您的缴费支付成功</p>
  </div>
  <div class="mtb10 clearfix" style="width:100%;">
  	<div style="float:left;width:100px;">
    	<span class="fc-blue mt5">查看</span>支付凭证
       </div>
     <div class="jfxx_btns" style="float:right;padding-right:30px; display:inline-block; height:30px;"><input  type="button"class="jfxx_btn3" value="下载到本地" name="searchBill"></div>
  </div>
  <div class="zfcg clear" >
  <table class="tab1"  cellpadding="0" cellspacing="0">
  <tr>
  <td>订单号：2014082201005000000001
  </td>
  <td>流水号：15104710036
  </td>
  </tr>
  </table>
   <div class="div1">
   本次：水费&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账单总金额：123.4元
   </div>
   <table width="90%" border="0" cellspacing="0" cellpadding="0" class="tab2 tab-style">
  <tr bgcolor="#e6edfb">
    <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	水费-账单明细</td>
    </tr>
  <tr bgcolor="#eee">
    <td colspan="4">&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	呼和浩特春华水务自来水公司</td>
    </tr>
  <tr>
    <td width="15%">&nbsp;分户账号</td>
    <td width="30%">&nbsp;200206342</td>
    <td width="15%">&nbsp;缴费地址</td>
    <td width="30%">&nbsp;呼和浩特新城区希望小区19#502</td>
  </tr>
  <tr>
    <td>&nbsp;账期</td>
    <td>&nbsp;2014年8月</td>
    <td>&nbsp;金额</td>
    <td>&nbsp;100元</td>
  </tr>
  <tr>
    <td>&nbsp;分户账号</td>
    <td>&nbsp;200206342</td>
    <td>&nbsp;缴费地址</td>
    <td>&nbsp;呼和浩特新城区希望小区19#602</td>
  </tr>
  <tr>
    <td>&nbsp;账期</td>
    <td>&nbsp;2014年8月</td>
    <td>&nbsp;金额</td>
    <td>&nbsp;23.4元</td>
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
    <td>&nbsp;123.4元</td>
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
    <td>&nbsp;壹佰贰拾叁元肆角整</td>
  </tr>
</table>

<table width="90%" border="0" cellspacing="0" cellpadding="0" class="tab-style tab2">
   <tr bgcolor="#e6edfb">
    <td colspan="6">&nbsp;支付信息（累计支付2张账单，支付总金额：123.4元）</td>
  </tr>
   <tr bgcolor="#eee">
    <td>&nbsp;支付机构</td>
    <td>&nbsp;中国银行</td>
    <td>&nbsp;卡号/账号</td>
    <td>&nbsp;6222 4023 1234 8923</td>
    <td>&nbsp;支付金额</td>
    <td>&nbsp;123.4元</td>
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
<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>