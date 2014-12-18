<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" import="com.ey.util.DateUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>代理商首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/css/agentiframe/base[2].css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/agentiframe/base_table[1]_2.css" type="text/css" rel="stylesheet">
<style type="text/css">
table.gridtable{
	width:98%;
	font-family: verdana,arial,sans-serif;
	font-size: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	margin:2px;
}
table.gridtable th {
	border-width: 1px;
	padding: 3px;
	border-style: solid;
	border-color: #007d65;
	background-color: #84bf96;
	line-height:18px;
}
table.gridtable td {
	border-width: 1px;
	padding: 3px;
	border-style: solid;
	border-color: #007d65;
	background-color: #ffffff;
	line-height:27px;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.min.js"></script> 
<script src="<%=request.getContextPath() %>/js/common/shfftAjax.js" language="Javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ofc/swfobject.js"></script>
<script>
var date = new Date();
var year = date.getFullYear();
var monthValues = new Object();
monthValues[year] = "合计";
monthValues[year+"-01"] = "一月份";
monthValues[year+"-02"] = "二月份";
monthValues[year+"-03"] = "三月份";
monthValues[year+"-04"] = "四月份";
monthValues[year+"-05"] = "五月份";
monthValues[year+"-06"] = "六月份";
monthValues[year+"-07"] = "七月份";
monthValues[year+"-08"] = "八月份";
monthValues[year+"-09"] = "九月份";
monthValues[year+"-10"] = "十月份";
monthValues[year+"-11"] = "十一月份";
monthValues[year+"-12"] = "十二月份";
$(document).ready(function(){
	//获取公告
	 jQuery.shfftAjaxHandler.ajaxRequest("<%=request.getContextPath() %>/announce/agentgglist.do",{group:0,page:1,rows:5},"get","json",function(data){
		   if(data.length>0){
			   var ggHtml = [];
			   var gglist = document.getElementById("ggList");
			   for(var i=0;i<data.length;i++){
				   var obj = data[i];
				   ggHtml.push('<div class="list">');
				   ggHtml.push('<div class="listName">');
				   ggHtml.push('<a href="javascript:getgg('+obj.id+');" title="'+obj.title+'">'+obj.title+'</a>');
				   ggHtml.push('</div>');
				   ggHtml.push('<div class="desktopTime">'+obj.createTime+'</div>');
				   ggHtml.push('</div>');
				 }
			   gglist.innerHTML = ggHtml.join("");
		   }
	 });
	//获取待办
	 jQuery.shfftAjaxHandler.ajaxRequest("<%=request.getContextPath() %>/agent/self.do",{page:1,rows:10,status:0},"get","json",function(data){
		   if(data.length>0){
			   var dbHtml = [];
			   var dblist = document.getElementById("dbList");
			   for(var i=0;i<data.length;i++){
				   var obj = data[i];
				   var title = obj.payTypeName+'订单'+obj.billNum+'笔，请各代理商落实';
				   dbHtml.push('<div class="list">');
				   dbHtml.push('<div class="listName">');
				   dbHtml.push('<a href="javascript:showbatch('+obj.id+')" title="'+title+'"><span class="error warn">'+title+'</span></a>');
				   dbHtml.push('</div>');
				   dbHtml.push('<div class="desktopTime">'+obj.createTime+'</div>');
				   dbHtml.push('</div>');
				 }
			   dblist.innerHTML = dbHtml.join("");
		   }
	 });
	
	//当月订单完成情况
	 swfobject.embedSWF("<%=request.getContextPath() %>/js/ofc/open-flash-chart.swf", "bt1", "300", "200", "9.0.0", 
				"expressInstall.swf",
				{"data-file":"<%=request.getContextPath() %>/agent/monthBillChart.do?year="+year});
	//当月新增用户数
      	 swfobject.embedSWF("<%=request.getContextPath() %>/js/ofc/open-flash-chart.swf", "bt2", "300", "200", "9.0.0", 
				"expressInstall.swf",
				{"data-file":"<%=request.getContextPath() %>/agent/queryUserChart.do?year="+year});
    //当月决算
      	 swfobject.embedSWF("<%=request.getContextPath() %>/js/ofc/open-flash-chart.swf", "bt4", "300", "200", "9.0.0", 
				"expressInstall.swf",
				{"data-file":"<%=request.getContextPath() %>/agent/monthSettleChart.do?year="+year});
	//统计报表
	 //jQuery.shfftAjaxHandler.ajaxRequest("<%=request.getContextPath() %>/agent/report.do",{year:year},"get","json",function(data){
		  // if(data.length>0){
			  // var reportHTML = [];
			   //var reportTable = document.getElementById("reportTable");
			  // for(var i=0;i<data.length;i++){
				   //var obj = data[i];
				   //reportHTML.push('<tr>');
				   //reportHTML.push('<td align="left">'+obj.month+'</td>');
				   //reportHTML.push('<td>'+obj.userNum+'</td>');
				  // reportHTML.push('<td>'+obj.userPercent+'</td>');
				  // reportHTML.push('<td>'+obj.billMoney+'</td>');
				  // reportHTML.push('<td>'+obj.billPercent+'</td>');
				   //reportHTML.push('</tr>');
				 //}
			   //reportTable.innerHTML = reportHTML.join("");
			  //alert(reportHTML.join(""));
		   //}
	// });
      	
});
function getgg(id){
	window.parent.location.href = "<%=request.getContextPath() %>/announce/showgg.do?id="+id;
}
function showbatch(id){
	window.parent.location.href = "<%=request.getContextPath() %>/agent/billlist.do?id="+id;
}
function moregg(){
	window.parent.location.href = "<%=request.getContextPath() %>/announce/more.do?group=0";
}
function moredb(){
	window.parent.location.href = "<%=request.getContextPath() %>/agent/worklist.do?status=0";
}
</script>
</head>
<body >
	<ul id="desktopBox">
		<div class="centerDesktopBox">
			<ul class="workBox">
				<li class="box" style="width: 300px;">
					<div class="title">
						<div class="desktopTitleName">通知公告</div>
						<div class="desktopTitleName1"><a class="more" href="javascript:moregg()">更多&gt;</a></div>
					</div>
					<div id="ggList">
						
					</div>
				</li>
				<li id="dbwork" class="box" style="width: 645px;">
					<div class="title">
						<div class="desktopTitleName">待办工作</div>
						<div class="desktopTitleName1" style="width:15%"><a class="more" href="javascript:moredb()">更多&gt;</a></div>
					</div>
					<div id="dbList">
					</div>
				</li>
			</ul>
			<div style="clear: both;"></div>
			<ul class="workBox">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td>
								<li class="box" style="width: 300px;">
									<div class="title">
										<div class="desktopTitleName">当月订单完成情况</div>
									</div>
									<div class="" id="bt1">
									</div>
								</li>
							</td>
							<td>
								<li class="box" style="width: 300px;">
									<div class="title">
										<div class="desktopTitleName">当月新增用户</div>
									</div>
									<div class="" id="bt2">
									</div>
								</li>
							</td>
							<td rowspan="2" valign="top">
								<li class="box" style="width: 330px; height: 492px;">
									<div class="title">
										<div class="desktopTitleName" id="xxlist"><script>document.write(year);</script>年综合统计报表</div>
										<div class="desktopMore"></div>
									</div>
									<div id="xqList">
										<div id="bt12" style="display: ">
											<div style="clear: both;"></div>
											<table class="gridtable">
											<tbody>
											   <tr>
														<th>项目</th>
														<th>用户数</th>
														<th>占比（%）</th>
														<th>订单金额</th>
														<th>占比（%）</th>
													</tr>
													<c:forEach var="item" items="${reports}" varStatus="status">
													<tr>
														<td align="left"><script>document.write(monthValues['${item.month}']);</script></td>
														<td>${item.userNum}</td>
														<td>${item.userPercent}</td>
														<td>${item.billMoney}</td>
														<td>${item.billPercent}</td>
													</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div> <!-- <div class="rightTableInfo"></div> -->
								</li>
							</td>
						</tr>
						<tr>
							<td>
								<li class="box" style="width: 300px;">
									<div class="title">
										<div class="desktopTitleName">用户投诉</div>
									</div>
									<div class="" id="bt3">
										<img src="<%=request.getContextPath() %>/images/bt3.jpg" align="right" height="200px">
									</div>
								</li>
							</td>
							<td>
								<li class="box" style="width: 300px;">
									<div class="title">
										<div class="desktopTitleName">当月决算</div>
									</div>
									<div class="" id="bt4">
									</div>
								</li>
							</td>
						</tr>
					</tbody>
				</table>
			</ul>
			<div style="clear: both; width: 100px; height: 15px;"></div>
		</div>
	</ul>
</body>