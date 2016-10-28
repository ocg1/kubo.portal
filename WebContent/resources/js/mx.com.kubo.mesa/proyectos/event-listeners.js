console.log("mx.com.kubo.mesa/proyectos/event-listeners.js");

Proyectos.init_event_listeners = function()
{	
	console.log("Proyectos.init_event_listeners(): INIT");
	
	$('body').click(function()				
	{
		 if ($(".ui-tooltip").is(':visible')) 
		 {
		 	setTimeout(function()
		 	{
		 		$(".ui-tooltip").hide();
		 		
		 	}, 500);
		 }
	 });
	
	$(window).scroll(function()
	{
		if( $("#pnlContTableCreditos").offset() != null )
		{	 			
			 var offset = $("#pnlContTableCreditos").offset();
							 
			 if((parseInt($(this).scrollTop()) + 92) > parseInt(offset.top))
			 {		 			
				 if($("#dvContInvFlot").is(":hidden"))
				 {		 				
				 	$("#dvContInvFlot").slideDown( "slow" );		 				 
				}
			}
			
			if(parseInt( offset.top ) > (parseInt($(this).scrollTop()) + 92))
			{		 				
				if( !$( "#dvContInvFlot" ).is(":hidden") )
				{		 				
					$( "#dvContInvFlot" ).slideUp( "slow" );		 				
				}
			}
		} 			
	});
	 
	$(".profileId").click(function()
	{
		$("#descProfile").toggle();
	});
	
	$(".clsTerm").click( function()
	{
		/*
		if($(this).hasClass("itemCheck"))
		{
			$(this).removeClass("itemCheck");
			
		} else {
			
			$(this).addClass("itemCheck");
		}
		*/
		
		var this_id  = $(this).attr("id");		
		var this_sec = this_id.split("_")[1];		
		var i = 0;
		
		var valMax = this_id.split("_")[0];
		
		$(".clsTerm").each(function()
		{
			if($(this).hasClass("itemCheck"))
			{
				$(this).removeClass("itemCheck");
			}
		});
/*
		if( i == 0)
		{
			$(this).addClass("itemCheck");
			return;
		}
*/
		$("#cadena2").val("");
		
		$(".clsTerm").each(function()
		{
			
			var this_id_2 = $(this).attr("id");
			
			var this_sec_2 = this_id_2.split("_")[1];
			
			if( parseInt(this_sec) >=  parseInt(this_sec_2) )
			{
				$(this).addClass("itemCheck");
			}
/*
			if($(this).hasClass("itemCheck"))
			{
				
				var valId = $(this).attr("id");
				valId = (valId.split("_")[0]) + "::";
				var val = $("#cadena2").val()+valId;
				$("#cadena2").val(val);
			}
*/
		});
		
		$("#cadena2").val( valMax );
		
		$("#cadena2").trigger("blur");
		
		//alert( $("#cadena2").val() );
		
		// setTimeout(function(){ $("#lnkAction").click(); } , 80);
		
	} );
	
	console.log("Proyectos.init_event_listeners(): OK");
};