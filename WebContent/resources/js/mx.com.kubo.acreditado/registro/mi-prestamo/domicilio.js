console.log("domicilio.js");

ActividadEconomica.Domicilio.input_text_oncomplete = function(xhr, status, args)
{
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var STREET = 6;
	var ADDRESS_NUMBER = 7;
	var APT_NUMBER = 8;
	var MX_MANZANA = 9;
	var MX_LOTE    = 10;
	var FIRST_STREET_REFERENCE  = 11;
	var SECOND_STREET_REFERENCE = 12;
	var DESCRIPTION = 13;
	
	var street;
	var address_number;
	var apt_number;
	var mx_manzana;
	var mx_lote;
	var first_street_reference;
	var second_street_reference;
	var description;
	
	var field_index = args.field_index;
	var address_type_id = args.address_type_id;
	
	console.log("Domicilio.input_text_oncomplete(): " + field_index);
	
	switch(field_index)
	{
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
	
	console.log(" > address_type_id = " + address_type_id);	
	
	switch(address_type_id)
	{
		case NEGOCIO:			
			$("div#business-domicilio").find("a.init-change-control").trigger("click");	
		break;
		
		case EMPLEO:
			$("div#employment-domicilio").find("a.init-change-control").trigger("click");
		break;
	}		
};

ActividadEconomica.Domicilio.change_control_oncomplete = function(xhr, status, args)
{
	var update_OK = args.update_OK;
	
	console.log("Domicilio.change_control_oncomplete(): ");
	console.log(" > update_OK = " + update_OK);	
};

ActividadEconomica.Domicilio.init_focus_date = function(address_type_id)
{
	var focus_date_ID;
	
	switch(address_type_id)
	{		
		case 3:
			focus_date_ID = "a#focus-address-business";			
		break;
		
		case 4:			
			focus_date_ID = "a#focus-address-employment";
		break;
		
		default: break;
	}
	
	console.log("Domicilio.init_focus_date(): " + focus_date_ID);	
	
	$(focus_date_ID).trigger("click");
};

ActividadEconomica.Domicilio.focus_on_complete = function(xhr, status, args)
{
	var focus_date = args.focus_date;	
	
	console.log("Domicilio.focus_on_complete(): "+ focus_date);
};
