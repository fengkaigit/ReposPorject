<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>移动缴费 - 生活助手</title>
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
          
    <div coor="default-content" class=" ui-grid-20  ui-grid-right">
      <div style="overflow: hidden;" class="znx_r">

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
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">移动缴费<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" onClick="show('addbills')">缴费号码设置</a>
		
		  <a style="color:#007abd;float:left;">|</a>
		  <a class="ywjs00" target="_blank" href="jiaofei_jftx.html">账单提醒设置</a>
		  <a style="color:#007abd;float:left;">|</a>
		  <a class="ywjs00" target="_blank" href="jiaofei_jlcx.html">缴税记录查询</a>
		  </span></div> 
        <div class="clear"></div>
      <div class="tx_step">
        	<span class="on">1、填写表单</span>
            <span>2、确认信息</span>
            <span>3、上线支付</span>
            <span>4、支付成功</span>
            
        </div>
        <div class="clear"></div>
        <div class="jf_txxx">
        
        	

        	<div class="jf_txxx_left">
        	
            	<form  method="post" id="form1" action="<%=request.getContextPath() %>/ydtx/second.do">

					<input type="hidden" id="searchNumber" value="" name="searchNumber">
					
					<input type="hidden" id="paymentcart" value="0" name="paymentcart">
					
					<input type="hidden" value="" id="webId">
					
					
	                <div style="height:250px;" id="queryBillDiv">
	    	
	    		
				<div id="queryBillChargeDiv">


						<input type="hidden" id="qdid" value="" name="qdid">

						<input type="hidden" id="billSubmitVoOrgCode" value="888883200942900" name="billSubmitVo.orgCode">

						<input type="hidden" id="payProvince" value="%E4%BA%91%E5%8D%97" name="payProvince">

						<input type="hidden" id="payCity" value="%E6%98%86%E6%98%8E" name="payCity">

						<input type="hidden" id="catalogEntryId" value="" name="catalogEntryId">

<!--新缴费流程代码-->

	<div style="margin-top:10px;clear:both;">
		<label style=" margin-right:8px;line-height:35px;">手机号码：</label>
		<input type="hidden" value="1" id="singleBillType" name="billSubmitVo.billType">
		   <input type="text"value="15804719999" class="on-show" id="billSubmitVoBillNo" name="billSubmitVo.billNo" maxlength="10">
		   &nbsp;&nbsp;
           <input name="" type="checkbox" value=""> <span class="font2">备存账号信息</span>
	</div  >
	
	<div style="margin-top:10px;clear:both;">
		<label style=" margin-right:8px;line-height:30px;">确认号码：</label>
		<input type="hidden" value="1" id="singleBillType" name="billSubmitVo.billType">
		   <input type="text"value="15804719999" class="on-show" id="billSubmitVoBillNo" name="billSubmitVo.billNo" maxlength="10">
		   
	</div>
    
    <div style="font-size:14px; width:110px; overflow:hidden; padding-left:0px; float:left;">
				  
				  <span style=" margin-right:8px;line-height:40px;" >充值金额：</span>
				  
	</div>
				  
	<div style="float:left;">
				 
				  
                  <select style="width:85px; margin-left:0px; " class="selectCss" default="2014" onchange="jQuery.shfftBillCharge.billCityChange()" id="billCity" name="billCitySel" onmousewheel="return false">
                  <option id="08" value="08" statuscode="00" statusname="" limit="" paymentcart="">30</option>
                  <option id="09" value="09" statuscode="00" statusname="" limit="" paymentcart="">50</option>
                  <option id="10" value="10" statuscode="00" statusname="" limit="" paymentcart="">100</option>
                  <option id="11" value="11" statuscode="00" statusname="" limit="" paymentcart="">200</option>
                  <option id="12" value="12" statuscode="00" statusname="" limit="" paymentcart="">500</option>
                  </select>
                  </div>元
                  <div style="clear:both;height:1px; overflow:hidden;"></div>
				  

 <div style="margin-bottom: 0px; margin-top:5px;clear:both;">
      <label style="margin-right: 8px;line-height:40px;">
      验证码：</label> 
      <input type="text" style="width:86px;border:1px solid #d5d5d5;height:29px;line-height:29px;  padding:3px;margin-top:7px;color:#666" class="cxbh_s" name="billSubmitVo.verify" maxlength="4" onclick="jQuery.shfftBillCharge.getVerifyCode('validateImg');{value='';this.style.color='#333'}" onblur="if(!value){value=defaultValue;this.style.color='#666'}" value="点此获取验证码">
       <!-- 图片验证码 -->
        <a href="javascript:jQuery.shfftBillCharge.refreshVerifyCode('validateImg');">
        <img onclick="javascript:jQuery.shfftBillCharge.refreshVerifyCode(this.id);" style="vertical-align: middle;margin-left:10px;display: none" title="点击验证码图片更换验证码!" id="validateImg">
        </a>
        <!-- 图片验证码 -->
</div>

	  <div style="border-bottom:1px solid #9D9D9D; width:770px; margin:0px 0px 20px 0px; padding:0px 0px 10px 0px;"> 
	</div>
<div class="jfxx_btns"><input  type="submit" class="jfxx_btn3" value="下一步" name="searchBill"></div>


</div>
	                </div>
					
	              	<!--查询元素块-->

              	</form>
              	
            </div>
            <input type="hidden" value="" id="defaultBillNo">
            <input type="hidden" value="" id="defaultSTYear">
          	<input type="hidden" value="" id="defaultSTMonth">
          	<input type="hidden" value="" id="defaultEDYear">
          	<input type="hidden" value="" id="defaultEDMonth">
          	<input type="hidden" value="" id="defaultOPBillIs">
          	<input type="hidden" value="" id="defaultInFlag">
          
            <!-- 缴费账单样张 -->
            <div style="margin-top:0px; position:relative;" class="jf_txxx_right">
            
                <div style="" id="billDetailExample" class="jf_txxx_right">
                
		           
   <!-- <div id="modelTitle" class="title_n">缴费单据实例</div>-->
		            
		            <div style="position:relative;" class="check_styles">
					
		            
		                   <div id="chargeOrgBill" class="check_picture"><a href="#"><img src="<%=request.getContextPath() %>/images/no_billImage.jpg" id="billChargeImg"></a></div>
		                   
		                   <div style="color:#666;" class="check_pics01"><ul>
						   <li id="limitInfo" style="margin:0px 0px -10px 0px;"></li></ul><div class="clear"></div></div>
		           	       <div class="check_fd_icon"><a href="#" id="billDetailHref" style="display: none;"></a></div>
		           	</div>
                </div>
            </div>
            <!-- 缴费账单样张 -->
            
           <div style="clear:both;"></div>

        </div>
        <div style="clear:both;"></div>
        
        <!-- 缴费提示信息  原idchargeAttention  注释id：chargeAttention2来隐藏信息-->
        <div id="chargeAttention2" style="display:none; padding-top:20px; clear:both;" class="jf_txxx_ts"></div>
		<!-- 缴费提示信息 -->
  </div>
  <span class="icon_futitle">
  <p>  &nbsp;1、因暂未开通与移动通信公司的系统对接，故暂采取人工跑腿代缴方式完成亲的缴费，故在正常水费基础上增加1元代缴者的劳务费，请亲谅解！</p>
  <p>  &nbsp;2、有需要发票的亲，可自行到自来水公司网点柜台打印，或留下地址邮寄给您，但邮寄费需要您来承担。</p></span>

                <div style="clear:both"></div>
         
 
  <!-- ui-prod-newbie -->
  <!-- .content -->
  
  

			</div><!-- .ui-grid -->
			
		</div><!-- .ui-content -->
		
	</div>
<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>