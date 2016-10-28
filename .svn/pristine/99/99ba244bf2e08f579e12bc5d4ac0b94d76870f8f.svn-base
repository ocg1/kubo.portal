console.log("init_mapa_ubicacion.js");

function init_mapa_ubicacion(component)
{	
	var coordenadas = $("#mapa_coordenadas").find("input").val();
	
	var latLong;
	
	console.log("initWithLatLong(): " + coordenadas);
	
	if((coordenadas.split(",")).length > 1)
	{
		mapa_marker.setMap(null);
		latLong = coordenadas.split(",", 3);
		
		if((coordenadas.split(",")).length > 2)
		{
			mapa_lema = latLong[2];
			
		} else {
			
			mapa_lema = "";
		}
			
		initialLocation = new google.maps.LatLng(latLong[0], latLong[1]);
		
	    mapa_edicion_vivienda.setCenter(initialLocation);
	    mapa_edicion_vivienda.setZoom(17);
	    
	    var mapa_marker_config = {
	    		map:      mapa_edicion_vivienda, 
	    		position: mapa_marker_position, 
	    		title:    mapa_lema
	    };
	    
        mapa_marker = new google.maps.Marker({map: mapa_edicion_vivienda, position:initialLocation, title:mapa_lema});
	}else{
		 mapa_marker = new google.maps.Marker({});
		 mapa_edicion_vivienda.setCenter(mexico);
	}
}