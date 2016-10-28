console.log("domicilio.js");

Beneficiario.Domicilio.init_focus_date = function (beneficiarie_id)
{
	var focus_date_ID = "div#beneficiario-domicilio-" + beneficiarie_id;
	
	console.log("init_focus_date: " + focus_date_ID);
	
	$(focus_date_ID).find("a.init-focus-date").trigger("click");
};

Beneficiario.Domicilio.focus_on_complete = function(xhr, status, args)
{	
	var focus_date = args.focus_date;	
	
	console.log("Domicilio.focus_on_complete(): "+ focus_date);
};

Vivienda.isNumberKey = function (evt)
{		
	 var charCode = (evt.which) ? evt.which : evt.keyCode;
	 
	 var is_code_OK = true;	 	 
	 
	 if (charCode > 31 && (charCode < 48 || charCode > 57))
	 {
		 is_code_OK = false;   	 
	 }
	 
	 console.log("Vivienda.isNumberKey(): " + is_code_OK);
	           
	 return is_code_OK;
};

Vivienda.validateZipCode = function (component_id) 
{			
	console.log("Vivienda.validateZipCode(): " + component_id);
	
	var vivienda_codigo_postal = $("#" + component_id);
	
	var valor = vivienda_codigo_postal.find("input").val();
	
	var is_zip_code_valid = false;
	
	if(valor != "" && valor.length >= 5)
	{	
		is_zip_code_valid = true;
			
	} else if(valor == "") {	   
		
		is_zip_code_valid = false;
		
	} else if(valor.length < 5) {
		
		$(vivienda_codigo_postal).validationEngine('showPrompt', '*Minimo 5 números ej.01010','error','centerRight', true);
		
		is_zip_code_valid = false;		
	}   
	
	console.log("Vivienda.validateZipCode " + is_zip_code_valid);
	
	return is_zip_code_valid;
};

function viviendaEspecial(xhr, status, args) 
{
	Vivienda.callback_vivienda_TOKEN(xhr, status, args);
	
	setTimeout(function()
	{ 
		validacionLlenado2();
		
	}, 1000);
}

Beneficiario.Domicilio.input_text_oncomplete = function(xhr, status, args)
{	
	var NEIGHBORHOOD_TEXT = 5;
	var STREET = 6;
	var ADDRESS_NUMBER = 7;
	var APT_NUMBER = 8;
	var MX_MANZANA = 9;
	var MX_LOTE    = 10;
	var FIRST_STREET_REFERENCE  = 11;
	var SECOND_STREET_REFERENCE = 12;
	var DESCRIPTION = 13;
	
	var neighborhood_text;
	var street;
	var address_number;
	var apt_number;
	var mx_manzana;
	var mx_lote;
	var first_street_reference;
	var second_street_reference;
	var description;	
	
	var field_index     = args.field_index;
	var beneficiarie_id = args.beneficiarie_id;	
	
	console.log("Domicilio.input_text_oncomplete(): " + field_index);
	console.log(" > beneficiarie_id   = " + beneficiarie_id);			
	
	switch(field_index)
	{
		case NEIGHBORHOOD_TEXT:
			neighborhood_text = args.neighborhood_text;
			
			console.log(" > neighborhood_text = " + neighborhood_text);	
		break;
	
		case STREET:
			street = args.street;
			
			console.log(" > street = " + street);	
		break;
	
		case ADDRESS_NUMBER:
			address_number = args.address_number;
			
			console.log(" > address_number = " + address_number);	
		break;
		
		case APT_NUMBER:
			apt_number = args.apt_number;
			
			console.log(" > apt_number = " + apt_number);	
		break;
		
		case MX_MANZANA:
			mx_manzana = args.mx_manzana;
			
			console.log(" > mx_manzana = " + mx_manzana);	
		break;
		
		case MX_LOTE:
			mx_lote = args.mx_lote;
			
			console.log(" > mx_lote = " + mx_lote);	
		break;
		
		case FIRST_STREET_REFERENCE:
			first_street_reference = args.first_street_reference;
			
			console.log(" > first_street_reference = " + first_street_reference);
		break;
		
		case SECOND_STREET_REFERENCE:
			second_street_reference = args.second_street_reference;
			
			console.log(" > second_street_reference = " + second_street_reference);
		break;
		
		case DESCRIPTION:
			description = args.description;
			
			console.log(" > description = " + description);
		break;
	}		
	
	$("div#beneficiario-domicilio-" + beneficiarie_id).find("a.init-change-control").trigger("click");
};

Beneficiario.Domicilio.change_control_oncomplete = function(xhr, status, args)
{
	var update_OK         = args.update_OK;
	var beneficiarie_id   = args.beneficiarie_id;	
	var domicilio_CHANGED = args.domicilio_CHANGED;
	
	console.log("Domicilio.change_control_oncomplete(): ");
	console.log(" > update_OK = " + update_OK);	
	console.log(" > beneficiarie_id   = " + beneficiarie_id);
	console.log(" > domicilio_CHANGED = " + domicilio_CHANGED);
	
	Beneficiario.setMismo_domicilio_ENABLED(domicilio_CHANGED, beneficiarie_id);
};
