console.log("mx.com.kubo.inversionista/registro/beneficiario/zipcode.js");

Beneficiario.Domicilio.zipcode_oncomplete = function(xhr, status, args) 
{				
	var zipcode = args.zipcode;
	var zipcode_ENABLED = args.zipcode_ENABLED;	
	var address_type_id = args.address_type_id;
	var beneficiarie_id = args.beneficiarie_id;
	
	console.log("zipCodeComplete(): ");
	console.log("> zipcode:   " + zipcode);
	console.log("> zipcode_ENABLED:   " + zipcode_ENABLED);
	console.log("> address_type_id:   " + address_type_id);
	console.log("> beneficiarie_id:   " + beneficiarie_id);
	
	$("div#beneficiario-domicilio-" + beneficiarie_id).find("a.init-lista-neighborhood").trigger("click");
};

Vivienda.callback_codigo_postal = function(xhr, status, args) 
{
	console.log("Vivienda.callback_codigo_postal():");
	
	var codigo_postal_valido = args.codigo_postal_valido;
	var is_estado_ENABLED    = args.is_estado_ENABLED;
	
	var delegacion_municipio = args.delegacion_municipio;
	var estado               = args.estado_name;
	var colonias             = args.colonias;
	var beneficiarie_id      = args.beneficiarie_id;
	var vivienda_CHANGED     = args.vivienda_CHANGED;
	
	var lista_colonias_ID           = "#lista_colonias_"       + beneficiarie_id;
	var delegacion_municipio_ID     = "#delegacion_municipio_" + beneficiarie_id;
	var vivienda_estado_ID          = "#vivienda_estado_"      + beneficiarie_id;	
				
	console.log("\tcodigo_postal_valido   = " + codigo_postal_valido);
	console.log("\tis_estado_ENABLED      = " + is_estado_ENABLED);
	console.log("\tdelegacion_municipio   = " + delegacion_municipio);
	console.log("\testado                 = " + estado);		
	console.log("\tcolonias               = " + colonias);
	console.log("\tbeneficiarie_id        = " + beneficiarie_id);
	console.log("\tvivienda_CHANGED       = " + vivienda_CHANGED);	
			
	if(codigo_postal_valido && is_estado_ENABLED)
	{	
		Vivienda.asignar_lista_colonias(lista_colonias_ID, colonias);
				
		$(delegacion_municipio_ID).find("input").val(delegacion_municipio);
		$(vivienda_estado_ID).find("input").val(estado);
		
		Vivienda.showNeighSel(beneficiarie_id);
		
	} else {
		
		$(delegacion_municipio_ID).find("input").val("");
		$(vivienda_estado_ID).find("input").val("");
		
		Vivienda.showNeighText(beneficiarie_id);
	}	
	
	Beneficiario.setMismo_domicilio_ENABLED(vivienda_CHANGED, beneficiarie_id);
};

