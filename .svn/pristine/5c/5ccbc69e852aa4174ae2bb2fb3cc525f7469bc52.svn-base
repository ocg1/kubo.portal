function validar_historial_crediticio()
{
	console.log("validar_historial_crediticio(): ");
	/*
	var credito_year_anterior;
	var credito_semestre_anterior;
	
	
		
	credito_year_anterior     = $("input[type='radio'][name='credito_obtenido_year_anterior']:checked").val();
	credito_semestre_anterior = $("input[type='radio'][name='credito_obtenido_semestre_anterior']:checked").val();
	
	console.log("> credito_year_anterior     = " + credito_year_anterior);
	console.log("> credito_semestre_anterior = " + credito_semestre_anterior);

	if(credito_year_anterior == undefined)
	{
		
		
		$.scrollTo('#contFrmPre',50, { axis:'y' });
		
		flagH = false;
	} 
	
	if(flagH && credito_semestre_anterior == undefined)
	{

		
		$.scrollTo('#contFrmPre',100, { axis:'y' });
		
		flagH = false;
	}
	*/
	var flagH = true;
	if(flagH)
	{
		flagH = validar_titular_tarjeta_vigente();
	}	
	
	if(flagH)
	{
		flagH = validar_institucion_credito_hipotecario();		
	} 
	
	if(flagH) 
	{		
		flagH = validar_institucion_credito_automotriz();
	}
	
	if(flagH)
	{		
		fieldCount();
		showSureCont();
	}
	
	return flagH;
}

function remove_error_required_input(selector)
{
	console.log("remove_error_required_input(): " + selector);
	
	$("#" + selector).removeClass('error_required_input');
	
	return true;
	
}