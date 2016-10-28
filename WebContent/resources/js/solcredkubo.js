var geocoder;
//var map; //Esta declarada dento de kubo.js para verificar que este inicializada!!
var marker;
var initialLocation;
var mexico;
var browserSupportFlag =  new Boolean();
var lema="";
/*
function initializeMap() {
	var objNext=$("#latlongMap").val();
	var latLongVal=objNext;
	alert(latLongVal);
	latLongVal= latLongVal.split("::");
	var latLong;	
	var newLatLong;
	var myOptions= {zoom: 10,mapTypeId: google.maps.MapTypeId.ROADMAP};
	

	if(document.getElementById("mapSolicitud")){
    	map = new google.maps.Map(document.getElementById("mapSolicitud"),myOptions);
    	
    	geocoder = new google.maps.Geocoder();
	}
	
	if(latLongVal.length>0){
		for(var i = 0; i<latLongVal.length;i++){
			latLong=(latLongVal[i]).split(",",3);
			if(latLong.length>0){
				lema=latLong[2];
				initialLocation = new google.maps.LatLng(latLong[0],latLong[1]);
			    map.setCenter(initialLocation);
			    map.setZoom(17);
		        marker = new google.maps.Marker({map: map,position:initialLocation,title:lema});
			}
		}
	}else{
		 marker = new google.maps.Marker({});
		 map.setCenter(mexico);
	}
}
*/
/*
var map = null;
var geocoder = null;

function initializeMap() {
    map = new GMap2(document.getElementById("mapSolicitud"));
    map.setCenter(new GLatLng(37.4419, -122.1419), 13);
    geocoder = new GClientGeocoder();
}*/

function initializeMap() {
	if(document.getElementById("mapSolicitud")){
			var objNext=$("#latlongMap").val();
			var latLongVal=objNext.split("::");
			var myOptions= {zoom: 10,mapTypeId: google.maps.MapTypeId.ROADMAP};
			//alert(objNext);
			map = new google.maps.Map(document.getElementById("mapSolicitud"),myOptions);
		   
		   //
		    
		// alert("dibujando: "+latLongVal);
		  if(latLongVal.length>0){
				for(var i = 0; i<latLongVal.length;i++){
					
					latLong=latLongVal[i].split(",",3);
					if(latLong.length>0 && latLong[0].length>0 ){
						lema=latLong[2];
						initialLocation = new google.maps.LatLng(latLong[0],latLong[1]);
					    map.setCenter(initialLocation);
					    map.setZoom(10);
				        marker = new google.maps.Marker({map: map,position:initialLocation,title:lema});
					}
				}
			}else{
				marker = new google.maps.Marker({});
				mexico = new google.maps.LatLng(19.402487, -99.167404);
				 map.setCenter(mexico);
			}
		  
		  	if(marker == null){
		  		
		  		marker = new google.maps.Marker({});
				mexico = new google.maps.LatLng(19.402487, -99.167404);
				 map.setCenter(mexico);
		  		
		  	}
		  
		  marker.setMap(map);  
		  //geocoder = new google.maps.Geocoder();
	}
}


$(document).ready(function() {
	
	inicializaModal();
	
	$(window).scroll(function(){
		
		var menu = $('#carterakubo');
		var footerpos = $('#footer').height();
		
		if( window.innerWidth > 1025 ){
		
			if(menu === 'undefined' || menu == null){
				
			}else
				if(menu.offset()!=null){
			//		if(parseInt($(this).scrollTop())>parseInt($('#header').height())){
					if(parseInt($(this).scrollTop())>((menu.offset().top)+10)){
						menu.removeClass('investFrm');
						menu.removeClass('investFrmBottom');
						menu.addClass('investFrmFixed');
					}
					if( $(".container") != null && $(".container").offset() != null && (($(".container").offset().top)>=menu.offset().top)){
						menu.removeClass('investFrmFixed');
						menu.removeClass('investFrmBottom');
						menu.addClass('investFrm');
					}
					if(((menu.height())+10)>=(parseInt($(document).height()-footerpos)+15)){
						menu.removeClass('investFrm');
						menu.removeClass('investFrmFixed');
						menu.addClass('investFrmBottom');
					}
				}
		
		}
		
		
		
		if( $('#dvInvestSeccion').is(":visible") ){
			
			scrollInvestInterno( $('#dvInvestSeccion') );
			
		}
		
	});
	
});

function scrollInvestInterno( menu ){
	
	var tmpInvPos = "";
	
	var footerpos = $('#footer').height();
	
		if(menu === 'undefined' || menu == null){
			
		}else
			if(menu.offset()!=null){
		//		if(parseInt($(this).scrollTop())>parseInt($('#header').height())){
				if(parseInt($(this).scrollTop())>((menu.offset().top)-41)){
					menu.removeClass('investPosition');
					menu.removeClass('investFrmBottomSeccion');
					menu.addClass('investFrmFixedSeccion');
					tmpInvPos="1";
				}
				else if(menu.hasClass('investFrmBottomSeccion') && parseInt($(this).scrollTop())>((menu.offset().top)-71) ){
					menu.removeClass('investPosition');
					menu.removeClass('investFrmBottomSeccion');
					menu.addClass('investFrmFixedSeccion');
					tmpInvPos="2";
				}
				else if( $(".container") != null && $(".container").offset() != null && (($(".container").offset().top)>=menu.offset().top) ){
					menu.removeClass('investFrmFixedSeccion');
					menu.removeClass('investFrmBottomSeccion');
					menu.addClass('investPosition');
					tmpInvPos="3";
				}
				else if(( parseInt($(this).scrollTop()) + menu.height() + 70 )>=(parseInt($(document).height()-footerpos)-25)){
					menu.removeClass('investPosition');
					menu.removeClass('investFrmFixedSeccion');
					menu.addClass('investFrmBottomSeccion');
					tmpInvPos="4";
				}else{
					menu.removeClass('investPosition');
					menu.removeClass('investFrmBottomSeccion');
					menu.addClass('investFrmFixedSeccion');
					tmpInvPos="5";
				}
			}
	
		$("#dvProperInve").empty();
		
		
		
	str =  "******************************************";
	str += "<br />" + "menu.height:"+menu.height();
	str += "<br />" + "$(document).height:"+$(document).height() ;
	str += "<br />" + "footerpos: "+footerpos;
	str += "<br />" + "$(this).scrollTop(): "+$(this).scrollTop();
	str += "<br />" + "$('.container').offset().top: " + $(".container").offset().top ;
	str += "<br />" + "menu.offset().top: " + menu.offset().top ;
	str += "<br />" + "tmpInvPos: " + tmpInvPos ;
	str += "<br />" + "******************************************";
	
	$("#dvProperInve").html( str );
	
}
