
Historial.mortage_is_principal_oncomplete = function(xhr, status, args)
{
	var mortgage_is_principal = args.mortgage_is_principal;
	var update_OK = args.update_OK;
	
	console.log("Historial.mortage_is_principal_oncomplete(): ");
	console.log(" > mortgage_is_principal = " + mortgage_is_principal);
	console.log(" > update_OK = " + update_OK);
};

Historial.init_mortage_company_name = function()
{	
	var company_name = $("#acSimple2_input").val();
	
	console.log("Historial.init_mortage_company_name(): " + company_name);		
	
	var remote_command = "div#historial-remote-command";
	
	$(remote_command).find("#init-mortage-company-name").val(company_name).trigger("click");
};

Historial.mortage_company_oncomplete = function(xhr, status, args)
{
	var mortgage_company = args.mortgage_company;
	var update_OK = args.update_OK;
	
	console.log("Historial.mortage_company_oncomplete(): ");
	console.log(" > mortgage_company = " + mortgage_company);
	console.log(" > update_OK = " + update_OK);
};