
	   	var GetURLParameter = function (sParam) {
	   	    var sPageURL = window.location.search.substring(1);
	   	    var sURLVariables = sPageURL.split('&');
	   	    for (var i = 0; i < sURLVariables.length; i++){
	   		     var sParameterName = sURLVariables[i].split('=');
	   	        if (sParameterName[0] == sParam) {
	   	            return sParameterName[1];
	   	        }
	   	    }
	   	}

	   	function fechaPersonalizada() {
	   		var locacion = document.location.href;
			var fechaUrl = GetURLParameter('fecha');
			setTimeout(function(){
		   	if(locacion.indexOf('fecha')>0) {  
		   			
		   			$(".fechaPersonalizada").html(fechaUrl.replace(/\-/g, " "));
		   			/*
			   			$(".fechaPersonalizada").text(function(i,v){
			   			    return unescape(v);
			   			});
		   			*/
		   	
		   	}
			},300);
	   	}

	   	$(document).ready(function(){
	   	
	   		fechaPersonalizada();
	   		/*
	   		if($(".firmaDigital").length){
	   			
		   		 $(".portadaTabla").each(function() {
					 	var portada = $(this);
			 
				       portada.find("div.registro").insertAfter(portada.find(".logoCelda td .logo"))
	
				 })
	   		}*/
	   		$(window).resize(function() {
	   			
	   			/*
	   			var width = $(window).width();
	   	
	   			
	   		 if (width >= 1025 && !$(".firmaDigital").length) {
	   			
	   			 $(".portadaTabla").each(function() {
	   				 	var portada = $(this);
	   				 	portada.find("div.registro").prependTo(portada.find(".noRegistro td"));
	   				
	   			 });
	   		 }
	   		 if (width <= 1024 || $(".firmaDigital").length) {
	   			 $(".portadaTabla").each(function() {
	   				 	var portada = $(this);
	   		 
	   			       portada.find("div.registro").insertAfter(portada.find(".logoCelda td .logo"))

	   			 });
	   			
	   		 }*/
	   	   var 	numberAcountInvestorI  = GetURLParameter('numberAcountInvestor');
	   	   	if( numberAcountInvestorI  == "00100037149" ) {  
	   		    $(".disponiblecontrato").remove();
	   		    $(".noDisponibleContrato").show();
	   			console.log(idLinkTabla);
	   	   	}else{
	   	   		$(".disponiblecontrato").css("opacity", "1");
	   	   	}
	   		 
	   	 });	 
	   	$(window).resize();	
	   	});