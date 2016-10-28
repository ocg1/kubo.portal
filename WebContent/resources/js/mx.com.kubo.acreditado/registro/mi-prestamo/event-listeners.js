console.log("event-listeners.js");

MiPrestamo.init_event_listeners = function()
{			 
	console.log("MiPrestamo.init_event_listeners(): INIT");
	
	$('.mapClass').bind(
    {							
		change: function(event) 
		{										
			//generateStringAddress(this);
			//event.preventDefault(); 
		}
	});
				    
	$(".validatorClass").bind("change blur", function(event) 
	{												
		fieldCount();
		event.preventDefault();
		
		if($(this).val() != "" )
		{
			$(this).removeClass("requiredClass");
		}
			
	});			
	
	$("h2.expand_heading").toggle(function()
	{        	
		$(this).children().removeClass("change");	 
		
     }, function () {
    	 
        $(this).children().addClass("change");
    });
			
    $("h2.expand_heading").click(function(event)
    {   
		$(this).next(".toggle_container").slideToggle("slow");
		
		event.preventDefault();
    });
    
 	$('.miPrestamoNX').click(function(){  		
 		if( $("#area").val() == "L") {
 			buttonClick = true;
			revision_pasos(); 
			console.log("saliendo docs");
		}else {
			$("#hdNext\\:siguienteDocPLD").click();
		}
 	}); 
 	
 	console.log("MiPrestamo.init_event_listeners(): OK");		   
				    
/* 
	$(".countCharact").charCount({
		limit: 140,
		warning: 20,
		css: 'counter',
		cssWarning: 'warning',
	    counterText: 'Caracteres restantes: '
	});
*/
};