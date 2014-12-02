// JavaScript Document
function getPageSize() {
	var xScroll, yScroll;    
	if (window.innerHeight && window.scrollMaxY) {        
		xScroll = window.innerWidth + window.scrollMaxX;        
		yScroll = window.innerHeight + window.scrollMaxY;    
	}else {
		if (document.body.scrollHeight > document.body.offsetHeight) {
			// all but Explorer Mac  
			xScroll = document.body.scrollWidth; 
			yScroll = document.body.scrollHeight;
		} else {
			// Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari 
			xScroll = document.body.offsetWidth;
			yScroll = document.body.offsetHeight
			}
		} 
	var windowWidth, windowHeight; 
	if (self.innerHeight) { 
	// all except Explorer 
	if (document.documentElement.clientWidth) { 
	windowWidth = document.documentElement.clientWidth; 
	} else {
		windowWidth = self.innerWidth;
		}
		windowHeight = self.innerHeight;
		} else { 
		if (document.documentElement && document.documentElement.clientHeight) {
			// Explorer 6 Strict Mode 
			windowWidth = document.documentElement.clientWidth;
			windowHeight = document.documentElement.clientHeight;
			} else {
				if (document.body) { // other Explorers   
				windowWidth = document.body.clientWidth; 
				windowHeight = document.body.clientHeight; 
			}
			}
			} 
			// for small pages with total height less then height of the viewport   
			if (yScroll < windowHeight) {   
			pageHeight = windowHeight;    
			} else {
				pageHeight = yScroll;  
				}   
			// for small pages with total width less then width of the viewport     
			if (xScroll < windowWidth) {
				pageWidth = xScroll; 
			} else { 
			pageWidth = windowWidth;
			} 
			
			arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight); 
		return arrayPageSize;
	}
function openWin(url){
	//var path=document.location.href;
	
	//var arr=path.split('/');
	//alert(arr[7]);
	window.location.href=url;
}

function goback(){
	//var path=document.location.href;
	
	//var arr=path.split('/');
	//alert(arr[7]);
	history.go(-1);
}
function hidden(id){
	$("#"+id).css("display","none");
	
}
function show(id){
	if(document.getElementById(id)&&document.getElementById(id).style.display=="block"){
		hidden(id);
	}else{
		$("#"+id).css("display","block");
	}
}
function showDivWin(id,w,h,pageUrl){
	url=pageUrl
	document.getElementById("bg").style.display ="block";
	$("#"+id).width(w);
	$("#"+id).height(h);
	$("#"+id).css("left",($(document).width()- parseInt(w))/2);
	$("#"+id).css("display","block");
}

function hiddenDiv(id){
	document.getElementById("bg").style.display ="none";
	$("#"+id).css("display","none");
}
function openLoginIn(){
	openWin(url);
}
function turnoff(obj){document.getElementById(obj).style.display="none";}
function addsj(num,id){
	if(num==1000){
		var str='<div style="margin-top:10px;clear:both;" id="tab_'+num+'"><table cellpadding="0" cellspacing="0" width="100%"><tr><td>'
			+'<label  class="label">手机号码：</label></td><td>'
		   +'<input type="text" value="" style="width:140px;"  class="on-show" id="telnum_'+num+'" name="mobiles" maxlength="11"></td>'
 			+'<td><span style=" margin-right:8px;" >充值金额：</span> </td><td>'
			+'<select style="width:65px;margin-top:0px;" class="selectCss" onChange="" id="billMoneys" name="billMoneys">'
              +'<option   value="30" statuscode="00" statusname="" limit="" paymentcart="">30</option>'
               +'<option value="50" statuscode="00" statusname="" limit="" paymentcart="">50</option>'
               +'<option   value="100" statuscode="00" statusname="" limit="" paymentcart="">100</option>'
              +'<option   value="200" statuscode="00" statusname="" limit="" paymentcart="">200</option>'
                +'<option  value="500" statuscode="00" statusname="" limit="" paymentcart="">500</option></select>'
				+'</td><td><span>元</span><span style="display:inline-block;width:10px;margin-left:5px;"></span>'
				+'</td></tr></table></div>';
	}else{
	var str='<div style="margin-top:10px;clear:both;" id="tab_'+tabId+'"><table cellpadding="0" cellspacing="0" width="100%"><tr><td>'
			+'<label class="label">手机号码：</label></td><td>'
		   +'<input type="text" value="" style="width:140px;"  class="on-show" id="telnum_'+tabId+'" name="mobiles" maxlength="11"></td>'
 			+'<td><span style=" margin-right:8px;" >充值金额：</span> </td><td>'
			+'<select style="width:65px;margin-top:0px;" class="selectCss" onChange="" id="billMoneys" name="billMoneys">'
              +'<option   value="30" statuscode="00" statusname="" limit="" paymentcart="">30</option>'
               +'<option value="50" statuscode="00" statusname="" limit="" paymentcart="">50</option>'
               +'<option   value="100" statuscode="00" statusname="" limit="" paymentcart="">100</option>'
              +'<option   value="200" statuscode="00" statusname="" limit="" paymentcart="">200</option>'
                +'<option  value="500" statuscode="00" statusname="" limit="" paymentcart="">500</option></select>'
				+'</td><td><span>元</span><a title="删除此行？" href="javascript:void(0)" class="delTel" onclick="delTab(\'tab_'+num+'\')"></a>'
				+'</td></tr></table></div>';
	tabId=num+1;
	}
	$("#"+id).append(str);
}
function delTab(id){
	$("#"+id).remove();
}
function checktel(id){
	var tel=$("#"+id).val();
	  var reg =/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;	
	  if(tel==""||tel==null){
	  alert('请输入手机号码');
	 // $("#"+id)[0].focus();
	  }else if (!tel.match(reg)){
		alert('手机格式不对，请重新输入');
		// $("#"+id)[0].focus();
		return false
	}
	return true
}
function getCookie(c_name)
{
  var arr,reg = new RegExp("(^|)"+c_name+"=([^;]*)(;|$)");
  if(arr=document.cookie.match(reg))
     return unescape(arr[2]);
  else
     return "";
}