function refreshCity(obj){
	if(obj&&obj.value){
		if(obj.value!=""&&$("#areaId")){
			$.ajax({
		         type: "post",
		         url: chgUrl("refeshCity.do"),
		         dataType: "html",
		         data: {
					areaId: obj.value
		         },
		         success: function(data, textStatus) {
		        	if(data){
		        		var city = data.split(";");
		        		for(var i=0;i<city.length;i++){
		        			var _opt = city[i];
		        			var _opts = _opt.split(",");
		        			var option = new Option(_opts[1],_opts[0]);
			        		$("#areaId").options.add(option);
		        		}
		        		
		        	}
		         }
		     });
		}
	}
}