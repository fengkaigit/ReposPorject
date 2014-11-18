<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>缴费记录</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/global.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/aboutUs.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/allQuery.css">

<link href="<%=request.getContextPath() %>/css/chart/chart.css" type="text/css" rel="stylesheet">
</head>
<body class="index">
<%@include file="/pages/template/jsp/common/header.jsp"%>
<div class="jf_main clearfix">
        <!--左侧菜单导入-->
<!--关于我们 左侧菜单-->
<div class="jf_lefts">
    <div class="jfztc_nav">
        <div class="left_tt">
            <a href="<%=request.getContextPath() %>/ej/about.do"><span style="line-height: 35px;">关于e缴365</span></a>
        </div>
        <div class="left_lists">
            <table width="193" border="0" bgcolor="#D1E6ED" align="center" cellspacing="0" cellpadding="0">
                <tbody><tr>
                    <td valign="top" height="200" bgcolor="#FFFFFF"><table width="200" border="0" align="center" cellspacing="0" cellpadding="0">
                            <tbody>
                           
                            <tr>
                                <td height="33" class="line3" id="aboutUs"><table width="200" border="0" cellspacing="0" cellpadding="0">
                                        <tbody><tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td width="126" height="22" align="left"><a href="<%=request.getContextPath() %>/jf/query.do">缴费记录</a></td>
                                        </tr>
                                    </tbody></table></td>
                            </tr>
                        </tbody></table></td>
                </tr>
            </tbody></table>
        </div>


    </div>
</div>


        <!--右侧主页面-->
        <div class="znx_r">
            
<div class="tcxx_tt">
    <span class="tcxx_tt_a"><a href="<%=request.getContextPath() %>/ej/about.do">关于e缴365</a> &gt; <span class="tcxx_tt_b">缴费记录</span> </span>
</div>
<div class="clearfix">
 <form  method="post" id="transQueryForm" action="<%=request.getContextPath() %>/jf/query.do">  
  <div class="jy_ope">
	<div class="jy_tags fl">
		<div class="A jy_tag1 jy_tags_bgon"><a class="live" href="<%=request.getContextPath() %>/jf/query.do">
			全部交易</a></div>
	</div>
    <div class="jy_date">
	<table width="520" border="0" cellspacing="0" cellpadding="0" style="HEIGHT: 38px">
	  <tbody><tr>
	    <td width="110" align="right">查询时间：</td>
	    <td width="15" align="left">从</td>
	    <td width="100" align="left">
	    	<input type="text" onClick="WdatePicker()" class="Wdate" id="startDate" value="2014-07-31" name="transQueryVo.tempStartDate">
	    </td>
	    <td width="16" align="left">到</td>
	    <td width="100">
	    	<input type="text" onClick="WdatePicker()" class="Wdate" id="endDate" value="2014-08-31" name="transQueryVo.tempEndDate">
	    </td>
	    <td width="72" align="left">
	    <input type="submit" class="jycx" value="查 询" id="btnSearch">

	  </td></tr>
	</tbody></table>
    </div>
  </div>
 <div class="jy_lists jy_lists_show">
 
        <div class="jy_tts">
       <table width="733" height="35" border="0" align="left" cellspacing="0" cellpadding="0">
		<tbody>
        <tr>
        <td width="65">交易时间</td>        
        <td width="85">业务类型</td>
        <td width="85">交易内容</td>
        <td width="85">账期</td>
        <td width="85">支付方式</td>
        <td width="85">金额（元）</td>
        <td width="85">状 态</td>
        <td width="126">操作</td>
        </tr>
		</tbody>
        </table>
         
       </div>
	</div>
<div id="searchpages">
		<table width="733" height="35" border="0" align="left" cellspacing="0" cellpadding="0">
		<tbody>
        <tr class="add">
        <td width="65">2014-07-20</td>        
        <td width="85">代缴</td>
        <td width="85">水电气</td>
         <td width="85">7月</td>
        <td width="85">在线支付</td>
        <td width="85">215</td>
        <td width="85">支付成功</td>
        <td style=" cursor:pointer"  onClick="showDivWin('divWin',480,430)">[图形分析]</td>
        </tr>
         <tr class="even">
        <td>2014-06-13</td>        
        <td>代缴</td>
        <td>水电气</td>
         <td >6月</td>
        <td>在线支付</td>
        <td>215</td>
        <td>支付成功</td>
      <td style=" cursor:pointer"  onClick="showDivWin('divWin',480,430)">[图形分析]</td>
        </tr>
        <tr class="add">
        <td width="65">2014-05-23</td>        
        <td width="85">代缴</td>
        <td width="85">水电气</td>
         <td width="85">5月</td>
        <td width="85">在线支付</td>
        <td width="85">215</td>
        <td width="85">支付成功</td>
        <td style=" cursor:pointer"  onClick="showDivWin('divWin',480,430)">[图形分析]</td>
        </tr>
         <tr class="even">
        <td>2014-04-20</td>        
        <td>代缴</td>
        <td>水电气</td>
         <td >4月</td>
        <td>在线支付</td>
        <td>215</td>
        <td>支付成功</td>
       <td style=" cursor:pointer"  onClick="showDivWin('divWin',480,430)">[图形分析]</td>
        </tr>
        <tr class="add">
        <td width="65">2014-03-23</td>        
        <td width="85">代缴</td>
        <td width="85">水电气</td>
         <td width="85">3月</td>
        <td width="85">在线支付</td>
        <td width="85">215</td>
        <td width="85">支付成功</td>
        <td style=" cursor:pointer"  onClick="showDivWin('divWin',480,430)">[图形分析]</td>
        </tr>
         <tr class="even">
        <td>2014-02-20</td>        
        <td>代缴</td>
        <td>水电气</td>
         <td >2月</td>
        <td>在线支付</td>
        <td>215</td>
        <td>支付成功</td>
         <td style=" cursor:pointer"  onClick="showDivWin('divWin',480,430)">[图形分析]</td>
        </tr>
        <tr class="add">
        <td width="65">2014-01-21</td>        
        <td width="85">代缴</td>
        <td width="85">水电气</td>
         <td width="85">1月</td>
        <td width="85">在线支付</td>
        <td width="85">215</td>
        <td width="85">支付成功</td>
         <td style=" cursor:pointer"  onClick="showDivWin('divWin',480,430)">[图形分析]</td>
        </tr>
		 <tr class="even">
        <td width="65"></td>        
        <td width="85"></td>
        <td width="85"></td>
         <td  style="font-weight:bold ">合计金额：</td>
        <td width="85"></td>
        <td style="font-weight:bold ">1505</td>
        <td width="85"></td>
        <td style="cursor:pointer"> </td>
        </tr>
 
		</tbody>
        </table>
	</div>


<!--合计图形 -->

</form>   
</div> 
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto;border:1px solid #ddd;"></div>  
</div>  
</div>

<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<!-- 弹出窗口-->
<div id="divWin" class="divWin">
<div class="close cur" onClick="hiddenDiv('divWin')">关闭</div>
    <h1 id="title"></h1>
	<div class="jfzh-con" style="width:460px;height:320px;padding:10px;border:0px;">
		<div class="jfzh-top clearfix">
		<div id="chart0" style="width:440px;height:300px;border:0px;">
        </div>
    <div style="margin-top:5px;" class="items clearfix" >
       <table cellpadding="0" cellspacing="0" width="100%" class="tab">
	<tr><th>水费</th><th>电费</th><th>燃气费</th></tr>
	<tr><td id="sf">35</td><td id="df">150</td><td id="rq">30</td></tr>
</table>
    
    </div>
   
    </div>
	 
    
</div>
</div>
<!-- 弹出窗口 end-->
<!-- 数据表格-->
<table id="datatable" style="display:none;">
	<thead>
		<tr>
			<th>月份</th>
			<th>缴费额</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>1月</th>
			<td>167</td>
		</tr>
		<tr>
			<th>2月</th>
			<td>191</td>
		</tr>
		<tr>
			<th>3月</th>
			<td>158</td>
		</tr>
		<tr>
			<th>4月</th>
			<td>189</td>
		</tr>
		<tr>
			<th>5月</th>
			<td>221</td>
		</tr>
        	<tr>
			<th>6月</th>
			<td>234</td>
		</tr>
        	<tr>
			<th>7月</th>
			<td>254</td>
		</tr>
	</tbody>
</table>
<div id="bg"></div>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/chart/highcharts.js"></script>
<script src="<%=request.getContextPath() %>/js/chart/data.js"></script>
<script src="<%=request.getContextPath() %>/js/chart/exporting.js"></script>
</body></html>