console.log("init_function_events.js");

function init_function_events()
{	
	/*
	$("#sendInfo").click(function()
	{
		console.log("listener_boton_siguiente: CLICK");
		
		asignar_nota_del_coach();
		validar_historial_crediticio();		
	});
	*/
								
	$("#buroAction").click(function(event)
	{
		$("#callSGBWS").blur();
	});
	
	$(".validatorClass").bind("change blur", function(event) 
	{
		fieldCount();
		
		event.preventDefault();
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
}


/* 
$("#sendInfo").fancybox({
'width' : '90%',
'height' : '100%',
'padding': '0',
'margin' : '0',
'autoScale' : 'false',
'transitionIn' : 'elastic',
'transitionOut' : 'elastic',
'type' : 'iframe',
'scrolling' : 'auto',
'centerOnScroll' : 'true',
'overlayColor': '#333333',
'hideOnOverlayClick': false,
'showCloseButton' : false,
'href': 'secciones/registro/pre_aprobacion.xhtml',
'onClosed':function() {
	if($("#valFlagPrep").val() == "aceptado"){
		$("#hdNext\\:siguientePrep").click();
		$.scrollTo('#header', 800, { axis:'y' });
	}
},
'onStart':function() {
	if(validateNextMenu('S')){
		return window.confirm('¿Estás seguro que todos tus datos son correctos?');
	}else 
		return false;
} 
});
*/