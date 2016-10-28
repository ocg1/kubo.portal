var mapa_edicion_vivienda = null;
var mapa_geocoder = null;
var mapa_marker   = null;
var mapa_lema     = null;
var mapa_mexico = new google.maps.LatLng(19.402487, -99.167404);

function init_mapa_edicion_vivienda(coordenadas) 
{		
	var mapa_canvas = document.getElementById("mapa_canvas");	
	
	var mapa_config = {
			zoom: 10, 
			mapTypeId: google.maps.MapTypeId.ROADMAP
	};			

	if(mapa_canvas)
	{
    	mapa_edicion_vivienda = new google.maps.Map(mapa_canvas, mapa_config);    	    	    
    	mapa_geocoder         = new google.maps.Geocoder();
    	
    	init_mapa_marker(coordenadas);
    	
    	google.maps.event.addListener(mapa_edicion_vivienda, 'click', listener_mapa_ubicacion);
    	
    	console.log("init_mapa_edicion_vivienda(): OK");
	}		
}	

function init_mapa_marker(coordenadas)
{
	console.log("init_mapa_marker(): " + coordenadas);
	
	if(coordenadas.length > 0)
	{
		var coordenadas_TOKEN = coordenadas.split(",", 3);
		
		var latitud   = coordenadas_TOKEN[0];
		var longitud  = coordenadas_TOKEN[1];
		mapa_lema     = coordenadas_TOKEN[2];
		
		var mapa_marker_position = new google.maps.LatLng(latitud, longitud);
		
	    mapa_edicion_vivienda.setCenter(mapa_marker_position);
	    mapa_edicion_vivienda.setZoom(17);
	    
	    var mapa_marker_config = {
	    		map:      mapa_edicion_vivienda, 
	    		position: mapa_marker_position, 
	    		title:    mapa_lema
	    };
	    
        mapa_marker = new google.maps.Marker(mapa_marker_config);
        
	} else {
		
		 mapa_marker = new google.maps.Marker({});
		 mapa_edicion_vivienda.setCenter(mapa_mexico);
	}
}
