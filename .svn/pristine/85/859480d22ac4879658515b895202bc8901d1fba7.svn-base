console.log("employment.js");

function listener_update_employment(xhr, status, args)
{
	console.log("MiPrestamo.listener_update_employment(): INIT");
	
	var same_address_ENABLED = args.same_address_ENABLED;
	var employment_id        = args.employment_id;
	
	console.log("> same_address_ENABLED: " + same_address_ENABLED);
	console.log("> employment_id: " + employment_id);
	
	closeFancy();
	
	generateStringAddress($('.mapClass'));
	
	$("#update-employment-list").trigger("click");
	
}

function listener_update_employment_list(xhr, status, args)
{
	console.log("MiPrestamo.listener_update_employment_list(): OK");
	
	fieldCount();
	
	MiPrestamo.init_event_listeners();	
	MiPrestamo.ocultar_address_HOME();
	
	initialize($(".zipcode"));
	conjuntoCombos();
}

MiPrestamo.setMismo_domicilio_ENABLED = function(vivienda_CHANGED, beneficiarie_id)
{
	var log_INFO = "Beneficiario.setMismo_domicilio_ENABLED(): " + vivienda_CHANGED;
	
	console.log(log_INFO);
	
	var mismo_domicilio_ID = "#mismo_domicilio_" + beneficiarie_id;
	
	if(vivienda_CHANGED)
	{			
		$(mismo_domicilio_ID).find("input:radio[value~='N']").attr("checked","checked").trigger("click");
		
	} else {
		
		$(mismo_domicilio_ID).find("input:radio[value~='S']").attr("checked","checked").trigger("click");
	}
};