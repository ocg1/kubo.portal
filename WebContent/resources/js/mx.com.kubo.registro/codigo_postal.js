console.log("mx.com.kubo.registro/codigo_postal.js");

var zip_code_id_now;

function validateZipCode(component) 
{
	var clientId = component.replace(/:/g, '\\:');
			
	zip_code_id_now = clientId;
	
	var element = $("#" + clientId);
	var valor   = element.val();
	var flagZC  = false;
	
	if(valor !="" && valor.length>=5 )
	{
		$(element).parent().children('.formError').remove();
		
		fieldCount();
		
		flagZC = true;
			
	} else if(valor == "") {	
		
		flagZC = false;
		
	} else if(valor.length < 5) {
		
		$(element).validationEngine('showPrompt', '*Minimo 5 números ej.01010','error','centerRight', true);
		
		flagZC = false;
		
	}   
	
	return flagZC;
}

function zipCodeComplete(xhr, status, args) 
{				
	var EMPLEO  = 4;
	var NEGOCIO = 3;
	
	var zipcode = args.zipcode;
	var zipcode_ENABLED = args.zipcode_ENABLED;	
	
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
}