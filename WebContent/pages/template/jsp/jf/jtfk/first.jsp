<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>交通罚款缴费 - 生活助手</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<script language="javascript">
$(document).ready(function(){
	refreshAreaId(document.getElementById("_parentAreaId"));
	refreshCityId(document.getElementById("_areaId"));
	$.formValidator.initConfig({formID:"form1",alertMessage:true,debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
	$("#areaId").formValidator().inputValidator({min:1,onError:"请选择城市"});
	
	$("#carOwner").formValidator().inputValidator({min:1,max:30,onError:"请输入不多于30个字的行驶证车主"});
	$("#vehicleNumber").formValidator().inputValidator({min:1,max:30,onError:"请输入不多于30个字的行驶证号"});
	$("#billNumber").formValidator().inputValidator({min:1,max:30,onError:"请输入不多于30个字的车牌号码"});
	$("#payAddress").formValidator().inputValidator({min:1,max:100,onError:"请输入不多于100个字的缴费住址"});
	$("#carframeNumber").formValidator().inputValidator({min:1,max:30,onError:"请输入不多于30个字的车架号码"});
	$("#engineNumber").formValidator().inputValidator({min:1,max:30,onError:"请输入不多于30个字的发动机号"});
	
	$("#billMoney").formValidator().regexValidator({regExp:"decmal1",dataType:"enum",onError:"请输入合法缴费金额"});
	$("#poundage").formValidator().regexValidator({regExp:"decmal1",dataType:"enum",onError:"代缴劳务费金额非法"});
	registerBillNumber('<%=request.getContextPath()%>','${paymentType}');
	
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
															<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000063" href="<%=request.getContextPath() %>/ghf/first.do">
									<span data-id="10063" class="myapp-icon icon-apps24-10063">固话宽带</span>
									<span class="myapp-item-name">固话宽带</span>
																										</a>
															</li>
																<li class="myapp-item myapp-item-selected  fn-clear">
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
     <form  method="post" id="form1" action="<%=request.getContextPath() %>/jtfk/second.do">     
    <div coor="default-content" class=" ui-grid-20  ui-grid-right">
      <div style="overflow: hidden;" class="znx_r">

    	<div class="tcxx_tt">
    		
        	  
        </div>
        <div class="clear"></div>
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">交通缴费<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" onClick="quickSetting('<%=request.getContextPath() %>',5)">交通缴费设置</a>
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
        <div class="jf_txxx" style="margin-bottom:5px;">
        
        		<div style="border-bottom:1px solid #9D9D9D; margin:0px 0px 10px 0px; padding:10px 0px 10px 0px;"> 
				  <div style="font-size:14px; width:85px; overflow:hidden; padding-left:8px; float:left;"><span style="display:block;padding-top:px;"> 违章地区：</span></div>
				  <div style="float:left;">
				 		  
				  <select style="margin-left:24px;width:85px;" class="selectCss" onchange="refreshCity(this,'<%=request.getContextPath() %>','_areaId');refreshAreaId(this);" id="_parentAreaId" name="_parentAreaId" onmousewheel="return false">
				  
				  	<c:forEach var="item" items="${areaList}" varStatus="status">
                      <option value="${item.id}" <c:if test="${item.id==parent}">selected="selected"</c:if>>${item.province}</option>
                    </c:forEach>
				 </select>
                  
				  <select style="width:85px; margin-left:8px; " class="selectCss" id="_areaId" name="_areaId" onmousewheel="return false" onchange="refreshCityId(this);refreshPoundageByCity(this,'<%=request.getContextPath() %>','paymentType')">
				  	<c:forEach var="item" items="${cityList}" varStatus="status">
                      <option value="${item.id}" <c:if test="${item.id==areaId}">selected="selected"</c:if>>${item.province}</option>
                    </c:forEach>
				  </select>
                  </div>
                  <div style="clear:both;height:1px; overflow:hidden;"></div>
				</div>


        	<div class="jt" >
        	
            	
					<div class=" items " style="margin-bottom:10px;">
                    	
					<div>
		<label style="font-size:14px; margin-right:8px;line-height:30px;">行驶证车主：</label>
		
		   <input type="text" value="${hoster}" class="on-show" id="carOwner" name="carOwner" maxlength="30">
		   
	</div>
				<div>
		<label style="font-size:14px; margin-right:8px;line-height:30px;">行驶证号：</label>
		
		   <input type="text" class="on-show" id="vehicleNumber" name="vehicleNumber" maxlength="30"  value="${vehicleNumber}">
		   &nbsp;&nbsp;
           
	</div>	 
					 <div >
						<label style="font-size:14px;">车辆类型：</label>
		                &nbsp;&nbsp;<select class="selectCss"  onchange="" id="carType" name="carType" onmousewheel="return false">
						<option  value="0" >小型车</option>
						<option  value="1" >大型车</option>
						<option  value="2" >挂车</option>
						<option  value="3">摩托车</option></select>
					</div>
					
								<div>
		<label style="font-size:14px; margin-right:8px;line-height:60px;">车牌号码：</label>
		
		   <input type="text" class="on-show" id="billNumber" name="billNumber" maxlength="30"  value="${billNumber}">
		   &nbsp;&nbsp;
           <input name="bczh" type="checkbox"  checked="checked"> <span class="font2">备存账号信息</span>
	</div>
			<div>
		<label style="font-size:14px; margin-right:8px;line-height:60px;">缴费住址：</label>
		   <input type="text" class="on-show" id="payAddress" name="payAddress" maxlength="100" value="${payAddress}">
           
	</div>			
                    			<div>
		<label style="font-size:14px; margin-right:8px;line-height:60px;">车架号码：</label>
		
		   <input type="text"  class="on-show" id="carframeNumber" name="carframeNumber" maxlength="30" value="${carframeNumber}">
		   
	</div>
                    				<div>
		<label style="font-size:14px; margin-right:8px;line-height:80px;">发动机号：</label>
	
		   <input type="text"  class="on-show" id="engineNumber" name="engineNumber" maxlength="30" value="${engineNumber}">
		   
	</div>
                    
                    	
                    
                 
                    </div>
                    
              	
              
			  
            </div>
            <div style="border-bottom:1px solid #9D9D9D; margin:0px 0px 10px 0px; padding:0px 0px 5px 0px;"> 
        <div style="font-family:'微软雅黑'; width:120px; margin:0px auto;">
        		<input  type="button" class="jfxx_btn3" onClick="show('jtfk')" value="违章信息查询" name="searchBill">
        </div>
           
        		
				</div>
           <div style="clear:both;"></div>

        </div>
		
        <div style="clear:both;"></div>
        
        <!-- 缴费提示信息  原idchargeAttention  注释id：chargeAttention2来隐藏信息-->
        <div>
		<label style="font-size:14px; margin-right:8px;line-height:30px;"> &nbsp;&nbsp;&nbsp;缴费金额：</label>
		   <input type="text" value="" class="on-show" id="billMoney" name="billMoney" maxlength="10">
	&nbsp;&nbsp;元</div>
     
	<div style="margin-top:10px;clear:both;">
		<label style="font-size:14px; margin-right:8px;line-height:30px;">代缴劳务费：</label>
		   <input type="text" value="${poundage}" class="on-show" id="poundage" name="poundage" maxlength="10" readonly="readonly">
	&nbsp;&nbsp;元</div>
    <div style="margin-left:78px;line-height:20px;height:20px;display:none;clear:both;" class="onShow" id="billSubmitVoBillNoTip">请输入查询编号</div>
	



  <div style="font-family:'微软雅黑'; width:120px; margin:0px auto;">
  <input type="hidden" id="parentAreaId" name="parentAreaId"/>
<input type="hidden" id="areaId" name="areaId"/>
<input type="hidden" id="paymentType" name="paymentType" value="${paymentType}"/>
		<input  type="submit" class="jfxx_btn3" value="下一步" name="searchBill">
       </div>
  <span class="icon_futitle">
  <p>  &nbsp;1、因暂未开通与交管局的系统对接，故暂采取人工跑腿代缴方式完成亲的缴费，故在正常交通罚款费基础上增加${poundage}元代缴者的劳务费，请亲谅解！</p>
  <p>  &nbsp;2、有需要发票的亲，可自行到交管局柜台打印，或留下地址邮寄给您，但邮寄费需要您来承担。</p></span>

                <div style="clear:both"></div>
         
   
  <!-- ui-prod-newbie -->
  <!-- .content -->
  
  

			</div><!-- .ui-grid -->
			
		</div><!-- .ui-content -->
		</form>
	</div>
<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
<!--缴费账号设置弹出窗口  -->
<div id="addbills"  class="divWin"style="display:none; height:320px;">
<div class="close cur"  onclick="javascript:turnoff('addbills')">关闭</div>
            <h1></h1>
			 <p >【交通】缴费详细信息设置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（一次设置，便捷使用）</p> 
            <table width="400" border="0" bgcolor="#c3c6c9" cellspacing="1" cellpadding="10">
            <tbody><tr>
                <td width="28%" bgcolor="#f1f8ff" align="right">账单类型: </td>
                <td bgcolor="#FFFFFF">
                    <select class="selectCss"  id="cataLogId" name="cataLogId">						
						
						
								    <option value="0006">交通罚款费</option>
									   
						
					</select>
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">缴费区域: </td>
                <td bgcolor="#FFFFFF">
                    <select onclick="jQuery.billManagerDialog.closeTooltip();" class="selectCss"  name="orgId">                    	                       
                    <option value="888880002502900">内蒙古</option></select>
					 <select onclick="jQuery.billManagerDialog.closeTooltip();" class="selectCss"    name="orgId">                    	                       
                    <option value="888880002502900">呼和浩特</option></select>
                </td>
            </tr>
          
			<tr>
                <td bgcolor="#f1f8ff" align="right">车牌号码：</td>
                <td bgcolor="#FFFFFF">
                  
                     	<input type="text" name="billName"  class="billInput"  id="billName" maxlength="30">
						<span id="billNoWhere"><a style="font-family:'宋体';color:#FF0000;font-size:12px;" href="#">缴费时信息核对使用！</a></span>
					
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">公共事业单位<br>/收款单位：</td>
                <td bgcolor="#FFFFFF">
                  
                      <select class="selectCss"  id="cataLogId" name="cataLogId">						
						
							<option value="0001">呼和浩特交通管理局</option>
						
					</select>
					
                </td>
            </tr>
          
           
            <tr>
                <td bgcolor="#FFFFFF" align="center" colspan="2">
				<input  type="button"class="jfxx_btn3" onClick="openWin('jiaofei_zhsz.html')" value="保存" name="searchBill">
				
				</td>
            </tr>
            </tbody></table>
        </div>
        
         <!--缴费账号设置弹出窗口  -->

        
         <!--缴费账号设置弹出窗口  -->
<div id="jtfk" class="divWin" style="width:520px;height:320px;">
<div class="close cur"  onclick="javascript:turnoff('jtfk')">关闭</div>
 <h1></h1>
			 <p >交通违章记录</p> 
            <div class="jfzh-con" style="width:460px;padding:15px;" >
	<div class="jfzh-top clearfix">
 	
    <div style="margin-top:0px;" class="items clearfix" >
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab">
      
        <tbody>
		<div  class="dtr" > 
      <tr class="add1">
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日期</td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罚款地点</td>
         <td>&nbsp;&nbsp;&nbsp;&nbsp;罚款金额</td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原因</td>
      </tr>
	  </div>
      <tr class="even">
        <td>20140602</td>
        <td>呼和浩特草原明珠西路</td>
         <td>150元</td>
        <td>车辆逆行</td>
      </tr>
     <tr class="add">
        <td>20140708</td>
        <td>北京回龙观大街</td>
         <td>300元</td>
        <td>超速-扣6分</td>
      </tr>
      <tr class="even">
        <td>20140802</td>
        <td>呼和浩特成吉思汗大街</td>
         <td>100元</td>
        <td>违章停车</td>
      </tr>
       <tr class="add">
        <td>20140808</td>
        <td>呼和浩特成吉思汗大街</td>
         <td>100元</td>
        <td>违章停车</td>
      </tr>
      </tbody>
    </table>
    </div>
   
    </div>
    </div>
    </div>
<!--结束  -->
      <!--账单提醒设置弹出窗口  -->
<div id="zdtx" class="divWin"  style="width:480px;height:410px;">
<div class="close cur"  onclick="javascript:turnoff('zdtx')">关闭</div>
    <h1></h1>
		
            <div class="jfzh-con" style="width:460px;padding:10px;">
	<div class="jfzh-top clearfix">
   
  
    <div style="margin-top:5px;clear:both;">
    <label class="label">e-mail：</label><input type="text" class="ipt" name="user" id="user" /><span class="fcr">&nbsp;&nbsp;*</span>
    </div>

    <div style="margin-top:5px;clear:both;">
    <label class="label">手机：</label><input type="text" class="ipt" name="user" id="user" /><span class="fcr">&nbsp;&nbsp;*</span>
    </div>

    <div style="margin-top:5px;" class="items clearfix" >
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab">
        <thead>消息列表</thead>
        <tbody>
		<div  class="dtr" > 
      <tr class="add1">
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;信息内容</td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮件</td>
         <td>&nbsp;&nbsp;&nbsp;&nbsp;短信</td>
        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;系统内通知</td>
      </tr>
	  </div>
      <tr class="even">
        <td>&nbsp;账单提醒</td>
        <td align=center><input name="" type="checkbox" value=""></td>
         <td align=center><input name="" type="checkbox" value=""></td>
        <td align=center><input name="" type="checkbox" value=""checked="checked"></td>
      </tr>
     <tr class="add">
        <td>电子对账单生成时</td>
        <td align=center ><input name="" type="checkbox" value=""></td>
         <td align=center><input name="" type="checkbox" value=""></td>
        <td align=center><input name="" type="checkbox" value=""checked="checked"></td>
      </tr>
      <tr class="even">
        <td>代缴费办理完毕后</td>
        <td align=center><input name="" type="checkbox" value=""></td>
         <td align=center><input name="" type="checkbox" value=""></td>
        <td align=center><input name="" type="checkbox" value=""checked="checked"></td>
      </tr>
      <tr class="add">
        <td>代缴费异常</td>
        <td align=center><input name="" type="checkbox" value="" ></td>
         <td align=center><input name="" type="checkbox" value=""></td>
        <td align=center><input name="" type="checkbox" value="" checked="checked"></td>
      </tr>
      </tbody>
    </table>
    <div class="save"><input  type="button" style="clear:both;" class="jfxx_btn3" onClick="openWin('jiaofei_zhsz.html')" value="保存" name="searchBill"></div>
    </div>
   
    </div>
	 
    
</div>
</div>
</body></html>