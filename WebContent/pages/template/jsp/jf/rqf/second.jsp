<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>燃气费缴费确认 - 生活助手</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<script language="javascript">
function checkSubmit(){
	var bankCodes = document.getElementsByName("bankCode");
	var canSubmit = false;
	for(var i=0;i<bankCodes.length;i++){
		if(bankCodes[i].checked==true){
			canSubmit = true;
			break;
		}
	}
	if(!canSubmit){
		alert("请选择支付银行");
		return false;
	}
	return true;
}
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
								<li class="myapp-item fn-clear">
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
															<li class="myapp-item myapp-item-selected  fn-clear">
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
    		
        	  
        </div>
        <div class="clear"></div>
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">缴费确认<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" onClick="quickSetting('<%=request.getContextPath() %>',2)">缴费账号设置</a>
		  <a style="color:#007abd;float:left;">|</a>
		 
		  <a class="ywjs00" target="_blank" href="<%=request.getContextPath() %>/jf/query.do">缴费记录查询</a>
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
  	<li><span>订单编号：</span>${RQF_BILL.billNo}</li>
    <li><span>收费单位：</span>${RQF_BILL.endName}</li>
    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帐期：</span>${RQF_BILL.year}年${RQF_BILL.month}月</li>
    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金额：</span>${RQF_BILL.totalMoney}元（包含代缴服务费${RQF_BILL.poundage}元）</li>
    <li><span>缴费户号：</span>${RQF_BILL.billNumber}</li>
	  <li><span>缴费住址：</span>${RQF_BILL.payAddress}</li>
   </ul>
  </fieldset>


  </div>
 <form method="post" id="form1" onsubmit="return checkSubmit();"  action="<%=request.getContextPath() %>/rqf/third.do">
  <div class="zffs">
   
       <fieldset>
    <legend>支付方式</legend>
    
   <ul class="list-bank">
    				    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-icbc">
								<input type="radio" name="bankCode" value="01020000" class="jdradio" id="bank_ICBC">
								<label>
									<span class="bank-logo" id="bank-icbc"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-ccb">
								<input type="radio" name="bankCode" value="01050000" class="jdradio" id="bank_CCB">
								<label>
									<span class="bank-logo" id="bank-ccb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-cmb">
								<input type="radio" name="bankCode" value="03080000" class="jdradio" id="bank_CMB">
								<label>
									<span class="bank-logo" id="bank-cmb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-abc">
								<input type="radio" name="bankCode" value="01030000" class="jdradio" id="bank_ABC">
								<label>
									<span class="bank-logo" id="bank-abc"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-bcom">
								<input type="radio" name="bankCode" value="03010000" class="jdradio" id="bank_BCOM">
								<label>
									<span class="bank-logo" id="bank-bcom"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-gdb">
								<input type="radio" name="bankCode" value="05785800" class="jdradio" id="bank_GDB">
								<label>
									<span class="bank-logo" id="bank-gdb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-boc">
								<input type="radio" name="bankCode" value="01040000" class="jdradio" id="bank_BOC">
								<label>
									<span class="bank-logo" id="bank-boc"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-cmbc">
								<input type="radio" name="bankCode" value="03050000" class="jdradio" id="bank_CMBC">
								<label>
									<span class="bank-logo" id="bank-cmbc"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-hxb">
								<input type="radio" name="bankCode" value="03040000" class="jdradio" id="bank_HXB">
								<label>
									<span class="bank-logo" id="bank-hxb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-cib">
								<input type="radio" name="bankCode" value="03090000" class="jdradio" id="bank_CIB">
								<label>
									<span class="bank-logo" id="bank-cib"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-ceb">
								<input type="radio" name="bankCode" value="63030000" class="jdradio" id="bank_CEB">
								<label>
									<span class="bank-logo" id="bank-ceb"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-post">
								<input type="radio" name="bankCode" value="01000000" class="jdradio" id="bank_POST">
								<label>
									<span class="bank-logo" id="bank-post"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-citic" style="display: list-item;">
								<input type="radio" name="bankCode" value="03020000" class="jdradio" id="bank_CITIC">
								<label>
									<span class="bank-logo" id="bank-citic"></span>
								</label>
															</li>
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-spdb" style="display: list-item;">
								<input type="radio" name="bankCode" value="03100000" class="jdradio" id="bank_SPDB">
								<label>
									<span class="bank-logo" id="bank-spdb"></span>
								</label>
															</li>
    					    						
    					    						<li onClick="bankSelect(this)" clstag="payment|keycount|bank|c-bob" style="display: list-item;">
								<input type="radio" name="bankCode" value="04031000" class="jdradio" id="bank_BOB">
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