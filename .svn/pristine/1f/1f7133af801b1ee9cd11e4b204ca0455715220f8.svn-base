console.log("estatus/init_modal_cambio_estatus.js");

function init_modal_cambio_estatus()
{		
	$("#msg_fecha_valida").hide();
	
	var fancy_box_config = { 
		'width'          : '90%',
		'height'         : '130%',
		'padding'        : '15px',
		'margin'         : '0',
		'autoScale'      : 'false',
		'transitionIn'   : 'elastic',
		'transitionOut'  : 'elastic',
		'type' 			 : 'inline',
		'href' 			 : '#dvContChangeStatus',
		'scrolling' 	 : 'auto',
		'centerOnScroll' : 'true',
		'overlayColor'   : '#333333',
		
		'onComplete'     : init_listeners_cambio_estatus
	};
	
	$("#descStatus").fancybox(fancy_box_config);
	
	console.log("init_modal_cambio_estatus(): OK");
}

function init_listeners_cambio_estatus()
{		
	console.log("init_listeners_cambio_estatus(): INIT");
	console.log("init_listeners_cambio_estatus(): " + $("#input_cambio_estatus_TOKEN").val());
	
	$("input[type='radio'][name='selectStatus1']").off();	
	$("input[type='radio'][name='selectStatus1']").change(listener_estatus_selected);
	
	$("#dvBtnChangeStatus").click(listener_editor_estaus_guardar_cambios);
			
	init_cambio_estatus_TOKEN();
	
	console.log("init_listeners_cambio_estatus(): OK");
}

function muestraMotive(tooltip_id)
{
	$(tooltip_id).show("fast");
}


function hideMotive(tooltip_id)
{
	$(tooltip_id).hide("fast");
}

function onstartMotive()
{
	return true;	
}