console.log("estatus/listener_cambio_estatus_TOKEN.js");

function init_cambio_estatus_TOKEN()
{
	if( $("#input_cambio_estatus_TOKEN").val() != "" )
	{
		var estatus_TOKEN = ($("#input_cambio_estatus_TOKEN").val()).split("::")[0];
		
		var flag123 = false;
				
		$.each($("input[type='radio'][name='selectStatus1']"), function(i, obj)
		{
			if(parseInt($(obj).val()) == parseInt(estatus_TOKEN))
			{
				console.log("init_listener_cambio_estatus_TOKEN(): " + estatus_TOKEN);								
				
				$(obj).trigger("click");
				
				flag123 = true;
			}
		});	
		
		
		if(flag123)
		{			
			$.each($("input[type='radio'][name='selectStatus1']"), function(i, obj)
			{
				if(parseInt($(obj).val()) != parseInt(estatus_TOKEN))
				{
					console.log("init_listener_cambio_estatus_TOKEN(): " + estatus_TOKEN);								
							
					$(obj).css("display","none");							
				}
			});				
		}				
	}
	
	console.log("init_cambio_estatus_TOKEN(): OK");
}