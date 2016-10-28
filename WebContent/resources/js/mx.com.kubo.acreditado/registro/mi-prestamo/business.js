console.log("business.js");

function listener_update_business(xhr, status, args)
{
	var same_address_ENABLED = args.same_address_ENABLED;
	
	console.log("MiPrestamo.listener_update_business(): " + same_address_ENABLED);
	
	closeFancy();
	
	generateStringAddress($('.mapClass'));
	
	$("#update-business-list").trigger("click");
}

function listener_update_business_list(xhr, status, args)
{
	console.log("MiPrestamo.listener_update_business_list(): OK");
	
	fieldCount();
	
	MiPrestamo.init_event_listeners();	
	MiPrestamo.ocultar_mapa_business();
	MiPrestamo.ocultar_address_HOME();
	
	initialize($(".zipcode"));
	
	conjuntoCombos();
}

MiPrestamo.ocultar_mapa_business = function()
{
	var lista_mapas = $("div#mapa");
	
	console.log("MiPrestamo.ocultar_mapa_business(): INIT");
	console.log("MiPrestamo.ocultar_mapa_business() " + lista_mapas.length);
	
	if(lista_mapas.length == 2)
	{
		$("div#mapa").each(function(index, value)
		{
			console.log(index + " - " + $(this).attr("id"));
			
			if(index == 1)
			{
				$(this).attr("id","map_disabled").css({"border": "0"}).html("");
				
				console.log("MiPrestamo.ocultar_mapa_business() " + index);
			}
		});
	}
	
	console.log("MiPrestamo.ocultar_mapa_business(): OK");
};

