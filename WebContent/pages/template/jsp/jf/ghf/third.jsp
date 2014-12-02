<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>固话宽带缴费线上支付 - 生活助手</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<script language="javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formID:"form1",alertMessage:true,debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#bankAccount").formValidator().inputValidator({min:1,max:30,onError:"请输入合法的支付卡号/支付卡别名"});
	$("#mobile").formValidator().inputValidator({min:4,max:4,onError:"请输入手机号码后四位"}).regexValidator({regExp:"num",dataType:"enum",onError:"请输入手机号码后四位"});
	$("#mobileVerify").formValidator({onShow:"请输入验证码",onFocus:"请输入验证码"}).inputValidator({min:1,max:10,onError:"请输入验证码"});

	
});
</script>
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
								<li class="myapp-item   fn-clear">
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
															<li class="myapp-item myapp-item-selected  fn-clear">
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
    		
        	  
        </div>
        <div class="clear"></div>
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">线上缴费<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" onClick="quickSetting('<%=request.getContextPath() %>',3)">缴费账号设置</a>
		  <a style="color:#007abd;float:left;">|</a>
		 
		  <a class="ywjs00" target="_blank" href="<%=request.getContextPath() %>/jf/query.do">缴费记录查询</a>
		  </span></div> 
        <div class="clear"></div>
      <div class="tx_step3">
        	<span >1、填写表单</span>
            <span>2、确认信息</span>
            <span class="on">3、线上支付</span>
            <span>4、支付成功</span>
   
        </div>
        <div class="clear"></div>
        <div class="xxqr" style="margin-top:0px; position:relative;" >
       <fieldset>
    <legend>缴费信息确认</legend>
   <ul >
  	<li><span>订单编号：</span>${GHF_BILL.billNo}</li>
    <li><span>收费单位：</span>${GHF_BILL.endName}</li>
    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：</span>${GHF_BILL.billMoney+GHF_BILL.poundage}元（包含代缴服务费${GHF_BILL.poundage}元）</li>
   </ul>
  </fieldset>


  </div>
  
  
  
  
   <form method="post" id="form1" action="<%=request.getContextPath() %>/ghf/fourth.do">
  <div class="zffs">
      
   <fieldset>
    <legend>银行信息</legend>
    
    	<div>
		<label style=" margin-left:8px;line-height:30px;"> 支付卡号/支付卡别名：</label>
		   <input type="text" value="" class="on-show" id="bankAccount" name="bankAccount" maxlength="30">
	</div>
     
	<div style="margin-top:10px;clear:both;">
		<label style=" margin-left:42px;line-height:30px;">手机号码后四位：</label>
		   <input type="text" value="" class="on-show" id="mobile" name="mobile" maxlength="4">
	</div>
	
	<div style="margin-top:10px;clear:both;">
		<label style=" margin-left:98px;line-height:30px;">验证码：</label>
		   <input type="text" value="" class="on-show" id="mobileVerify" name="mobileVerify" maxlength="10">
	</div>
	
	<div style="margin-top:10px;padding:0px 0px 0px 85px;">
						<label>备忘信息：</label>
		                <select class="selectCss" default="" onChange="" id="billOrg" name="billOrgSel" onmousewheel="return false"><option id="888883200942900" value="888883200942900">自定义</option></select>
					</div>
	<div style="margin-top:10px;clear:both;">
	
		<label style=" margin-left:98px;line-height:30px;">请输入：</label>
		   <input type="text" value="" class="on-show" id="remark" name="remark" maxlength="30">
	</div>
	
  </fieldset>

<div style="margin-top:10px; padding:0px 120px;"><input  type="submit" class="jfxx_btn3" value="确认" name="searchBill" >&nbsp;&nbsp;
<input  type="button" class="jfxx_btn3" value="返回重选" onClick="goback()" name="searchBill"  ></div>

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