function validar_institucion_credito_hipotecario()
{
	console.log("\nvalidar_institucion_otorgante()");
	
	var selectedValue = "";
	var is_value_OK   = true;
	
	var selected              = $("input[type='radio'][name='titularMortgageVig']:checked");
	var institucion_otorgante = $("#institucion_credito_hipotecario").find("input");
	
	var institucion_otorgante_name = institucion_otorgante.val();
	
	console.log("institucion_otorgante_name = " + institucion_otorgante_name);
	
	if(selected.length > 0)
	{
	    selectedValue = selected.val();
	
		if(selectedValue == "1")
		{				
			if(institucion_otorgante_name.length == 0)
			{
				alert("Ingresa los datos de la institución de crédito hipotecario");
				
				$(institucion_otorgante).focus();
				
			
				
				$.scrollTo('#contFrmPre',300, { axis:'y' });
				
				is_value_OK = false;				
			}
		}
		
	} else {
		
		
		
		$.scrollTo('#contFrmPre',300, { axis:'y' });
		
		is_value_OK = false;
		
	}
	
	return is_value_OK;
}