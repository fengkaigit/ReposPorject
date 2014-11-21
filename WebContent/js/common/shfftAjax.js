/**
 * 网站AJAX代码
 * 
 * 同步方法和异步方法
 * 
 * @author QIAOYU
 */
jQuery.shfftAjaxHandler={
	
	AJAX_LOAD_SUCCESS:"success",
	
	AJAX_RES_SUCCESS:"200",
	
	/**
	 * 异步AJAX方法
	 * 
	 * @param requestUrl 请求URL
	 * 
	 * @param params 请求参数
	 * 
	 * @param method 请求方法POST or GET
	 * 
	 * @param returnType 数据返回类型json,html,xml,text,script
	 * 
	 * @param callBack 请求成功后回调处理函数
	 */
	ajaxRequest:function(requestUrl,params,method,returnType,callBack){
        $.ajax({
            url:requestUrl,
            type:method,
            cache:false,
            async:true,
            dataType:returnType,
            data:params,
            success:function(data){
            	callBack(data);
            },
            error: function(XMLHttpRequest){
            }
        });
	},
	
	/**
	 * 同步AJAX方法
	 * 
	 * @param requestUrl 请求URL
	 * 
	 * @param params 请求参数
	 * 
	 * @param method 请求方法POST or GET
	 * 
	 * @param returnType 数据返回类型json,html,xml,text,script
	 * 
	 * @param callBack 请求成功后回调处理函数
	 */
	ajaxSynRequest:function(requestUrl,params,method,returnType,callBack){
	
        $.ajax({
            url:requestUrl,
            type:method,
            cache:false,
            async:false,
            dataType:returnType,
            data:params,
            success:function(data){
            	callBack(data);
            },
            error: function(XMLHttpRequest){
            }
        });
	},
	
	ajaxLoad:function(requestUrl,data,elementId,callBack){
	    $("#"+elementId).load(requestUrl,data,callBack);
	}
};