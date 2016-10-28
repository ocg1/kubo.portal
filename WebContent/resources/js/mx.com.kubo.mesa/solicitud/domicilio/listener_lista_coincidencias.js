console.log("listener_lista_coincidencias.js");

function init_listener_lista_coincidencias(nivel, address_type_id)
{
	var coincidencias_OK = false;
	var CASA = 1;
	
	var         alarma_coincidencias = "";
	var listener_lista_coincidencias = "";
	var 		 lista_coincidencias = "";
	
	if(nivel == "NIVEL_1")
	{
		if(address_type_id == "ACTIVIDAD_ECONOMICA")
		{
					 lista_coincidencias = "div#lista-coincidencias-actividad";
					alarma_coincidencias = "#alarma-coincidencias-NIVEL-1";
			listener_lista_coincidencias = "#init-lista-coincidencias-NIVEL-1";			
			
		} else {						
					 lista_coincidencias = "div#lista_coincidencias";
					alarma_coincidencias = "#alarma_coincidencias_NIVEL_1";
			listener_lista_coincidencias = "#listener_lista_coincidencias_NIVEL_1";								
		}
		
		coincidencias_OK = $(alarma_coincidencias).hasClass("coincidencias_OK");
		
		console.log("init_listener_lista_coincidencias(NIVEL_1): " + coincidencias_OK);
	}
	
	if(nivel == "NIVEL_2")
	{
		if(address_type_id == "ACTIVIDAD_ECONOMICA")
		{
			         lista_coincidencias = "div#lista-coincidencias-actividad";
			 	    alarma_coincidencias = "#alarma-coincidencias-NIVEL-2";
			listener_lista_coincidencias = "#init-lista-coincidencias-NIVEL-2";	
			
		} else {						
					 lista_coincidencias = "div#lista_coincidencias";
					alarma_coincidencias = "#alarma_coincidencias_NIVEL_2";
			listener_lista_coincidencias = "#listener_lista_coincidencias_NIVEL_2";						
		}
		
		coincidencias_OK = $(alarma_coincidencias).hasClass("coincidencias_OK");
		
		console.log("init_listener_lista_coincidencias(NIVEL_2): " + coincidencias_OK);
	}
	
	if(coincidencias_OK)
	{
		$(alarma_coincidencias).removeClass("coincidencias_OK");
		
		$(lista_coincidencias).slideUp();
		
	} else {				
		
		$(alarma_coincidencias).addClass("coincidencias_OK");
		
		$(listener_lista_coincidencias).click();
	}		
}

function listener_lista_coincidencias(xhr, status, args)
{
	var address_type_id      = args.address_type_id;
	var numero_coincidencias = args.numero_coincidencias;
	var lista_coincidencias;
	
	var EMPRESA = 3;
	var NEGOCIO = 4;
	
	console.log("listener_lista_coincidencias(): ");
	console.log("     address_type_id: " + address_type_id);
	console.log("numero_coincidencias: " + numero_coincidencias);
	
	switch(address_type_id)
	{	
		case EMPRESA:
		case NEGOCIO:
			lista_coincidencias = "div#lista-coincidencias-actividad";
		break;
		
		default:
			lista_coincidencias = "div#lista_coincidencias";
		break;
	}
	
	$(lista_coincidencias).slideDown();
	
	closeMessageProcessing();
}