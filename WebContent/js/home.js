function changeVerifyFP(url){
	 var imgSrc = $("#loginValidateImg");     
	 //var src = imgSrc.attr("src");     
	 imgSrc.attr("src",chgUrl(url));
}
function chgUrl(url){     
    var timestamp = (new Date()).valueOf();     
    urlurl = url.substring(0,17);     
    if((url.indexOf("&")>=0)){     
        urlurl = url + "×tamp=" + timestamp;     
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
		$('#billNumber').val(row.billNumber);
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