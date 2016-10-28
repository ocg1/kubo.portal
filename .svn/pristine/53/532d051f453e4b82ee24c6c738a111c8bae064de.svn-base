console.log("mx.com.kubo.acreditado/registro/mi-prestamo/domicilio/zipcode.js");

ActividadEconomica.Domicilio.zipcode_oncomplete = function(xhr, status, args) 
{				
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var zipcode = args.zipcode;
	var zipcode_ENABLED = args.zipcode_ENABLED;	
	var address_type_id = args.address_type_id;
	
	console.log("zipCodeComplete(): ");
	console.log("> zipcode:   " + zipcode);
	console.log("> zipcode_ENABLED:   " + zipcode_ENABLED);
	console.log("> address_type_id:   " + address_type_id);
	
	if(zipcode_ENABLED)
	{
		switch(address_type_id)
		{
			case NEGOCIO:
				$("div#business-domicilio").find("a.init-lista-neighborhood").trigger("click");
			break;
			
			case EMPLEO:
				$("div#employment-domicilio").find("a.init-lista-neighborhood").trigger("click");
			break;
		}		
	}	
};

