<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>e缴365</title>
<meta content="" name="description">
<%@include file="/pages/template/jsp/common/common.jsp"%>
<style>
.input_middle{
   font-size:13px;
}
.input_middle label{vertical-align: middle;line-height:12px;height:13px;
}
.input_middle input{vertical-align: middle;margin-right:3px;
}
</style>
<script>
var gloabObj = {page:<c:out value="${page}"/>,rows:<c:out value="${rows}"/>,total:<c:out value="${total}"/>};
function delNotice(id){
	if(confirm("确实要删除该信息吗?")){
		if(gloabObj.total%gloabObj.rows==1&&gloabObj.page!=1)
			gloabObj.page = gloabObj.page-1;
	   jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/sysparam/delnotice.do",{id:id},"post","json",function(data){
		    if(data.result){
		    	alert("信息删除成功");
		        window.location.href = "<%=request.getContextPath() %>/sysparam/noticelist.do?page="+gloabObj.page+"&rows="+gloabObj.rows;
		    }
	    });
	}
}
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
			    window.location.href = "<%=request.getContextPath() %>/sysparam/noticelist.do?page="+pageNumber+"&rows="+gloabObj.rows;
			}
	  });
	}
	 $.formValidator.initConfig({formID:"saveForm",debug:false,submitOnce:false,
			onError:function(msg,obj,errorlist){
				$("#errorlist").empty();
				alert(msg);
			},
			onSuccess:function(){
				return false;
			},
			submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	   });
	 $("#noticeType").formValidator().inputValidator({min:1,onError:"请选择通知类型"});
	 $("#noticeMode").formValidator().inputValidator({min:1,onError:"请选择通知主方式"});
	 $("#serverContent").formValidator().inputValidator({min:1,onError:"通知内容不能为空"});
	 $("#parentarea").formValidator().inputValidator({min:1,onError:"请选择地区"});
});
function getData(id){
	jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/sysparam/noticeedit.do",{id:id},"get","json",function(data){
		$("#sendStatus").val(data.sendStatus);
		$("#noticeType").val(data.noticeType);
		$("#noticeMode").val(data.noticeMode);
		$("#id").val(data.id);
		$("#parentAreaId").val(data.parentAreaId);
		$("#areaId").val(data.areaId);
		$("#areaName").val(data.areaName);
		$("#newDate").val(data.createTime);
		var parentId = '';
		var areaId = '';
		if(data.parentAreaId!='0'){
			parentId = data.parentAreaId;
		    areaId = data.areaId;
		}else{
			parentId =  data.areaId;
		}
		$("#parentarea").val(parentId);
		   getArea(parentId,'area','请选择地区',areaId);
		$("#serverContent").val(data.serverContent);
		$("#operFlag").val('修改');
	    showDivWin('addNotice',520,420,'');
    });
	
}
function add(){
	$("#id").val('');
	$("#sendStatus").val('0');
	$("#noticeType").val('');
	$("#noticeMode").val('');
	$("#parentAreaId").val('');
	$("#areaId").val('');
	$("#areaName").val('');
	$("#parentarea").val('');
	$("#area").val('');
	$("#operFlag").val('添加');
	$("#serverContent").val('');
	showDivWin('addNotice',520,420,'');
}
function postHandle(){
	 if($.formValidator.pageIsValid()){
	   var pareaId = '0';
	   var parentarea = $("#parentarea");
	   var areaName = parentarea.find("option:selected").text();
	   var areaId = parentarea.val();
	   var area = $("#area");
	   if(area.val()!=''){
		   areaId = area.val();
		   pareaId = parentarea.val();
		   areaName = areaName+"/"+area.find("option:selected").text();
	   }
	   $("#areaId").val(areaId);
	   $("#parentAreaId").val(pareaId);
	   $("#areaName").val(areaName);
	   $('#saveForm').ajaxSubmit(function(data){
		    if(data.result){
		    	alert('用户通知信息'+$("#operFlag").val()+'成功');
		    	hiddenDiv('addNotice');
			    window.location.href = "<%=request.getContextPath() %>/sysparam/noticelist.do?page="+gloabObj.page+"&rows="+gloabObj.rows;
		    }
	   });
	 }
}
function getArea(id,elementId,initMessage,selectedValue){
	if(id!=""){
		var obj = {id:id};
		jQuery.shfftAjaxHandler.ajaxSynRequest("<%=request.getContextPath() %>/agent/getarea.do",obj,"get","json",function(data){
	    	if(data!=null&&data.length>0){
	    		var eleObj = document.getElementById(elementId);
	    		eleObj.options.length = 0;
	    		eleObj.options.add(new Option(initMessage,""));
	    		for(var i = 0 ; i < data.length ;i++){
	    			var obj = data[i];
	    			var option = new Option(obj.province,obj.id);
	    			eleObj.options.add(option);
	    		}
	    		if(selectedValue){
	    			eleObj.value = selectedValue;
	    		}
	    	}
	     });
	}
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div class="ui-container clearfix" id="container">  
  <div class="jfzh-title"><span class="icon1"></span>
  通知信息维护 
  </div>
<div class="jfzh-con">

	
    <div class="jfzh-bottom clearfix">    
	<div class="name"><span class="fl"><img src="<%=request.getContextPath() %>/images/common/icon2.png" width="16">&nbsp;&nbsp;通知信息</span>
	
	<span class="fr cur" onClick="add()"><span class="fcr">新建</span></span>	
	</div>
  
    <table  width="100%" border="0" cellspacing="0" cellpadding="0" class="tab" style="width:890px;">
  <tr class="add">
    <td>序号</td>
    <td>通知地区</td>
    <td>状态</td>
    <td>通知类型</td>
    <td>通知方式</td>
    <td>操作</td>
  </tr>
  <c:forEach var="item" items="${notices}" varStatus="status">
   <tr <c:choose>
       <c:when test="${(status.index+1) % 2 == 0}">
         class="add"
       </c:when>
       <c:otherwise>
         class="even"
       </c:otherwise>
     </c:choose> >
    <td>&nbsp;${status.index+1}</td>
    <td>&nbsp;${item.areaName}</td>
    <td>&nbsp;<c:if test="${item.sendStatus==0}">有效</c:if><c:if test="${item.sendStatus==1}">失效</c:if></td>
    <td>&nbsp;${noticeTypes[item.noticeType]}</td>
    <td>&nbsp;${noticeModes[item.noticeMode]}</td>
    <td>
	<a class="cur" style="color:#007abd;"  onClick="getData(${item.id})" >修改</a>&nbsp;
	<a class="cur" style="color:#007abd;" onclick="delNotice(${item.id})">删除</a></td>

  </tr>
 </c:forEach>
   
</table>

    </div>
   <div id="pageNav"></div>
  <div class="clear"></div> 
</div>
</div>
<form id="saveForm" method="post" action="<%=request.getContextPath() %>/sysparam/savenotice.do">
<input type="hidden" id="operFlag" />
<input type="hidden" id="id" name="id"/>
<input type="hidden" id="parentAreaId" name="parentAreaId"/>
<input type="hidden" id="areaId" name="areaId"/>
<input type="hidden" id="areaName" name="areaName"/>
<input type="hidden" id="newDate" name="newDate"/>
<div id="addNotice" class="divWin" style="display:none;">
<div class="close cur"  onclick="javascript:hiddenDiv('addNotice')">关闭</div>
            <h1></h1>
			 <p >添加通知信息</p> 
            <table style="width:100%;" border="0" bgcolor="#c3c6c9" cellspacing="0" cellpadding="0">
            <tbody><tr>
                <td width="28%" bgcolor="#f1f8ff" align="right" style="vertical-align: middle;">用户地区</td>
                <td bgcolor="#FFFFFF">
                         <select class="zc_city"  style="width:93px;height:28px;" id="parentarea" name="parentarea" onchange="getArea(this.value,'area','请选择城市')">
                             <option value="">请选择地区</option>
                             <c:forEach var="item" items="${areas}" varStatus="status">
                                <option value="${item.id}" >${item.province}</option>
                             </c:forEach>    
                          
                        </select>&nbsp;<select class="zc_city"  style="width:93px;height:28px;" id="area" name="area">
                             <option value="">请选择地区</option>
                        </select>               
                        </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">通知状态</td>
                <td bgcolor="#FFFFFF">
                             <select class="zc_city"  style="width:190px;height:28px;" id="sendStatus" name="sendStatus">
                                <option value="0" >有效</option>
                                <option value="1" >失效</option>
                        </select>
                </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">通知类型</td>
                <td bgcolor="#FFFFFF">
                        <select class="zc_city"  style="width:190px;height:28px;" id="noticeType" name="noticeType">
                             <option value="">请选择类型</option>
                             <c:forEach var="item" items="${noticeTypes}" varStatus="status">
                                <option value="${item.key}" >${item.value}</option>
                             </c:forEach>    
                          
                        </select>                
                        </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">通知方式</td>
                <td bgcolor="#FFFFFF">
                        <select class="zc_city"  style="width:190px;height:28px;" id="noticeMode" name="noticeMode">
                             <option value="">请选择方式</option>
                             <c:forEach var="item" items="${noticeModes}" varStatus="status">
                                <option value="${item.key}" >${item.value}</option>
                             </c:forEach>    
                          
                        </select>                
                        </td>
            </tr>
            <tr>
                <td bgcolor="#f1f8ff" align="right">通知内容</td>
                 <td bgcolor="#FFFFFF">
                       <textarea style="height: 105px;border-color: rgb(214, 214, 214);" onpropertychange="if(value.length&gt;10000)value=value.substring(0,10000)" class="textareafeedBox text text-empty" id="serverContent" name="serverContent"></textarea>
				 </td>
            </tr>
            <tr>
                <td bgcolor="#FFFFFF" align="center" colspan="2">
				<input  type="button" class="jfxx_btn3" onClick="postHandle()" value="保存" name="searchBill"/>
				</td>
            </tr>
            </tbody></table>
 </div>
 </form>
 <div id="bg"></div>
<%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="js/funs.js" type="text/javascript"></script>
</body></html>