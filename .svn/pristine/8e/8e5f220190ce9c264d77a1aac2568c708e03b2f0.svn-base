function asignar_address_TOKEN()
{
	console.log("asignar_address_TOKEN");
	
	var street = address_number = neighborhood = town = zipcode = state = search = null;
	
	//var objSection = $(component).parents(".sectionAddress");
	
	var objSection = $("div#container_editar_vivienda_TOKEN");
	
	street = objSection.find(".street");
	
	console.log("generateStringAddress(): " + street.val());
}

function generateStringAddress(component)
{
	var street = address_number = neighborhood = town = zipcode = state = search = null;
	
	//var objSection = $(component).parents(".sectionAddress");
	
	var objSection = $("div#container_editar_vivienda_TOKEN");
	
	street = objSection.find(".street");
	
	console.log("generateStringAddress(): " + street.val());
		
	if(street.val() != undefined )
	{	
		address_number = objSection.find(".address_number");
		neighborhood   = objSection.find(".neighborhood");		
		town           = objSection.find(".town");
		zipcode        = objSection.find(".zipcode");
		state          = objSection.find(".state");
		
		if(street.val().length > 3)
		{
			street = street.val();
		}
			 		
		if(address_number != undefined && address_number.val().length > 0)
		{
			address_number = " " + address_number.val() + ", ";
			
		} else {
			
			street = street + ", ";
		}
				 
		if(neighborhood != undefined && neighborhood.val() != "")
		{
			neighborhood.each(function() 
			{
				neighborhood = $(this).find('option:selected').text() + ",";
			});
			
		} else {
			
			neighborhood = "";
		}
				
		if(town && town.val().length != 0)
		{
			town = " " + town.val() + ",";
		}
			
		if(zipcode.val().length == 5)
		{
			zipcode = " " + zipcode.val() + ",";	
		}
							
		if(state.val() + "" == 'México')
		{
			state = " Estado de México";
		} else {
			
			state = " "+state.val();
		}
							
		search = town + " C.P. " + zipcode + street + address_number + " Coloinia " + neighborhood + state;
				
		if(search.length>0)
		{
			search += ", Mexico ";
			
		} else {
			
			search= "Mexico";
		}
					
		console.log(search);
		
		codeAddress(search, objSection); 
	}
}

function codeAddress(address, objSection) 
{	 
	var newLatLong;
	var objZC = objSection.find(".zipcode");
	
	if(geocoder != null)
	{
	    geocoder.geocode( { 'address': address}, function(results, status) 
	    {
	      if (status == google.maps.GeocoderStatus.OK && mapa_edicion_vivienda!=null) 
	      {
	    	  marker.setMap(null);
	    	  mapa_edicion_vivienda.setCenter(results[0].geometry.location);
	    	  mapa_edicion_vivienda.setZoom(17);
	    	  
	    	  marker = new google.maps.Marker({map: mapa_edicion_vivienda, position: results[0].geometry.location,  title:lema});
	        	
	        if(objSection!=null)
	        {
	        	newLatLong=results[0].geometry.location;
	    		newLatLong=newLatLong.toString().replace("(","");
	    		newLatLong=newLatLong.replace(")","");
	        	objZC.next().val(newLatLong);
	    		objZC.next().blur();	        	
	        }
	        	
	      } else {
	    	  
	        marker.setMap(null);
	        
	        var latlng = new google.maps.LatLng(19.402487, -99.167404);
	        
		    var myOptions = {
		      zoom: 10,
		      center: latlng,
		      mapTypeId: google.maps.MapTypeId.ROADMAP
		    };
		    
		    mapa_edicion_vivienda = new google.maps.Map($("#mapa_canvas"), myOptions);
		    
		    google.maps.event.addListener(mapa_edicion_vivienda, 'click', function(event) 
		    {
		    	var mapa_ubicacion = objZC.find("div#mapa_ubicacion");
		    	
	    		marker.setMap(null);
	    		
	    		marker = new google.maps.Marker({map: mapa_edicion_vivienda, position:event.latLng, title:lema});
	    		
	    		newLatLong = event.latLng;
	    		newLatLong = newLatLong.toString().replace("(","");
	    		newLatLong = newLatLong.replace(")", "");
	    		
	    		mapa_ubicacion.val(newLatLong.trim()).blur();    	
	    		
	    		return false;
	    	  });
		    
	    	geocoder = new google.maps.Geocoder();
		    /*
		    $("#latLong").val(",");
        	$("#latLong").blur();*/
		    
	      }
	    });
	}
}