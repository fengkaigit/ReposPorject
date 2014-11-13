// JavaScript Document
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
function hiddenDiv(id){
	$("#"+id).css("display","none");
}
function show(id){
	$("#"+id).css("display","block");
}