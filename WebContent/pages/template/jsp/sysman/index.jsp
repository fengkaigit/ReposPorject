<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  管理员设置 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;管理员详细设置</span>
	
	<span class="fr cur" onClick="openWin('<%=request.getContextPath() %>/sysman/add.do')"><span class="fcr">新建帐户</span></span>	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>登录名</td>
    <td>名称</td>
    <td>邮箱</td>
    <td>联系电话</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${syslist}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.managerName}</td>
    <td>&nbsp;${item.managerRealname}</td>
    <td>&nbsp;${item.EMail}</td>
    <td>&nbsp;${item.mobilePhone}</td>
    <td>
	<a class="fr cur" style="color:#007abd;float:left;"  onClick="openWin('<%=request.getContextPath() %>/sysman/edit/${item.id}.do')" >修改</a>
	<c:if test="${item.id!=0}"><a >删除</a></c:if></td>

  </tr>
 </c:forEach>
   
</table>

    </div>
    
</div>
</div>
<!--管理员设置弹出窗口  -->
<div id="addsysman" class="divWin" style="display:none;">
<div class="close cur"  onclick="javascript:turnoff('addsysman')">关闭</div>
            <h1></h1>
			 <p >管理员详细信息设置&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> 
            <table width="400" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td width="28%" bgcolor="#f1f8ff" align="right">登录名: </td>
                <td bgcolor="#FFFFFF">
                     <input type="text" name="managerName"  class="billInput"  id="managerName" maxlength="20">
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">真实名称: </td>
                <td bgcolor="#FFFFFF">
                                        <input type="text" name="managerRealname"  class="billInput"  id="managerRealname">
                </td>
            </tr>
          
			<tr>
                <td bgcolor="#f1f8ff" align="right">邮箱：</td>
                <td bgcolor="#FFFFFF">
                  
                     	<input type="text" name="billName"  class="billInput"  id="billName" maxlength="20">
						<span id="billNoWhere"><a style="font-family:'宋体';color:#FF0000;font-size:12px;" onClick="javascript:window.parent.jQuery.shfftOftenPay.sendSumbit(&quot;&quot;,3,888880002502900)" href="#">缴费时信息核对使用！</a></span>
					
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">联系电话：</td>
                <td bgcolor="#FFFFFF">
                  
                      <select class="selectCss"  id="cataLogId" name="cataLogId">						
						
							<option value="0001">呼和浩特春华水务自来水公司</option>
						
					</select>
					
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">缴费号：</td>
                <td bgcolor="#FFFFFF">
                    <input type="text" onClick="jQuery.billManagerDialog.closeTooltip();" autocomplete="off" onpaste="return false" class="billInput" id="confirmBillNo" name="confirmBillNo" maxlength="9">
                </td>
            </tr>
            <tr>	
                <td bgcolor="#f1f8ff" align="right">分 组：</td>
                <td bgcolor="#FFFFFF">
                	
					  <select class="selectCss"  id="cataLogId" name="cataLogId">						
						
							<option value="0001">自家</option>
						
							<option value="0000">父母家</option>
						
							<option value="0003">朋友家</option>
						
							<option value="0004">单位</option>
							
						    <option value="0005">其他</option>
								  
						
					</select>
					
					
                </td>
            </tr>
            <tr>
                <td bgcolor="#FFFFFF" align="center" colspan="2">
				<input  type="button" class="jfxx_btn3" onClick="openWin('jiaofei_zhsz.html')" value="保存" name="searchBill">
				
				</td>
            </tr>
            </tbody></table>
        </div>
<!--结束  -->
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>