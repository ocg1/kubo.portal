console.log("mx.com.kubo.inversionista/registro/person-type.js");

PersonaMoral.init = function()
{
	console.log("PersonaMoral.init(): OK");		
	
	asterisk(".labelsStl");
	asterisk(".numberAndTitle");
	asterisk(".titleDisabled");
	resetar_indices();
	
	//$("div#person-type-id").find("input:checked").trigger("click");	
};

PersonaMoral.person_type_on_complete = function(xhr, status, args)
{
	var person_type_id        = args.person_type_id;
	var persona_moral_ENABLED = args.persona_moral_ENABLED;
		
	console.log("PersonaMoral.person_type_on_complete(): " + person_type_id + " " + persona_moral_ENABLED);
	
	$("#init-address-type").val(person_type_id).trigger("click");
	
	closeFancy();
};

PersonaMoral.legal_name_on_complete = function(xhr, status, args)
{
	var legal_name = args.legal_name;
	
	console.log("PersonaMoral.legal_name_on_complete(): " + legal_name);	
};

PersonaMoral.society_type_on_complete = function(xhr, status, args)
{
	var society_type_id = args.society_type_id;
	
	console.log("PersonaMoral.society_type_on_complete(): " + society_type_id);	
};

PersonaMoral.company_rfc_on_complete = function(xhr, status, args)
{
	var mx_company_rfc = args.mx_company_rfc;
	
	console.log("PersonaMoral.company_rfc_on_complete(): " + mx_company_rfc);	
};

PersonaMoral.address_type_on_complete = function(xhr, status, args)
{
	var address_type_id = args.address_type_id;
	var fiscal_ENABLED  = args.fiscal_ENABLED;
	var legal_address_ENABLED = args.legal_address_ENABLED;
	
	asterisk(".labelsStl");
	asterisk(".numberAndTitle");
	asterisk(".titleDisabled");
	resetar_indices();
	
	console.log("PersonaMoral.address_type_on_complete(): ");
	console.log(" > address_type_id = " + address_type_id);
	console.log(" > fiscal_ENABLED  = " + fiscal_ENABLED);
	console.log(" > legal_address_ENABLED = " + legal_address_ENABLED);
};