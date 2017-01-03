console.log("creditcard.js");

Historial.creditcard_is_principal_oncomplete = function(xhr, status, args)
{
	var creditcard_is_principal = args.creditcard_is_principal;
	var update_OK = args.update_OK;
	
	console.log("Historial.creditcard_is_principal_oncomplete(): ");
	console.log(" > creditcard_is_principal = " + creditcard_is_principal);
	console.log(" > update_OK = " + update_OK);
	 conjuntoCombos()
};

Historial.creditcard_company_oncomplete = function(xhr, status, args)
{
	/* var creditcard_company = args.creditcard_company;
	var update_OK = args.update_OK;
	
	console.log("Historial.creditcard_company_oncomplete(): ");
	console.log(" > creditcard_company = " + creditcard_company);
	console.log(" > update_OK = " + update_OK);
	*/
	bancoppel();
};

Historial.init_company_name = function()
{	
	var company_name = $("#acSimple_input").val();
	
	console.log("Historial.init_company_name(): " + company_name);
	
	ayudaQuitar();	
	
	var remote_command = "div#historial-remote-command";
	
	$(remote_command).find("#init-company-name").val(company_name).trigger("click");
};

Historial.creditcard_four_digits_oncomplete = function(xhr, status, args)
{
	var creditcard_four_digits = args.creditcard_four_digits;
	var update_OK = args.update_OK;
	
	console.log("Historial.creditcard_four_digits_oncomplete(): ");
	console.log(" > creditcard_four_digits = " + creditcard_four_digits);
	console.log(" > update_OK = " + update_OK);
};