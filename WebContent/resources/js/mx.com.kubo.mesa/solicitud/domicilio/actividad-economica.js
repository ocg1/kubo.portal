console.log("actividad-economica.js");

ActividadDomicilio.init = function()
{	
	$("#container-employment-address-TOKEN").toggle("slow");			
};


ActividadDomicilio.zipcode_oncomplete = function(xhr, status, args) 
{				
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var zipcode = args.zipcode;
	var zipcode_ENABLED = args.zipcode_ENABLED;	
	var address_type_id = args.address_type_id;
	
	console.log("ActividadDomicilio.zipcode_oncomplete(): ");
	console.log("> zipcode:   " + zipcode);
	console.log("> zipcode_ENABLED:   " + zipcode_ENABLED);
	console.log("> address_type_id:   " + address_type_id);
	
	if(zipcode_ENABLED)
	{
		switch(address_type_id)
		{
			case NEGOCIO:
			case EMPLEO:
				$("div#remote-domicilio-actividad").find("a.init-lista-neighborhood").trigger("click");
			break;
		}		
	}	
};

ActividadDomicilio.lista_neighborhood_oncomplete = function(xhr, status, args)
{
	var isValid         = args.isValid;
	var address_type_id = args.address_type_id;		
	var state_name      = args.state_name;
	var town_name       = args.town_name;
	
	console.log("ActividadDomicilio.lista_neighborhood_oncomplete(): ");
	console.log("> address_type_id = " + address_type_id);
	console.log("> isValid    = " + isValid);	
	console.log("> town_name  = " + town_name);
	console.log("> state_name = " + state_name);
	
	if(isValid)
	{
		$("#activity-town-id").find("input").val(town_name);
		$("#activity-state-id").find("input").val(state_name);
		
	} else {
		
		$("#activity-town-id").find("input").val("");
		$("#activity-state-id").find("input").val("");
	}

};

/*
ActividadDomicilio.init_neighborhood = function(address_type_id)
{
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	switch(address_type_id)
	{
		case NEGOCIO:
			$("div#business-domicilio").find("a.init-neighborhood").trigger("click");
		break;
		
		case EMPLEO:
			$("div#employment-domicilio").find("a.init-neighborhood").trigger("click");
		break;
	}	
	
	console.log("ActividadDomicilio.init_neighborhood(): OK");		
};
*/

ActividadDomicilio.neighborhood_id_oncomplete = function(xhr, status, args)
{
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var neighborhood_id = args.neighborhood_id;
	var address_type_id = args.address_type_id;
	
	console.log("ActividadDomicilio.neighborhood_id_oncomplete(): ");
	console.log(" > neighborhood_id = " + neighborhood_id);	
	
	switch(address_type_id)
	{
		case NEGOCIO:			
			$("div#business-domicilio").find("a.init-change-control-neighborhood").trigger("click");	
		break;
		
		case EMPLEO:
			$("div#employment-domicilio").find("a.init-change-control-neighborhood").trigger("click");
		break;
	}			
};

ActividadDomicilio.address_field_oncomplete = function(xhr, status, args)
{
	var input_text_id    = args.input_text_id;
	var input_text_value = args.input_text_value;
	
	console.log("ActividadDomicilio.address_field_oncomplete(): ");
	console.log(" > input_text_id    = " + input_text_id);
	console.log(" > input_text_value = " + input_text_value);
};

ActividadDomicilio.update_address = function(xhr, status, args)
{
	var update_OK     = args.update_OK;
	var address_TOKEN = args.address_TOKEN;
		
	console.log("ActividadDomicilio.update_address(): ");
	console.log(" > update_OK     = " + update_OK);
	console.log(" > address_TOKEN = " + address_TOKEN);
	
	$("#container-employment-address-TOKEN").hide("slow");
		
	closeFancy();

	$(document).scrollTop($("#container-employment-address-TOKEN").offset().top);
	
	$("div#remote-domicilio-actividad").find("a.init-domicilio-actividad").trigger("click");
/*		
	$("div#motivo_edicion_vivienda").find("textarea").val("");
	
*/	
};

ActividadDomicilio.cancelar = function()
{
	$("#container-employment-address-TOKEN").hide("slow");
	
	$(document).scrollTop($("#container-employment-address-TOKEN").offset().top);
	
/*	
	
+ 876
2 ('msgprocessing',false);		
	
	$("div#motivo_edicion_vivienda").find("textarea").val("");
	
	closeFancy();
*/
};

ActividadDomicilio.domicilio_actividad_oncomplete = function(xhr, status, args)
{
	var address_TOKEN = args.address_TOKEN;
	
	console.log("ActividadDomicilio.domicilio_actividad_oncomplete(): ");
	console.log(" > address_TOKEN = " + address_TOKEN);
};

