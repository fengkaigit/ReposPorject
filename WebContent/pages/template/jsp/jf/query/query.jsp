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
<style>
.queryhead{
 color: #515151;
    font-weight: bold;
    height: 27px;
    line-height: 27px;
    text-align: center;
	font-size:12px;
	}
</style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/ofc/swfobject.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			swfobject.embedSWF("<%=request.getContextPath() %>/js/ofc/open-flash-chart.swf", "container", "735", "400", "9.0.0", 
					"expressInstall.swf",
					{"data-file":"<%=request.getContextPath() %>/jf/queryBarchart.do?year="+$("#year").val()+"&startMonth="+$("#startMonth").val()+"&endMonth="+$("#endMonth").val()});
		});
		function viewDetail(year,month){
			var iWidth =760;    
		    var iHeight = 500;  
		    var iTop = (window.screen.height-iHeight)/2;
		    var iLeft = (window.screen.width-iWidth)/2;
			window.showModalDialog("<%=request.getContextPath() %>/jf/queryDetail.do?year="+year+"&month="+month,"","dialogLeft="+iLeft+"px;dialogTop="+iTop+"px;dialogHeight="+iHeight+"px;dialogWidth="+iWidth+"px;status=no;scroll=no;resizable=no;help=no");
		}
		</script>
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
	    <td align="right">查询时间：
	    	<input type="text" id="year" name="year" value="${year}" size="8" maxlength="4"/>年
	    	<select id="startMonth" name="startMonth">
	    		 <c:forEach var="item" items="${monthes}" varStatus="status">
                      		<option value="${item}" <c:if test="${item==startMonth}">selected="selected"</c:if>>${item}</option>
                    </c:forEach>
	    	</select>月到<select id="endMonth" name="endMonth">
	    	 <c:forEach var="item" items="${monthes}" varStatus="status">
                      		<option value="${item}" <c:if test="${item==endMonth}">selected="selected"</c:if>>${item}</option>
                    </c:forEach>
	    	</select>
	    	<input type="submit" class="jycx" value="查 询" id="btnSearch">
	    	&nbsp;&nbsp;
	    </td>
	   </tr>
	</tbody></table>
    </div>
  </div>

<div id="searchpages">
		<table width="733" height="35" border="0" align="left" cellspacing="0" cellpadding="0">
		<tbody>
		 <tr class="add">
        <td width="15%" class="queryhead">账期</td>        
        <td width="50%" class="queryhead">缴费项目</td>
        <td width="20%" class="queryhead">月小计（元）</td>
        
        <td width="15%" class="queryhead">操作</td>
        </tr>
        <c:forEach var="item" items="${totalList}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
         <td >${item.year}年${item.month}月</td>        
        <td >${item.item}</td>
        <td >${item.money}</td>
        <td><a href="javascript:viewDetail(${item.year},${item.month});">详细</a></td>
       
        </tr>
 		</c:forEach>
 		 <tr class="even">
       
         <td  style="font-weight:bold;text-align: center;" colspan="4">合计金额：${money}</td>
        
        </tr>
 		
		</tbody>
        </table>
	</div>


<!--合计图形 -->

</form>   
</div> 
<div id="my_chart"></div>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto;border:1px solid #ddd;"></div>  
</div>  
</div>

<%@include file="/pages/template/jsp/common/about.jsp"%> 
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>

</body></html>