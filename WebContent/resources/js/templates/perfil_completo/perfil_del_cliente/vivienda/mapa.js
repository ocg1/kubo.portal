console.log("mapa.js");

function init_mapa()
{
	var config_mapa_eventos =  
	{
		focus: function()
		{
			$('#mapa_edicion_vivienda').slideDown();
		}, 
			
		change: function(event) 
		{
			//generateStringAddress(this);
			//asignar_address_TOKEN();
			//event.preventDefault(); 
		}
	};
	
	mostrar_mapa_edicion_vivienda();
	
	$("div.cerrar_mapa_icon").find("img").trigger("click");
	$('div#vivienda_codigo_postal').find("input").bind(config_mapa_eventos);
	
	console.log("init_mapa(): OK");
}

function mostrar_mapa_edicion_vivienda() 
{		
	console.log("mostrar_mapa_edicion_vivienda(mapa_edicion_vivienda): " + mapa_edicion_vivienda);
	
	var coordenadas = $("#mapa_ubicacion").find("input").val();
			
	if(mapa_edicion_vivienda == null)
	{				
		init_mapa_edicion_vivienda(coordenadas);			
		
	} else {

		/*
		console.log("initWithLatLong(this);");
		
		init_mapa_ubicacion(this);	
		*/
		
		init_mapa_marker(coordenadas);
	}				
	
	$('#mapa_edicion_vivienda').slideDown();	
}

function cerrar_mapa_edicion_vivienda()
{
	$('#mapa_edicion_vivienda').slideUp();
}