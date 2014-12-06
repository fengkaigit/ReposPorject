function changeVerifyFP(url){
	 var imgSrc = $("#loginValidateImg");     
	 //var src = imgSrc.attr("src");     
	 imgSrc.attr("src",chgUrl(url));
}
function chgUrl(url){     
    var timestamp = (new Date()).valueOf();     
    urlurl = url.substring(0,17);     
    if((url.indexOf("&")>=0)){     
        urlurl = url + "&timestamp=" + timestamp;     
    }else{     
        urlurl = url + "?timestamp=" + timestamp;     
    }
    //alert(urlurl);
    return urlurl;     
}
function login(){
	var loginCode = $("#loginCode").val();
	var loginPass = $("#password").val();
	var loginVerify = $("#verify").val();
	if(loginCode==""){
		alert("请输入登录账号");
		 $("#loginId").focus();
		return;
	}
	if(loginPass==""){
		alert("请输入登录密码");
		$("#loginWord").focus();
		return;
	}
	if(loginVerify==""||loginVerify=="输入验证码"){
		alert("请输入验证码");
		$("#verify").focus();
		return;
	}
	if(loginVerify.length!=4){
		alert("验证码不正确");
		$("#verify").focus();
		return;
	}
	document.getElementById("loginForm").submit();
}
function doreg(){
	var accountNumber = $("#accountNumber").val();
	
	var realName = $("#realName").val();
	
	var registType = $("#registType").val();
	
	var areaId = $("#areaId").val();
	
	var passwd = $("#passwd").val();
	
	var confirmPassword = $("#confirmPassword").val();
	
	var email = $("#email").val();
	
	var mobilePhone = $("#mobilePhone").val();
	
	var verify = $("#verify").val();
	
	if(accountNumber==""){
		alert("请输入登录账号");
		 $("#accountNumber").focus();
		return;
	}
	if(realName==""){
		alert("请输入真实姓名");
		 $("#realName").focus();
		return;
	}
	if(passwd==""){
		alert("请输入密码");
		 $("#passwd").focus();
		return;
	}
	if(passwd.length<4){
		alert("密码长度至少为4位");
		 $("#passwd").focus();
		return;
	}
	if(confirmPassword==""){
		alert("请输入确认密码");
		 $("#confirmPassword").focus();
		return;
	}
	if(passwd!=confirmPassword){
		alert("密码和确认密码不一致");
		 $("#confirmPassword").focus();
		return;
	}
	if(verify==""){
		alert("请输入验证码");
		 $("#verify").focus();
		return;
	}
	if(verify.length!=4){
		alert("验证码不正确");
		$("#verify").focus();
		return;
	}
	document.getElementById("registerForm").submit();
}
function refreshCity(obj,site,targetObjId){
	if(obj&&obj.value){
		if(obj.value!=""){
			
			document.getElementById(targetObjId).options.length = 0;
			$.ajax({
		         type: "post",
		         url: chgUrl(site+"/refeshCity.do"),
		         dataType: "html",
		         data: {
					areaId: obj.value
		         },
		         success: function(data, textStatus) {
		        	 //alert(data);
		        	if(data){
		        		var city = data.split(";");
		        		
		        		for(var i=0;i<city.length;i++){
		        			var _opt = city[i];
		        			var _opts = _opt.split(",");
		        			var option = new Option(_opts[1],_opts[0]);
			        		document.getElementById(targetObjId).options.add(option);
		        		}
		        		try{
		        			refreshCityId(document.getElementById(targetObjId));
		        		}catch(err){
		        		}
		        		try{
		        			refreshEntByCity(document.getElementById(targetObjId),site,'paymentType','entId');
		        		}catch(err){
		        		}
		        		try{
		        			refreshtvs(document.getElementById(targetObjId),site);
		        		}catch(err){
		        			
		        		}
		        	}
		         }
		     });
		}
	}
}
function refreshAreaId(obj){
	var tcity = document.getElementById("_areaId");
	var area = document.getElementById("parentAreaId");
	var city = document.getElementById("areaId");
	if(area&&obj){
		area.value=obj.value;
	}
	if(tcity&&city){
		city.value=tcity.value;
	}
}
function refreshCityId(obj){
	var tarea = document.getElementById("_parentAreaId");
	var area = document.getElementById("parentAreaId");
	var city = document.getElementById("areaId");
	if(area&&tarea){
		area.value=tarea.value;
	}
	if(obj&&city){
		city.value=obj.value;
	}
}
function refreshEntByPayType(obj,site,cityId,targetObjId){
	if(obj&&obj.value){
		if(obj.value!=""){
			
			document.getElementById(targetObjId).options.length = 0;
			$.ajax({
		         type: "post",
		         url: chgUrl(site+"/setting/refreshEnt.do"),
		         dataType: "html",
		         data: {
					paymentType: obj.value,
					areaId:document.getElementById(cityId).value
		         },
		         success: function(data, textStatus) {
		        	 //alert(data);
		        	if(data){
		        		var ent = data.split(";");
		        		for(var i=0;i<ent.length;i++){
		        			var _opt = ent[i];
		        			var _opts = _opt.split(",");
		        			var option = new Option(_opts[1],_opts[0]);
			        		document.getElementById(targetObjId).options.add(option);
		        		}
		        		
		        	}
		         }
		     });
		}
	}
}
function refreshEntByCity(obj,site,payTypeId,targetObjId){
	
	if(obj&&obj.value){
		if(obj.value!=""){
			
			document.getElementById(targetObjId).options.length = 0;
			$.ajax({
		         type: "post",
		         url: chgUrl(site+"/setting/refreshEnt.do"),
		         dataType: "html",
		         data: {
					paymentType: document.getElementById(payTypeId).value,
					areaId:obj.value
		         },
		         success: function(data, textStatus) {
		        	 //alert(data);
		        	if(data){
		        		var ent = data.split(";");
		        		for(var i=0;i<ent.length;i++){
		        			var _opt = ent[i];
		        			var _opts = _opt.split(",");
		        			var option = new Option(_opts[1],_opts[0]);
			        		document.getElementById(targetObjId).options.add(option);
		        		}
		        		
		        	}
		        	try{
		        		chgBillImg(site,targetObjId,document.getElementById(payTypeId).value);
		        	}catch(error){}
		         }
		     });
		}
	}
}
function quickSetting(site,paymentType){
	var iWidth =480;    
    var iHeight = 432;  
    var iTop = (window.screen.height-iHeight)/2;
    var iLeft = (window.screen.width-iWidth)/2;
	window.showModalDialog(site+"/setting/editframe.do?paymentType="+paymentType,"","dialogLeft="+iLeft+"px;dialogTop="+iTop+"px;dialogHeight="+iHeight+"px;dialogWidth="+iWidth+"px;status=no;scroll=no;resizable=no;help=no");
}
function registerAutoComplete(inp, div,site,payType) {
		//var billNumber = document.getElementById(inp).value;
	  //alert(instanceId);
		var myServer = site+"/jf/autoComplete.do";
		myDataSource = new YAHOO.widget.DS_XHR(myServer, ["\n", "\t"]);
		myDataSource.responseType = YAHOO.widget.DS_XHR.TYPE_FLAT;
		myDataSource.scriptQueryAppend="paymentType="+payType;
		myDataSource.maxCacheEntries = 0;
		myDataSource.queryMatchCase = false;
		//alert("1");
		myAutoComp = new YAHOO.widget.AutoComplete(inp,div, myDataSource); 
		myAutoComp.animVert = false;
		//myAutoComp.delimChar = [";",";"];
		myAutoComp.animHoriz = false;
		myAutoComp.animSpeed = 0;
		myAutoComp.maxResultsDisplayed = 100;
		myAutoComp.queryDelay = 0.1;
		myAutoComp.minQueryLength = 1;
		myAutoComp.useIFrame = true;
		myAutoComp.typeAhead = false;

		myAutoComp.allowBrowserAutocomplete = false;
		//alert("2");
		//myAutoComp.itemSelectEvent.subscribe(fnCallback);
		myAutoComp.formatResult = function(aResultItem, sQuery) { 
		    var sResult = aResultItem[0]; 
		    //var sex = aResultItem[1]; 
		    //alert(aResultItem[0]);
		    if(sResult) {
		        return  sResult;
		    } else {
		        return "";
		    }
		};
	}
function fnCallback(e,args){
	//alert(args[2]);
	try{
		document.getElementById("billNumber").value=args[2].split("户主：")[0];
	}catch(err){
	}
}
function registerBillNumber(site,paymentType){
	$("#billNumber").autocomplete(site+'/jf/jqautoComplete.do', {
		width: 350,
		dataType:"json",
		minChars: 1,
		extraParams: {'paymentType':paymentType}, 
		parse: function(data) {
			return $.map(data, function(row) {
 				return {
 					data: row,
 					value: row.billNumber,
 					result: row.billNumber };
 			});
		},
		formatItem: function(row, i, max) {
            return  row.billNumber+" (<strong>户主: " + row.hoster + "</strong>)"+" (<strong>住址: " + row.payAddress + "</strong>)";
       }
	}).result(function(event, row) {
		if(paymentType=="6"){
			$('#billNumber').val(row.hoster);
		}else{
			$('#billNumber').val(row.billNumber);
		}
		if(paymentType=="5"){
			$('#carOwner').val(row.hoster);
			$('#vehicleNumber').val(row.vehicleNumber);
			$('#carframeNumber').val(row.carframeNumber);
			$('#engineNumber').val(row.engineNumber);
		}
		$('#payAddress').val(row.payAddress);
	});
}
function registerMobilePhone(site,paymentType){
	$("#telnum_1000").autocomplete(site+'/jf/jqautoComplete.do', {
		width: 350,
		dataType:"json",
		minChars: 1,
		extraParams: {'paymentType':paymentType}, 
		parse: function(data) {
			return $.map(data, function(row) {
 				return {
 					data: row,
 					value: row.billNumber,
 					result: row.billNumber };
 			});
		},
		formatItem: function(row, i, max) {
            return  row.billNumber+" (<strong>机主: " + row.hoster + "</strong>)"+" (<strong>住址: " + row.payAddress + "</strong>)";
       }
	}).result(function(event, row) {
		$('#telnum_1000').val(row.billNumber);
	});
}
function expWord(payType,billId,site){
	var downFrame = document.getElementById("downFrame");
	if(downFrame){
		downFrame.src=site+"/jf/expWord.do?payType="+payType+"&billId="+billId;
	}
}
function chgBillImg(site,entId,paymentType){
	//alert(site+entId+paymentType);
	var billChargeImg = document.getElementById("billChargeImg");
	var billChargeImga = document.getElementById("billChargeImga");
	var entObj = document.getElementById(entId);
	if(billChargeImg&&billChargeImga&&entObj){
	if(entObj.value==""){
		billChargeImg.src=site+"/images/no_billImage.jpg";
		billChargeImga.href=site+"/images/no_billImage.jpg";
		billChargeImga.title="缴费票示例图";
		return;
	}
	var paymentTypeName="";
	if(paymentType==0){
		paymentTypeName="水费";
	}
	if(paymentType==1){
		paymentTypeName="电费";
	}
	if(paymentType==2){
		paymentTypeName="燃气费";
	}
	if(paymentType==3){
		paymentTypeName="固话费";
	}
	if(paymentType==4){
		paymentTypeName="移动通讯费";
	}
	if(paymentType==5){
		paymentTypeName="交通罚款费";
	}
	if(paymentType==6){
		paymentTypeName="物业费";
	}
	if(paymentType==7){
		paymentTypeName="有线电视费";
	}
	if(paymentType==8){
		paymentTypeName="采暖费";
	}
	var checkText=$("#"+entId).find("option:selected").text();
		var url=site+"/jf/viewbillimg.do?entId="+entObj.value+"&payType="+paymentType;
		billChargeImg.src=chgUrl(url);
		billChargeImga.href=chgUrl(url)+"&a=b.jpg";
		billChargeImga.title=checkText+paymentTypeName+"缴费票";
	}
}
function refreshtvs(cityObj,site){
	var othertvs = document.getElementById("othertvs");
	if(othertvs&&cityObj&&cityObj.value!=""){
		othertvs.value="";
		document.getElementById("billMoney").value="";
		$.ajax({
	        type: "post",
	        url: chgUrl(site+"/yxds/gettvs.do"),
	        dataType: "json",
	        data: {
				type:0,
				areaId:cityObj.value
	        },
	        success: function(data, textStatus){
	       	 //alert(data);
	       	if(data){
	       		var htmlBuffer="";
	       		for(var i=0;i<data.length;i++){	       			
	       			var html = data[i].televisionName+"（"+data[i].televisionMoney+"元/月）<input type='radio' id='tvgroup"+data[i].id+"'"+" name='tvgroup' value='"+data[i].id+","+data[i].televisionName+","+data[i].televisionMoney+"'";
	       			if(i==0){
	       				html = html+" checked='checked'";
	       			}
	       			html = html + " onfocus='tzje(this);'/>&nbsp;&nbsp;";
	       			htmlBuffer = htmlBuffer +html;
	       			
	           	}
	       		htmlBuffer = htmlBuffer +"<a href='javascript:viewMore();'>更多</a>";
	       		document.getElementById("tvgroups").innerHTML=htmlBuffer;
	       		retzje();
	       		
	       	}
	        }
	    });
	}
}
function resetPassword(site){
	var oldpass = document.getElementById("oldpass").value;
	var newepass1 = document.getElementById("newepass1").value;
	var newepass2 = document.getElementById("newepass2").value;
	if(oldpass==""){
		alert("请输入原密码");
		return;
	}
	if(newepass1==""||newepass1.length<4||newepass1.length>20){
		alert("新密码至少4个长度,最多20个长度");
		return;
	}
	if(newepass2==""||newepass2.length<4||newepass2.length>20){
		alert("确认密码至少4个长度,最多20个长度");
		return;
	}
	if(newepass1!=newepass2){
		alert("两次密码不一致,请确认");
		return;
	}
	$.ajax({
        type: "post",
        url: chgUrl(site+"/resetPassword.do"),
        dataType: "json",
        data: {
			oldpass:oldpass,
			newepass1:newepass1,
			newepass2:newepass2
        },
        success: function(data, textStatus){
        	
       	if(data){
       		if(data.result=="ok"){
       			alert("密码已修改");
       			hiddenDiv('passwordwin');
       		}else{
       			alert(data.result);
       		}
       	}
       	
        }
    });
}
function resetMobile(site){
	if(document.getElementById("newmobile").value==""){
		alert("请输入手机号码");
		return;
	}
	if(checktel("newmobile")){
		$.ajax({
	        type: "post",
	        url: chgUrl(site+"/resetMobile.do"),
	        dataType: "json",
	        data: {
				newmobile:document.getElementById("newmobile").value
	        },
	        success: function(data, textStatus){
	       	if(data){
	       		alert(data.result);
	       	}
	       	hiddenDiv('chgmobilewin');
	        }
	    });
	}
}