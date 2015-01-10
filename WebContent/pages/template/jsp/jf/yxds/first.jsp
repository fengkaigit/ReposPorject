<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>有线电视缴费 - 生活助手</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
 <link href="<%=request.getContextPath()%>/css/jquery.fancybox.css" rel="stylesheet" />
 <script src="<%=request.getContextPath()%>/js/jquery.fancybox.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
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
	$("#entId").formValidator().inputValidator({min:0,onError:"请选择公用事业单位"});
	$("#billNumber").formValidator().inputValidator({min:1,max:30,onError:"请输入用户编号"});
	$("#payAddress").formValidator().inputValidator({min:1,max:100,onError:"请输入不多于100个字的缴费住址"});
	$("#jfdate").formValidator().inputValidator({min:1,max:10,onError:"请选择缴费日期"});
	$("#billMoney").formValidator().regexValidator({regExp:"decmal1",dataType:"enum",onError:"请输入合法缴费金额"});
	$("#poundage").formValidator().regexValidator({regExp:"decmal1",dataType:"enum",onError:"代缴劳务费金额非法"});

	registerBillNumber('<%=request.getContextPath()%>','${paymentType}');
	$('.fancybox').fancybox();
	chgBillImg('<%=request.getContextPath()%>','entId','${paymentType}');
	
	var tvgroups = document.getElementsByName("tvgroup");
	if(tvgroups){
	for(var i=0;i<tvgroups.length;i++){
		if(tvgroups[i].checked==true){
			document.getElementById("billMoney").value=tvgroups[i].value.split(",")[2];
			break;
		}
	}
	}
});
function viewMore(){
	var areaId = document.getElementById("_areaId").value;
	if(areaId==""){
		alert("请选择城市");
		return;
	}
	deleteRow();
	$.ajax({
        type: "post",
        url: chgUrl("<%=request.getContextPath()%>/yxds/gettvs.do"),
        dataType: "json",
        data: {
			type:1,
			areaId:areaId
        },
        success: function(data, textStatus){
       	 //alert(data);
       	if(data){
       		var tableObj = document.getElementById("tvtbl");
       		
       		for(var i=0;i<data.length;i++){
       			var tr1=tableObj.insertRow();
       			if(i%2==0){
					tr1.className="add";
           		}else{
           			tr1.className="even";
               	}
       		    var td1=tr1.insertCell(0);
       		    td1.style.textAlign='center';
       		    var td2=tr1.insertCell(1);
       		 	td2.style.textAlign='center';
       		    td1.innerHTML=data[i].televisionName+"（"+data[i].televisionMoney+"元/月）";
       		 	td2.innerHTML="<input type='checkbox' name='delkey' value='"+data[i].id+","+data[i].televisionName+","+data[i].televisionMoney+"'/>";
           	}
       		show('zdtx');
       		
       	}
        }
      
    });
}
	function deleteRow(){
		 
		   var tableObj=document.getElementById("tvtbl");
		   var rowNum=tableObj.rows.length;
		   while(rowNum>0){
		    tableObj.deleteRow(rowNum-1);
		    rowNum--;
		   }
		   
	}
	function tzje(radioObj){
		var othertvs = document.getElementById("othertvs");
		var ogv=0;
		if(othertvs.value!=""){
			var othertvses = othertvs.value.split(";");
			for(var i=0;i<othertvses.length;i++){
				ogv = ogv + parseFloat(othertvses[i].split(",")[2]);
			}
		}
		var jfmonth = document.getElementById("jfmonth").value;
		document.getElementById("billMoney").value=(parseFloat(radioObj.value.split(",")[2])+ogv)*parseFloat(jfmonth);
	}
	function sureCheck(){
		
		var delkeys = document.getElementsByName("delkey");
		if(delkeys){
		var othertvs="";
		var ogv=0;
		for(var i=0;i<delkeys.length;i++){
		if(delkeys[i].checked==true){
			ogv = ogv+parseFloat(delkeys[i].value.split(",")[2]);
			if(othertvs==""){
				othertvs=delkeys[i].value;
			}else{
				othertvs=othertvs+";"+delkeys[i].value;
			}
		}
		}
		document.getElementById("othertvs").value=othertvs;
		retzje();
		}
		turnoff('zdtx');
	}
	function retzje(){
		var tvgroups = document.getElementsByName("tvgroup");
		if(tvgroups){
		var radioObj;
		for(var i=0;i<tvgroups.length;i++){
			if(tvgroups[i].checked==true){
				radioObj=tvgroups[i];
				break;
			}
		}
		if(radioObj){
			tzje(radioObj);
		}
		}
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
																<li class="myapp-item  fn-clear">
								<a seed="myapp-item-1000000086" href="<%=request.getContextPath() %>/jtfk/first.do">
									<span data-id="10073" class="myapp-icon icon-apps24-10073">交通罚款</span>
									<span class="myapp-item-name">交通罚款</span>
																										</a>
															</li>
															<li class="myapp-item myapp-item-selected  fn-clear">
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
      <div style="overflow: hidden;" class="znx_r">

    	<div class="tcxx_tt">
    		

        	<!--<span class="tcxx_tt_a">
        	 <a href="/shanghai/shuifei">水费</a>
        	  &gt; <span class="tcxx_tt_b">填写付费信息</span></span>-->
        	  
        </div>
        <div class="clear"></div>
        <div style="margin-bottom: 0px; display: block; float: left;" id="icon_title_0000"><div style="float:left">有线电视缴费<span class="icon_futitle">单笔账单快速支付</span></div>
          <span style="float:right; margin-top:15px; margin-right:40px;" class="lcyst03">
		  <a class="ywjs" onClick="quickSetting('<%=request.getContextPath() %>',7)">电视缴费设置</a>
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
        <div class="jf_txxx">
        
        		<div style="border-bottom:1px solid #9D9D9D; width:770px; margin:0px 0px 10px 0px; padding:10px 0px 10px 0px;"> 
				  <div style="font-size:14px; width:85px; overflow:hidden; padding-left:6px; float:left;"><span style="display:block;padding-top:1px;"> 所在城市：</span></div>
				  <div style="float:left;">
				  <select style="margin-left:24px;width:85px;" class="selectCss" onchange="refreshCity(this,'<%=request.getContextPath() %>','_areaId');refreshAreaId(this);" id="_parentAreaId" name="_parentAreaId" onmousewheel="return false">
				  
				  	<c:forEach var="item" items="${areaList}" varStatus="status">
                      <option value="${item.id}" <c:if test="${item.id==parent}">selected="selected"</c:if>>${item.province}</option>
                    </c:forEach>
				 </select>
                  
				  <select style="width:85px; margin-left:8px; " class="selectCss" id="_areaId" name="_areaId" onmousewheel="return false" onchange="refreshCityId(this);refreshEntByCity(this,'<%=request.getContextPath() %>','paymentType','entId');refreshtvs(this,'<%=request.getContextPath() %>');refreshPoundageByCity(this,'<%=request.getContextPath() %>','paymentType')">
				  	<c:forEach var="item" items="${cityList}" varStatus="status">
                      <option value="${item.id}" <c:if test="${item.id==areaId}">selected="selected"</c:if>>${item.province}</option>
                    </c:forEach>
				  </select>
				  
                  </div>
                  
                  <div style="clear:both;height:1px; overflow:hidden;"></div>
				</div>


        	<div class="jf_txxx_left">
        	
            	 <form method="post" id="form1" action="<%=request.getContextPath() %>/yxds/second.do">
            	 <div style="padding:0px 0px 10px 0px;">
						<label>公用事业单位：</label>
						
		                <select class="selectCss"  id="entId" name="entId" onchange="chgBillImg('<%=request.getContextPath()%>','entId','${paymentType}')" onmousewheel="return false">
		                <c:forEach var="item" items="${charges}" varStatus="status">
                      		<option value="${item.id}" >${item.enterpriseName}</option>
                    	</c:forEach>
		                </select>
					</div>
					
				
	                <div style="height:250px;" id="queryBillDiv">
	    		    
	    		
				<div id="queryBillChargeDiv">

<!--新缴费流程代码-->

	<div>
		<label style=" margin-right:8px;line-height:30px;">用户编号：</label>
		   <input type="text" class="on-show" id="billNumber" name="billNumber" maxlength="30" value="${billNumber}">
		   &nbsp;&nbsp;
           <input id="bczh" name="bczh" type="checkbox" checked="checked"> <span class="font2">备存账号信息</span>
           
	</div>
  <div>
		<label style=" margin-right:8px;line-height:30px;">缴费住址：</label>
		   <input type="text" class="on-show" id="payAddress" name="payAddress" maxlength="100" value="${payAddress}">
           
	</div>

    <div style="border-bottom:1px solid #9D9D9D; width:770px; margin:0px 0px 20px 0px; padding:0px 0px 10px 0px;"> 
				  <label style=" margin-right:8px;line-height:30px;">缴费日期：</label>
				  
				 
				  	<input type="text" class="on-show" maxlength="10" id="jfdate" name="jfdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                  
                  <div style="clear:both;height:1px; overflow:hidden;"></div>
	</div>
  
 <!--star -->
	<div>
		<label style="margin-right:8px;line-height:30px;"> 频道：</label>
			<div id="tvgroups">
		    <c:forEach var="item" items="${tvList}" varStatus="status">
		    	${item.televisionName}（${item.televisionMoney}元/月）<input type="radio" id="tvgroup${item.id}"  name="tvgroup" value="${item.id},${item.televisionName},${item.televisionMoney}" <c:if test="${(status.index)== 0}"> checked="checked"</c:if> onfocus="tzje(this);"/>&nbsp;&nbsp;
            </c:forEach><a href="javascript:viewMore();">更多</a>
            </div>
	</div>
	<div>
		<label style=" margin-right:8px;line-height:30px;"> 缴费月数：</label>

		     <select id="jfmonth" name="jfmonth" class="selectCss" onchange="retzje();">
		     	<option value="1">一个月</option>
		     	<option value="2">两个月</option>
		     	<option value="3">三个月</option>
		     	<option value="4">四个月</option>
		     	<option value="5">五个月</option>
		     	<option value="6">六个月</option>
		     	<option value="7">七个月</option>
		     	<option value="8">八个月</option>
		     	<option value="9">九个月</option>
		     	<option value="10">十个月</option>
		     	<option value="11">十一个月</option>
		     	<option value="12">十二个月</option>
		     </select>

	</div>
 	<div>
		<label style=" margin-right:8px;line-height:30px;"> 缴费金额：</label>
		   <input type="text" value="" class="on-show" id="billMoney" name="billMoney" maxlength="10" readonly="readonly">&nbsp;&nbsp;元
	</div>
     
	<div style="margin-bottom: 0px; margin-top:5px;clear:both;">
		<label style=" margin-right:8px;line-height:40px;">代缴劳务费：</label>
		   <input type="text" value="${poundage}" class="on-show" id="poundage" name="poundage" maxlength="10" readonly="readonly">&nbsp;&nbsp;元
	</div>

<div class="jfxx_btns">
<input type="hidden" id="othertvs" name="othertvs"/>
<input type="hidden" id="parentAreaId" name="parentAreaId"/>
<input type="hidden" id="areaId" name="areaId"/>
<input type="hidden" id="paymentType" name="paymentType" value="${paymentType}"/>
<input  type="submit" class="jfxx_btn3"  value="下一步" name="searchBill"></div>


</div>
	                </div>
					
	              	

              
              	</form>
            </div>
            <div style="margin-top:0px; position:relative;" class="jf_txxx_right">
            
                <div style="" id="billDetailExample" class="jf_txxx_right"> 
		            <div style="position:relative;" class="check_styles">
					
		            
		                   <div id="chargeOrgBill" class="check_picture"><a class="fancybox" id="billChargeImga" href="<%=request.getContextPath()%>/images/no_billImage.jpg" data-fancybox-group="gallery" title="缴费票示例图"><img
	src="<%=request.getContextPath()%>/images/no_billImage.jpg"
	id="billChargeImg"></a></div>
		                   
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
  <p>  &nbsp;1、因暂未开通与广播电视公司的系统对接，故暂采取人工跑腿代缴方式完成亲的缴费，故在正常电视费基础上增加${poundage}元代缴者的劳务费，请亲谅解！</p>
  <p>  &nbsp;2、有需要发票的亲，可自行到广播电视公司柜台打印，或留下地址邮寄给您，但邮寄费需要您来承担。</p></span>

                <div style="clear:both"></div>
         
 
  <!-- ui-prod-newbie -->
  <!-- .content -->
  
  

			</div><!-- .ui-grid -->
			
		</div><!-- .ui-content -->
		
	</div>
<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<div id="zdtx" class="divWin"  style="width:480px;height:410px;">
<div class="close cur"  onclick="javascript:turnoff('zdtx')">关闭</div>
    <h1></h1>
		
            <div class="jfzh-con" style="width:460px;padding:10px;">
	<div class="jfzh-top clearfix">
    <div style="margin-top:5px;" class="items clearfix" >
        <table id="tvtbl" width="100%" border="0" cellspacing="0" cellpadding="0" class="tab">
        <thead>节目列表</thead>
        <tbody>

      
      </tbody>
    </table>
    <div class="save"><input  type="button" style="clear:both;" class="jfxx_btn3" onClick="sureCheck();" value="确定" name="btnsure"></div>
    </div>
   
    </div>
	 
</div>
</div>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>