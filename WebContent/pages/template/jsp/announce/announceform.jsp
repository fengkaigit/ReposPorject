
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<title>e缴365</title>
<head>
<%@include file="/pages/template/jsp/common/common.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/kindeditor-4.1/themes/default/default.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/js/kindeditor-4.1/plugins/code/prettify.css" />
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/js/kindeditor-4.1/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/js/kindeditor-4.1/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/js/kindeditor-4.1/plugins/code/prettify.js"></script>
<script>
$(document).ready(function(){
	$.formValidator.initConfig({formID:"announceForm",debug:false,submitOnce:true,
		onError:function(msg,obj,errorlist){
			$("#errorlist").empty();
			alert(msg);
		},
		submitAfterAjaxPrompt : '有数据正在异步验证，请稍等...'
	});
  
	$("#title").formValidator({onShow:"请输入标题",onFocus:"标题最多85个汉字",onCorrect:"输入合法"}).inputValidator({min:2,max:170,onError:"标题非法,请确认"});
	//$("#announcementScope").formValidator({onShow:"请选择公告范围",onFocus:"请选择公告范围",onCorrect:"选择合法"}).inputValidator({min:1,onError: "省份未选择,请选择!"});
	//$("#announcementGroup").formValidator({onShow:"请选择公告分组",onFocus:"请选择公告分组",onCorrect:"选择合法"}).inputValidator({min:1,onError: "城市未选择,请选择!"});
	//$("#provinc").formValidator({onShow:"请选择省份",onFocus:"请选择省份",onCorrect:"选择合法"}).inputValidator({min:1,onError: "省份未选择,请选择!"});
	//$("#city").formValidator({onShow:"请选择城市",onFocus:"请选择城市",onCorrect:"选择合法"}).inputValidator({min:1,onError: "城市未选择,请选择!"});
	//$("#status").formValidator({onShow:"请选择状态",onCorrect:"选择合法"}).inputValidator({min:1,onError: "城市未选择,请选择!"});	
	$("#content").formValidator({onShow:"请输入内容",onFocus:"请输入内容",onCorrect:"输入合法"}).inputValidator({min:1,onError: "内容不能为空!"});
	//$("#passwd").formValidator({onShow:"请输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码合法"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不合法,请确认"});
	//$("#confirmPassword").formValidator({onShow:"输再次输入密码",onFocus:"至少4个长度,最多20个长度",onCorrect:"密码一致"}).inputValidator({min:4,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码密码不合法,请确认"}).compareValidator({desID:"passwd",operateor:"=",onError:"两次密码不一致,请确认"});
	<c:if test="${announce.id!=null}">
	      var scope = '${announce.announcementScope}';
	      $("#announcementScope").val(scope);
          $("#announcementGroup").val('${announce.announcementGroup}');
          $("#status").val('${announce.status}');
          if(scope!='0'){
            var codeStr = '${areaPath}';
            var codeStrs = codeStr.split("/");
            $("#provinc").val(codeStrs[0]);
            getArea(codeStrs[0],'city','请选择城市',codeStrs.length==2||codeStrs.length==3 ? codeStrs[1]:undefined);
          }
          showcity(scope,'${announce.areaId}');
	      //if(codeStrs.length>=2)
	      //getArea(codeStrs[1],'area','请选择市区',codeStrs.length==3 ? codeStrs[2]:undefined);
	</c:if>
});
var editor1;
KindEditor.ready(function(K) {
	    editor1 = K.create('textarea[name="content"]', {
		cssPath : '<%=request.getContextPath() %>/js/kindeditor-4.1/plugins/code/prettify.css',
		uploadJson : '<%=request.getContextPath() %>/js/kindeditor-4.1/jsp/upload_json.jsp',
		allowImageUpload : true,
		items : [
			'undo', 'redo', '|', 'preview', 'print',  'cut', 'copy', 'paste',
			'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|','table', 'hr', 'emoticons',  'pagebreak','image',
		],
		afterChange:function(){
			K('.word_count').html(this.count('text'));
		}
	});
	prettyPrint();
});
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
function initArea(){
	editor1.sync();
	var pro = $("#provinc").val();
	var city = $("#city").val();
	var areaId = document.getElementById("areaId");
	areaId.value = '0';
	if(city!=""){
		areaId.value = city;
		return true;
	}
	if(pro!=""){
		areaId.value = pro;
		return true;
	}
	return true;
}
function refreshzsSpan(){
	msgLen = document.getElementById("content").value.length;
	//msgtx=280-msgLen;
	document.getElementById("zsspan").innerHTML=msgLen+"/300";
	
}
function showcity(value,selectValue){
	if(value==0){
		$("#annMong").hide();
		$("#annCity").hide();
	}else if(value==1){
		$("#annMong").show();
		$("#annCity").hide();
	}else{
		$("#annMong").show();
		$("#annCity").show();
	}
	if(!selectValue){
		 $("#provinc").val('');
	     $("#city").val('');
	}
}
</script>
</head>
<body>
<%@include file="/pages/template/jsp/common/sysheader.jsp"%>
<div>
<div class="jf_main">
<form method="post" action="<%=request.getContextPath() %>/announce/add.do?id=${announce.id}" id="announceForm" onsubmit="return initArea()" enctype="multipart/form-data">
    <div class="zc_zone">
        <div class="zc_title">
            <div class="card_title1">
                <ul>
                <li style="float:left;"><span>公告发布</span></li> 
                 </ul>
    
            </div>
        </div>
        <div class="fm-item1">
            <table width="88%" border="0" align="center" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td height="40" width="100" align="right">标题：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><input type="text" name="title" class="on-show" autocomplete="off" id="title" value="${announce.title}" /><span></span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="titleTip" style="width:250px"></div></td>
                    </tr>
                   <tr>
                        <td height="40" align="right">公告范围：</td>
                        <td align="left"><select class="zc_city"  style="width:190px;" id="announcementScope" name="announcementScope" onchange="showcity(this.value)">
                             <option value="0">全国</option>
                             <option value="1">省</option>
                             <option value="2">市</option>
                        </select>
                        </td>
                        <td><div id="announcementScopeTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">公告分组：</td>
                        <td align="left"> 
                        <select class="zc_city"  style="width:190px;" id="announcementGroup" name="announcementGroup">
                              <option value="0">代理商</option>
                              <option value="1">用户</option>
                        </select></td>
                        <td><div id="announcementGroupTip" style="width:250px"></div></td>
                    </tr>
                    <tr id="annMong" style="display:none;">
                        <td height="40" align="right">省份：</td>
                        <td align="left"> <input type="hidden" name="areaId" id="areaId" value="${announce.areaId}"/><select class="zc_city"  style="width:190px;" id="provinc" name="provinc" onchange="getArea(this.value,'city','请选择城市')">
                             <option value="">请选择省份</option>
                            <c:forEach var="area" items="${areas}" varStatus="status"> 
                            <option value="${area.id}">${area.province}</option>
                            </c:forEach>
                        </select>
                        </td>
                        <td><div id="provincTip" style="width:250px"></div></td>
                    </tr>
                    <tr id="annCity" style="display:none;">
                        <td height="40" align="right">市：</td>
                        <td align="left"> 
                  <select class="zc_city"  style="width:190px;" id="city" name="city">
                             <option value="">请选择市</option>
                        </select></td>
                        <td><div id="cityTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" align="right">公告状态：</td>
                        <td align="left"> 
                  <select class="zc_city"  style="width:190px;" id="status" name="status">
                             <c:forEach var="st" items="${status}" varStatus="status"> 
                              <option value="${st.id.dataValue}">${st.propChName}</option>
                             </c:forEach>
                        </select></td>
                        <td><div id="statusTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td height="40" width="100" align="right">内容：</td>
                        <td align="left">
                            <table>
                                <tbody>
                                    <tr>
                                        <td><textarea style="width: 100%;height:280px;visibility:hidden;" id="content" name="content">${announce.content}</textarea><span class="word_count">0</span>字</td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                         <td><div id="contentTip" style="width:250px"></div></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 87px;" colspan="3"><input id="btnadd" name="btnadd"  type="submit" class="dk_pay1" value="确定"/></td>
                    </tr>
                    <tr>
                        <td style="padding-left: 87px;" colspan="3">
                        <div class="onError">${message}</div>
                       </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</form>

        <!--右侧主页面-->
    </div>

</div>   
 <%@include file="/pages/template/jsp/common/footer.jsp"%>
<%@include file="/pages/template/jsp/common/links.jsp"%>
<script src="<%=request.getContextPath() %>/js/funs.js" type="text/javascript"></script>
</body></html>