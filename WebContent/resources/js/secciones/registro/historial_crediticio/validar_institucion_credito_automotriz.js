function validar_institucion_credito_automotriz()
{
	var selectedValue = "";
	var is_value_OK   = true;
	
	var selected              = $("input[type='radio'][name='titularCarVig']:checked");
	var institucion_otorgante = $("#institucion_credito_automotriz").find("input");
	
	var institucion_otorgante_name = institucion_otorgante.val();
	
	console.log("institucion_otorgante_name = " + institucion_otorgante_name);
	
	if(selected.length > 0)
	{
	    selectedValue = selected.val();
	
		if(selectedValue == "1")
		{				
			if(institucion_otorgante_name.length == 0)
			{
				alert("Ingresa los datos de la institución de crédito automotriz");
				
				$(institucion_otorgante).focus();
				
				
								
				is_value_OK = false;
			}
			mixPanelValue ('creditoAutomotriz', 'creditoAutomotriz', 'si');
		}else{
			mixPanelValue ('creditoAutomotriz', 'creditoAutomotriz', 'no');
		}
		
	} else {
		
		
		
		alert("Por favor llene todos los campos requeridos.");	
		
		is_value_OK = false;
	}
	
	return is_value_OK;
}