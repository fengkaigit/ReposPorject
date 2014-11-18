<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>物业缴费确认 - 生活助手</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css"></head>
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
															<li class="myapp-item myapp-item-selected fn-clear">
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
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">缴费确认<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" target="_blank" onClick="show('addbills')">缴费账号设置</a>
		  <a style="color:#007abd;float:left;">|</a>
		  <a class="ywjs00" target="_blank" href="jiaofei_jftx.html">账单提醒设置</a>
		  <a style="color:#007abd;float:left;">|</a>
		  <a class="ywjs00" target="_blank" href="jiaofei_jlcx.html">缴费记录查询</a>
		  </span></div> 
        <div class="clear"></div>
      <div class="tx_step2">
        	<span >1、填写表单</span>
            <span class="on">2、确认信息</span>
            <span>3、线上支付</span>
            <span>4、支付成功</span>
          
        </div>
        <div class="clear"></div>
        <div class="xxqr" style="margin-top:0px; position:relative;" >
       <fieldset>
    <legend>缴费信息确认</legend>
    <ul >
  	<li><span>订单编号：</span>2014082201005000000001</li>
    <li><span>收费单位：</span>北京勤蜂易缴电子商务有限公司</li>
    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帐期：</span>2014年8月</li>
    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：</span>123.4元（包含代缴服务费1元）</li>
    <li><span>缴费户号：</span>200206342</li>
	  <li><span>缴费住址：</span>呼和浩特市海东路丽苑小区8号楼201</li>
   </ul>
  </fieldset>


  </div>
 <form method="post" id="form1" action="<%=request.getContextPath() %>/wyf/third.do">
  <div class="zffs">
   
       <fieldset>
    <legend>支付方式</legend>
    
    <ul class="list-bank">
    				    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-icbc">
								<input type="radio" name="bankRadio" value="{'channelCode':'ICBC','channelName':'工商银行','channelType':'20','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'300','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'300','cardTypeCode':'1','activityId':0},'imageUrl':'../images/icbc_1301.png'}" class="jdradio" id="bank_ICBC">
								<label>
									<span class="bank-logo" id="bank-icbc"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-ccb">
								<input type="radio" name="bankRadio" value="{'channelCode':'CCB','channelName':'建设银行','channelType':'10','isRoute':true,'commonCredit':{'agencyCode':'050','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'050','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'300','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'132','cardTypeCode':'1','activityId':0},'imageUrl':'../images/ccb_1301.png'}" class="jdradio" id="bank_CCB">
								<label>
									<span class="bank-logo" id="bank-ccb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-cmb">
								<input type="radio" name="bankRadio" value="{'channelCode':'CMB','channelName':'招商银行','channelType':'20','isRoute':true,'commonCredit':{'agencyCode':'010','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'010','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'153','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'300','cardTypeCode':'1','activityId':0},'bindingCard':[{'id':2305100,'bankNo':'CMB','cardType':2,'valid':'1','agencyCode':'153','cardNoShort':'9141','activityId':0}],'imageUrl':'../images/cmb_1301.png'}" class="jdradio" id="bank_CMB">
								<label>
									<span class="bank-logo" id="bank-cmb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-abc">
								<input type="radio" name="bankRadio" value="{'channelCode':'ABC','channelName':'农业银行','channelType':'20','isRoute':true,'commonCredit':{'agencyCode':'050','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'300','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'132','cardTypeCode':'1','activityId':0},'imageUrl':'../images/abc_1301.png'}" class="jdradio" id="bank_ABC">
								<label>
									<span class="bank-logo" id="bank-abc"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-bcom">
								<input type="radio" name="bankRadio" value="{'channelCode':'BCOM','channelName':'中国交通银行','channelType':'20','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'153','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'132','cardTypeCode':'1','activityId':0},'imageUrl':'../images/bcom_1301.png'}" class="jdradio" id="bank_BCOM">
								<label>
									<span class="bank-logo" id="bank-bcom"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-gdb">
								<input type="radio" name="bankRadio" value="{'channelCode':'GDB','channelName':'广东发展银行','channelType':'10','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'153','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'132','cardTypeCode':'1','activityId':0},'imageUrl':'../images/gdb_1301.png'}" class="jdradio" id="bank_GDB">
								<label>
									<span class="bank-logo" id="bank-gdb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-boc">
								<input type="radio" name="bankRadio" value="{'channelCode':'BOC','channelName':'中国银行','channelType':'10','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'300','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'300','cardTypeCode':'1','activityId':0},'imageUrl':'../images/boc_1301.png'}" class="jdradio" id="bank_BOC">
								<label>
									<span class="bank-logo" id="bank-boc"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-cmbc">
								<input type="radio" name="bankRadio" value="{'channelCode':'CMBC','channelName':'中国民生银行','channelType':'20','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'300','cardTypeCode':'2','activityId':0},'imageUrl':'../images/cmbc_1301.png'}" class="jdradio" id="bank_CMBC">
								<label>
									<span class="bank-logo" id="bank-cmbc"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-hxb">
								<input type="radio" name="bankRadio" value="{'channelCode':'HXB','channelName':'华夏银行','channelType':'20','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'153','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'132','cardTypeCode':'1','activityId':0},'imageUrl':'../images/hxb_1301.png'}" class="jdradio" id="bank_HXB">
								<label>
									<span class="bank-logo" id="bank-hxb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-cib">
								<input type="radio" name="bankRadio" value="{'channelCode':'CIB','channelName':'兴业银行','channelType':'20','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'300','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'132','cardTypeCode':'1','activityId':0},'imageUrl':'../images/cib_1301.png'}" class="jdradio" id="bank_CIB">
								<label>
									<span class="bank-logo" id="bank-cib"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-ceb">
								<input type="radio" name="bankRadio" value="{'channelCode':'CEB','channelName':'中国光大银行','channelType':'10','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'300','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'132','cardTypeCode':'1','activityId':0},'imageUrl':'../images/ceb_1301.png'}" class="jdradio" id="bank_CEB">
								<label>
									<span class="bank-logo" id="bank-ceb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-post">
								<input type="radio" name="bankRadio" value="{'channelCode':'POST','channelName':'邮政储蓄','channelType':'10','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'153','cardTypeCode':'2','activityId':0},'imageUrl':'../images/post_1301.png'}" class="jdradio" id="bank_POST">
								<label>
									<span class="bank-logo" id="bank-post"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-citic" style="display: list-item;">
								<input type="radio" name="bankRadio" value="{'channelCode':'CITIC','channelName':'中信银行','channelType':'10','isRoute':true,'commonCredit':{'agencyCode':'050','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'300','cardTypeCode':'2','activityId':0},'imageUrl':'../images/citic_1301.png'}" class="jdradio" id="bank_CITIC">
								<label>
									<span class="bank-logo" id="bank-citic"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-spdb" style="display: list-item;">
								<input type="radio" name="bankRadio" value="{'channelCode':'SPDB','channelName':'浦东发展银行','channelType':'20','isRoute':true,'commonCredit':{'agencyCode':'147','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'153','cardTypeCode':'2','activityId':0},'quickDebit':{'agencyCode':'132','cardTypeCode':'1','activityId':0},'imageUrl':'../images/spdb_1301.png'}" class="jdradio" id="bank_SPDB">
								<label>
									<span class="bank-logo" id="bank-spdb"></span>
								</label>
															</li>
    					    						
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-bob" style="display: list-item;">
								<input type="radio" name="bankRadio" value="{'channelCode':'BOB','channelName':'北京银行','channelType':'10','isRoute':true,'commonCredit':{'agencyCode':'050','cardTypeCode':'2','activityId':0},'commonDebit':{'agencyCode':'147','cardTypeCode':'1','activityId':0},'quickCredit':{'agencyCode':'153','cardTypeCode':'2','activityId':0},'imageUrl':'../images/bob_1301.png'}" class="jdradio" id="bank_BOB">
								<label>
									<span class="bank-logo" id="bank-bob"></span>
								</label>
															</li>
    					    				                </ul>
    					    				                
  </fieldset>

<div style="margin-top:10px; padding:0px 80px;"><input type="button" class="jfxx_btn3" value="上一步"  onClick="goback()" name="searchBill" >&nbsp;&nbsp;
                                                <input  type="submit" class="jfxx_btn3"  value="下一步" name="searchBill"  ></div>
                                               
  </div>
   </form>
  </div>
 </div>
 
 
		</div><!-- .ui-content -->
		
	</div>
<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>