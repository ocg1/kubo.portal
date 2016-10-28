Historial.car_is_principal_oncomplete = function(xhr, status, args)
{
	var car_is_principal = args.car_is_principal;
	var update_OK = args.update_OK;
	
	console.log("Historial.car_is_principal_oncomplete(): ");
	console.log(" > car_is_principal = " + car_is_principal);
	console.log(" > update_OK = " + update_OK);
	conjuntoCombos();
};


Historial.init_car_company_name = function()
{	
	var company_name = $("#acSimple3_input").val();
	
	console.log("Historial.init_car_company_name(): " + company_name);		
	
	var remote_command = "div#historial-remote-command";
	
	$(remote_command).find("#init-car-company-name").val(company_name).trigger("click");
};

Historial.car_company_oncomplete = function(xhr, status, args)
{
	var car_company = args.car_company;
	var update_OK   = args.update_OK;
	
	console.log("Historial.car_company_oncomplete(): ");
	console.log(" > car_company = " + car_company);
	console.log(" > update_OK   = " + update_OK);
};