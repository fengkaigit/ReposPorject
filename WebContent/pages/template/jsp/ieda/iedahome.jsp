<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>意见反馈</title>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/aboutUs.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/feedback/base.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/feedback/outer.css">
<script>
var gloabObj = {page:<c:out value="${page}"/>,rows:<c:out value="${rows}"/>,total:<c:out value="${total}"/>};
$(document).ready(function(){
	if(gloabObj.total!=0){
	   $("#pageNav").pagination({
	        items: gloabObj.total,
	        itemsOnPage:gloabObj.rows,
	        currentPage:gloabObj.page,
	        cssStyle: 'light-theme',
			prevText:'上一页',
			nextText:'下一页',
	        onPageClick:function(pageNumber, event){
			    window.location.href = "<%=request.getContextPath() %>/ej/feedback.do?page="+pageNumber+"&rows="+gloabObj.rows;
			}
	  });
    }
});
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
                            <!--
                            <tr>
                                <td height="33" class="line2" id="aboutUs"><table width="200" border="0" cellspacing="0" cellpadding="0">
                                        <tbody><tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td width="126" height="22" align="left"><a href="#">关于我们</a></td>
                                        </tr>
                                    </tbody></table></td>
                            </tr>
                            -->
                            <tr>
                                <td height="33" class="line3" id="aboutUs"><table width="200" border="0" cellspacing="0" cellpadding="0">
                                        <tbody><tr>
                                            <td width="24" height="22" align="center" class="bg10">&nbsp;</td>
                                            <td width="126" height="22" align="left"><a href="<%=request.getContextPath() %>/ej/feedback.do">意见反馈</a></td>
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
    <span class="tcxx_tt_a"><a href="<%=request.getContextPath() %>/ej/about.do">关于e缴365</a> &gt; <span class="tcxx_tt_b">意见反馈</span> </span>
</div>
<div class="pinl">
<c:forEach var="item" items="${feedbacks}" varStatus="status">
 <div class="outer-personal-info">
                <div class="fn-clear">
                    <div class="outer-thumbnail outer-thumbnail-lg"><img src="<%=request.getContextPath() %>/images/default_head_img.jpg" alt=""></div>
                    <div class="outer-personal-info-row" style="line-height: 30px; color: #999;">
                        <span style="margin-right:30px;">用户：${item.userName}</span>
                        <span>发表于：<fmt:formatDate value="${item.viewTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                    </div>
                    <div class="outer-personal-info-row" >
                        <span class="fn-right" style="width: 580px; color: #333;">${item.userIdea}</span>
                  </div>
                </div>
                         <c:if test="${item.systemFeedback!=''&&item.systemFeedback!=null}" >
                                <div class="outer-personal-atten" style="width:542px;">
                                        <div class="outer-personal-info-row" style="color: #999; line-height: 100%; margin-bottom: 15px;"><span style="margin-right:30px;">系统回复</span><span>发表于：<fmt:formatDate value="${item.systemTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
                    <div class="outer-personal-info-row">
                        <span style="width: 580px; color: #333;">${item.systemFeedback}</span>
                    </div>
                                   </div></c:if>
    </div>
    </c:forEach>
    <div id="pageNav" style="margin-right:55px;"></div>
    <div class="clear"></div>

   </div>

        </div>
        <!--右侧主页面-->

    </div>
    <%@include file="/pages/template/jsp/common/about.jsp"%> 
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>

<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>