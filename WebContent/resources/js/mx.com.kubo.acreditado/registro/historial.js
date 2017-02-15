console.log("historial.js");

Historial.init_focus_date = function(focus_id)
{		
	var remote_command = "div#historial-remote-command";
	
	var focus_date_ID = this.init_focus_date_ID(focus_id);
	
	console.log("Historial.init_focus_date(): " + focus_date_ID);
	
	$(remote_command).find(focus_date_ID).trigger("click");
	conjuntoCombos();
};

Historial.focus_on_complete = function(xhr, status, args)
{	
	var focus_date = args.focus_date;	
	
	console.log("Historial.focus_on_complete(): " + focus_date);
};

Historial.init_focus_date_ID = function(focus_id)
{
	var focus_date;
	
	switch(focus_id)
	{
		case 1:
			focus_date = "a#focus-creditcard";		
			
			ayuda('.ayuda2');
		break;
		
		case 2:
			focus_date = "a#focus-mortage";			
		break;
		
		case 3:
			focus_date = "a#focus-car";			
		break;
		
		case 4:
			focus_date = "a#focus-autorizar";			
		break;		
		
		default: break;
	}
	
	return focus_date;
};

Historial.consulta_buro = function() 
{	
	var myString = $(".autenticador_buro .fecha").text();
	var newString = myString.substr(0, myString.length-11);
	
	$(".autenticador_buro .fecha").text(newString);

	console.log("consulta buro");
    /*
	$('#sendInfodisable').click(function(){
		  $(".autenticador_buro").addClass("sinllenar");
	
	});
	*/
	
	$("#aceptar_consulta input:radio[value=0]").click(function() {
		$(".no_acepto_consulta").hide();
		// $("#sendInfodisable").hide();
		
	    $(".autenticador_buro").removeClass("sinllenar");
	});
	
	$("#aceptar_consulta input:radio[value=1]").click(function() {
		$(".no_acepto_consulta").show();
		// $("#sendInfodisable").show();
		

	});
};

Historial.autorizar_consulta_oncomplete = function(xhr, status, args)
{
	var consulta_ENABLED = args.consulta_ENABLED;
	
	console.log("Historial.autorizar_consulta_oncomplete(): " + consulta_ENABLED);
};

Historial.person_data_oncomplete = function(xhr, status, args)
{
	
	 
	
	if( args.isActive || args.isActive == "true" ){
	
		var displayAction = args.displayAction;
		
		console.log("Historial.person_data_oncomplete(): " + displayAction);
		
		$('#dvWait').fadeOut(500, function()
		{
			//$('#dvRespHistorial').show();
			$('#dvGenSure').hide();
			guardar_nota_del_coach();
			creaConsulta();
			
		});
	
	}else{
		
		if( args.isActive === undefined ){
			
			console.log("Consultas Inactivas: undefined");
			
			
		}else{
		
			console.log("Consultas Inactivas: " + args.isActive );
			
			$(".veloE").fadeIn(300);
		
			$(".consultaHistorialDeshabilitada").addClass("show");
		    
		    $(".consultaHistorialDeshabilitada").show();
			
		}
	}
	
	//ga('send', 'event', 'Leads', 'Antes de verificar');
}

Historial.have_credit_oncomplete = function(xhr, status, args)
{
	var have_credit = args.have_credit;
	
	console.log("Historial.have_credit_oncomplete(): " + have_credit);	
};