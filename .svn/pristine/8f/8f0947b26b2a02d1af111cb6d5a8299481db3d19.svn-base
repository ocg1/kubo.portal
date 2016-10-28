function listener_mapa_ubicacion(event)
{
	var mapa_ubicacion = $("#mapa_ubicacion").find("input");
	var mapa_ubicacion_NEW = event.latLng;	
	
	var mapa_marker_config = {
			map:      mapa_edicion_vivienda, 
			position: mapa_ubicacion_NEW, 
			title:    mapa_lema
	};
	
	mapa_marker.setMap(null);
	mapa_marker = new google.maps.Marker(mapa_marker_config);
	
	var coordenadas_TOKEN;
	
	coordenadas_TOKEN = mapa_ubicacion_NEW.toString().replace("(", "");
	coordenadas_TOKEN = coordenadas_TOKEN.replace(")", "");
	coordenadas_TOKEN = coordenadas_TOKEN.trim() + ", " + mapa_lema;
	
	$(mapa_ubicacion).val(coordenadas_TOKEN).blur();  
	
	console.log("\nlistener_mapa_ubicacion()");
	console.log("mapa_ubicacion_NEW: " + mapa_ubicacion.val());
	
	return false;
}