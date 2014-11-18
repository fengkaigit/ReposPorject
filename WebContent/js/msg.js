/**
* 作者：WilliamSha
* 发布时间：21:31 2011-4-27
*
* 描述：该插件可以替代JavaScript的alert和confirm对话框
* @param msg 提示信息的对象，第一个元素为title（标题），第二个元素为message（显示信息）
* @param option 设置对话框的操作方式，第一个元素为option,属性值为"alert"或是"confirm",第二个元素为result,表示用户的操作结果，true 确定 false 取消
* @param okFun 点击确定按钮需要执行的方法
* @param cancelFun 点击取消需要执行的方法
*/

$.fn.MessageBox = function(msg,option,okFun,cancelFun){
		
		var obj = $(this);

		if (msg instanceof Object)
		{
			var title = msg.title;
			var message = msg.message;
			if (typeof title == "string" && typeof message == "string")
			{
				obj.find("#title").text(title).parent().parent().find("#message").text(message);
			}
		}
		if (option instanceof Object && typeof option.option == "string")
		{
			if (option.option == "confirm")
			{
				obj.find("#ok").click(function(){
					obj.fadeOut("slow");
					option.result = true;
					okFun();
				});
				obj.find("#cancel").click(function(){
					obj.fadeOut("slow");
					option.result = false;
					cancelFun();
				});
			} 
			else
			{
				obj.find("#ok").click(function(){
					obj.fadeOut("slow");
					okFun();
				});
				obj.find("#cancel").hide();
			}
		}
		obj.fadeIn("slow");
		obj.children(".title").children("a").click(function(){obj.fadeOut("slow")});

		//设置对话框的拖动
		var x,y;
		obj.mousedown(function(event){
			
			var offset = $(this).offset();
			x=event.clientX-offset.left;
			y=event.clientY-offset.top;
			$(this).mousemove(function(event){
				obj.css("margin", 0).css("cursor", "move").css("left",event.clientX-x).css("top",event.clientY-y);
			});
		}).mouseup(function(){
			obj.unbind("mousemove").css("cursor", "default");
		});

		return obj;
	}