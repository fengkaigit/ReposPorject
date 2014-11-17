<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>水费缴费 - 生活助手</title>
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
								<li class="myapp-item  myapp-item-selected fn-clear">
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
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">水费缴费<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" target="_blank" onClick="show('addbills')">水费账号设置</a>
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
        
        		<div style="border-bottom:1px solid #9D9D9D; width:770px; margin:0px 0px 10px 0px; padding:10px 0px 10px 0px;"> 
				  <div style="font-size:14px; width:85px; overflow:hidden; padding-left:6px; float:left;"><span style="display:block;padding-top:1px;"> 所在城市：</span></div>
				  <div style="float:left;">
				  <select style="margin-left:24px;width:85px;" class="selectCss" default="内蒙古" onchange="jQuery.shfftBillCharge.billAreaChange(this,'billCity')" id="billArea" name="billAreaSel" onmousewheel="return false">
				  <option id="内蒙古" value="内蒙古" statuscode="00" statusname="" limit="" paymentcart="">内蒙古</option>
				  <option id="浙江" value="浙江" statuscode="00" statusname="" limit="" paymentcart="">浙江</option>
				  <option id="云南" value="云南" statuscode="00" statusname="" limit="" paymentcart="">云南</option>
				  <option id="江西" value="江西" statuscode="00" statusname="" limit="" paymentcart="">江西</option>
				  <option id="河南" value="河南" statuscode="00" statusname="" limit="" paymentcart="">河南</option>
				  <option id="北京" value="北京" statuscode="00" statusname="" limit="" paymentcart="">北京</option>
				  <option id="辽宁" value="辽宁" statuscode="00" statusname="" limit="" paymentcart="">辽宁</option>
				  <option id="江苏" value="江苏" statuscode="00" statusname="" limit="" paymentcart="">江苏</option>
				  <option id="山东" value="山东" statuscode="00" statusname="" limit="" paymentcart="">山东</option>
				  <option id="黑龙江" value="黑龙江" statuscode="00" statusname="" limit="" paymentcart="">黑龙江</option>
				  <option id="安徽" value="安徽" statuscode="00" statusname="" limit="" paymentcart="">安徽</option>
				  <option value="其他">其他</option></select>
                  
				  <select style="width:85px; margin-left:8px; " class="selectCss" default="内蒙古" onchange="jQuery.shfftBillCharge.billCityChange()" id="billCity" name="billCitySel" onmousewheel="return false">
				  <option id="呼和浩特" value="呼和浩特" statuscode="00" statusname="" limit="" paymentcart="">呼和浩特</option>
				  <option value="包头">包头</option>
				  <option value="乌兰察布">乌兰察布</option>
				  <option value="鄂热多斯">鄂热多斯</option>
				  <option value="赤峰">赤峰</option>
				  <option value="通辽">通辽</option>
				  <option value="乌海">乌海</option>
				  <option value="临河">临河</option></select>
                  </div>
                  <div style="clear:both;height:1px; overflow:hidden;"></div>
				</div>


        	<div class="jf_txxx_left">
        	
            	<form method="post" id="form1" action="<%=request.getContextPath() %>/sf/second.do">

					<input type="hidden" id="searchNumber" value="" name="searchNumber">
					
					<input type="hidden" id="paymentcart" value="0" name="paymentcart">
					
					<input type="hidden" value="" id="webId">
					
					<!--<div>
						<label>付费项目：</label>
						<select style="width:65px;" class="selectCss" default="0001" onchange="jQuery.shfftBillCharge.billTypeChange()" id="billType" name="billTypeSel" onmousewheel="return false"><option id="0001" value="0001" statuscode="00" statusname="" limit="" paymentcart="">水费</option></select>
						<select class="selectCss" default="" onchange="jQuery.shfftBillCharge.billEntryTypeChange()" style="display:none;margin-left:10px;padding-left:0px;" id="billEntryType" name="billEntrySel" onmousewheel="return false"></select>
					</div>-->
					
					<div style="padding:0px 0px 10px 0px;">
						<label>公用事业单位：</label>
		                <select class="selectCss" default="" onchange="jQuery.shfftBillCharge.billOrgChange(this,'queryBillChargeMode')" id="billOrg" name="billOrgSel" onmousewheel="return false"><option id="888883200942900" value="888883200942900" statuscode="00" statusname="" limit="" paymentcart="0">呼和浩特市春华水务自来水公司</option></select>
					</div>
					
					<!--<div style="border-bottom: 1px solid rgb(221, 221, 221); height: 0px; margin-bottom: 0px;" id="queryBillClear"></div>
	                
	                查询元素块-->
	                <div style="height:250px;" id="queryBillDiv">
	    		     <!-- <div style="margin-bottom:3px;" id="queryBillChargeMode">

					<input type="hidden" id="billOrgId" value="888883200942900" name="billOrgId">

					<input type="hidden" id="catalogId" value="0001" name="catalogId">

				    <label style=" margin-right:4px;">付费方式：</label>
				
					<input type="radio" checked="checked" name="chargeType" class="dxbtn" value="search" onclick="jQuery.shfftBillCharge.billChargeModeChange(this.value)"><span class="font2">用户号</span>
               
                   </div>-->
	    		
				<div id="queryBillChargeDiv">


						<input type="hidden" id="qdid" value="" name="qdid">

						<input type="hidden" id="billSubmitVoOrgCode" value="888883200942900" name="billSubmitVo.orgCode">

						<input type="hidden" id="payProvince" value="%E4%BA%91%E5%8D%97" name="payProvince">

						<input type="hidden" id="payCity" value="%E6%98%86%E6%98%8E" name="payCity">

						<input type="hidden" id="catalogEntryId" value="" name="catalogEntryId">

<!--新缴费流程代码-->

	<div>
		<label style=" margin-right:8px;line-height:30px;">用户编号：</label>
		<input type="hidden" value="1" id="singleBillType" name="billSubmitVo.billType">
		   <input type="text"value="200206342" class="on-show" id="billSubmitVoBillNo" name="billSubmitVo.billNo" maxlength="10">
		   &nbsp;&nbsp;
           <input name="" type="checkbox" value=""> <span class="font2">备存账号信息</span>
	</div>
    
    <div style="border-bottom:1px solid #9D9D9D; width:770px; margin:0px 0px 20px 0px; padding:0px 0px 10px 0px;"> 
				  <div style="font-size:14px; width:75px; overflow:hidden; padding-left:6px; float:left;">
				  
				  <span style="display:block;padding-top:5px;line-height:30px;" >帐期：</span></div>
				  
				  <div style="float:left;">
				  <select style="margin-left:28px;width:85px;" class="selectCss" default="2014" onchange="jQuery.shfftBillCharge.billAreaChange(this,'billCity')" id="billArea" name="billAreaSel" onmousewheel="return false"><option id="2014" value="2014" statuscode="00" statusname="" limit="" paymentcart="">2014</option></select>
                  <select style="width:85px; margin-left:0px; " class="selectCss" default="2014" onchange="jQuery.shfftBillCharge.billCityChange()" id="billCity" name="billCitySel" onmousewheel="return false">
                  <option id="08" value="08" statuscode="00" statusname="" limit="" paymentcart="">08</option>
                  <option id="09" value="09" statuscode="00" statusname="" limit="" paymentcart="">09</option>
                  <option id="10" value="10" statuscode="00" statusname="" limit="" paymentcart="">10</option>
                  <option id="11" value="11" statuscode="00" statusname="" limit="" paymentcart="">11</option>
                  <option id="12" value="12" statuscode="00" statusname="" limit="" paymentcart="">12</option>
                  </select>
                  </div>
                  <div style="clear:both;height:1px; overflow:hidden;"></div>
	</div>
 
 <!--star -->
 
 	<div>
		<label style=" margin-right:8px;line-height:30px;"> 缴费金额：</label>
		   <input type="text"value="122.4" class="on-show" id="billSubmitVoBillNo" name="billSubmitVo.billNo" maxlength="10">
	</div>
     
	<div style="margin-top:10px;clear:both;">
		<label style=" margin-right:8px;line-height:30px;">代缴劳务费：</label>
		   <input type="text"value="1" class="on-show" id="billSubmitVoBillNo" name="billSubmitVo.billNo" maxlength="10">
	</div>
	
 <!-- end -->
<div style="margin-left:78px;line-height:20px;height:20px;display:none;clear:both;" class="onShow" id="billSubmitVoBillNoTip">请输入查询编号</div>
	

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
<div class="jfxx_btns"><input  type="submit" class="jfxx_btn3"  value="下一步" name="searchBill"></div>


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
  <p>  &nbsp;1、因暂未开通与自来水公司的系统对接，故暂采取人工跑腿代缴方式完成亲的缴费，故在正常水费基础上增加1元代缴者的劳务费，请亲谅解！</p>
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