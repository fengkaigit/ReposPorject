<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>移动缴费 - 生活助手</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">

<script type="text/javascript">
var tabId=0;
$(function(){
	

	var x=10;
var y=20;
$("a.tooltip").mouseover(function(e){
this.myTitle=this.title;
this.title="";
var tooltip="<div id='tooltip'>"+this.myTitle+"</div>";   //创建DIV元素
$("#link").append(tooltip);  //追加到文档中
$("#tooltip").css({"top": (e.pageY+y) + "px","left": (e.pageX+x) + "px"}).show(); //设置X  Y坐标， 并且显示
}).mouseout(function(){
this.title=this.myTitle;
$("#tooltip").remove();    //移除
}).mousemove(function(e){
$("#tooltip").css({"top": (e.pageY+y) + "px","left": (e.pageX+x) + "px"});
})
	
});
function checkform(){
	var mobiles = document.getElementsByName("mobiles");
	var reg =/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;	
	for(var i=0;i<mobiles.length;i++){
		var mobile = mobiles[i];
		if(mobile.value==""||mobile.value==null){
			  alert('请输入手机号码');
			  mobile.focus();
			  return false;
	    }else if (!mobile.value.match(reg)){
				alert('手机格式不对，请重新输入');
				mobile.focus();
				return false;
		}
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
          
     <div coor="default-content" class=" ui-grid-20  ui-grid-right">
      <div  class="znx_r">
    	<div class="tcxx_tt">

        	<!--<span class="tcxx_tt_a">
        	 <a href="/shanghai/shuifei">水费</a>
        	  &gt; <span class="tcxx_tt_b">填写付费信息</span></span>-->
        	  
        </div>
        <div class="clear"></div>
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">移动缴费<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" onClick="quickSetting('<%=request.getContextPath() %>',4)">缴费号码设置</a>
		
		  <a style="color:#007abd;float:left;">|</a>
		 
		  <a class="ywjs00" target="_blank" href="<%=request.getContextPath() %>/jf/query.do">缴费记录查询</a>
		  </span></div> 
        <div class="clear"></div>
      <div class="tx_step">
        	<span class="on">1、填写表单</span>
            <span>2、确认信息</span>
            <span>3、线上支付</span>
            <span>4、支付成功</span>
            
        </div>
        <div class="clear"></div>
        <div class="jf_txxx bby clearfix pb10">
        
        	<div class="jf_txxx_left">
            	<form  method="post" id="form1" onsubmit="return checkform();" action="<%=request.getContextPath() %>/ydtx/second.do">				
	                <div style="height:auto;" id="queryBillDiv">
	    					<div id="queryBillChargeDiv" class="clearfix">

<!--新缴费流程代码-->
	<div id="defaultTel" style="height:auto;"></div>
    <div id="items" style="height:auto;"></div>
    
    	<div style="margin-top:10px;clear:both;" id="add-btn-wrap">
		<a class="add"  href="javascript:void(0)" onClick="addsj(tabId,'items')">添加号码<span class="add-icon"></span></a>
		   <input id="bczh" name="bczh" type="checkbox" checked="checked"> <span class="font2 pl4">备存账号信息</span>
	</div>			  
    
     <input type="hidden" id="poundage" name="poundage" value="${poundage}"/>
     <input type="hidden" id="paymentType" name="paymentType" value="${paymentType}"/>   
    <input  type="submit" class="jfxx_btn3" style=" margin-left:100px; margin-top:20px;" value="下一步" name="searchBill">
</div>
	                </div>
					
	              	<!--查询元素块-->
					
              	</form>
             
            </div>
          
            <!-- 缴费账单样张 -->
            <div style="margin-top:0px; position:relative;" class="jf_txxx_right">
            
                <div style="" id="billDetailExample" class="jf_txxx_right">
                
		           
   <!-- <div id="modelTitle" class="title_n">缴费单据实例</div>-->
		            
		            <div style="position:relative;" class="check_styles">
					
		            
		                   <div id="chargeOrgBill" class="check_picture"><a href="javascript:void(0);"><img src="<%=request.getContextPath() %>/images/no_billImage.jpg" id="billChargeImg"></a></div>
		                   
		                   <div style="color:#666;" class="check_pics01"><ul>
						   <li id="limitInfo" style="margin:0px 0px -10px 0px;"></li></ul><div class="clear"></div></div>
		           	       <div class="check_fd_icon"><a href="#" id="billDetailHref" style="display: none;"></a></div>
		           	</div>
                </div>
            </div>

        </div>
  </div>
  <span class="icon_futitle">
  <p>  &nbsp;1、因暂未开通与移动通信公司的系统对接，故暂采取人工跑腿代缴方式完成亲的缴费，故在正常水费基础上增加${poundage}元代缴者的劳务费，请亲谅解！</p>
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
<script src="js/funs.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	addsj(1000,'defaultTel');
	try{
		document.getElementById("telnum_1000").value="${billNumber}";
	}catch(err){}
	registerMobilePhone('<%=request.getContextPath()%>','${paymentType}');
});
</script>
</body></html>