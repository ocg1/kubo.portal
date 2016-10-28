function validar_titular_tarjeta_vigente()
{
	console.log("validar_titular_tarjeta_vigente(): ");
	
	var selectedValue = "";
	var is_value_OK   = true;
	
	var selected               = $("input[type='radio'][name='titularTarjetaVig']:checked");	
	var creditcard_company     = $("#creditcard_company").find("input");
	var creditcard_four_digits = $("#creditcard_four_digits").find("input");
	
	var creditcard_company_name       = creditcard_company.val();
	var creditcard_four_digits_value  = creditcard_four_digits.val();
		
	if (selected.length > 0)
	{
	    selectedValue = selected.val();
		
		if(selectedValue == "1")
		{	
			console.log("> creditcard_company_name      = " + creditcard_company_name);
			console.log("> creditcard_four_digits_value = " + creditcard_four_digits_value);
			
			if(creditcard_company_name.length == 0)
			{
				alert("Ingresa los datos de la institución de la tarjeta de crédito");
				
				$(creditcard_company).focus();
				
				
				
				$.scrollTo('#contFrmPre',250, { axis:'y' });
				
				is_value_OK = false;
				
			} else if(creditcard_four_digits_value.length < 4) {
				
				alert("Ingresa los datos de tu tarjeta de credito");
				
				$(creditcard_four_digits).focus();
				
				
				
				$.scrollTo('#contFrmPre',250, { axis:'y' });
				
				is_value_OK = false;
			 }
		}
		
	} else {
		
		
		
		$.scrollTo('#contFrmPre',250, { axis:'y' });				
		
		is_value_OK = false;
	}
	
	return is_value_OK;
}