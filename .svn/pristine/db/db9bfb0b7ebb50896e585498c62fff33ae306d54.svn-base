function cambiar_pregunta(pregunta_selected, security_question_id)
{
	console.log("\ncambiar_pregunta(" + pregunta_selected + "," + security_question_id + ")");
	
	var check_icon = $("div#comprobacion_icons").find(".ui-icon-check");
	var error_icon = $("div#comprobacion_icons").find(".ui-icon-closethick");
	
	$("div#listener_pregunta_selected").find("input").val(pregunta_selected).trigger("click");
	$("div#lista_preguntas_seguridad").find("select").val(security_question_id).trigger("change");
	
	mostrar_panel("div#lista_preguntas_seguridad");
	mostrar_panel("div#panel_respuesta_seguridad");
	mostrar_panel("div#panel_respuesta_confirmacion");
	
	mostrar_panel("div#guardar_cambios_preguntas");
	
	ocultar_panel("div#panel_pregunta_description");
	
	check_icon.hide();
	error_icon.hide();
	
	$("#respuesta_seguridad").val("");	
	$("#respuesta_confirmacion").val("");
}

function comprobar_pregunta(pregunta_selected)
{	
	console.log("\ncomprobar_pregunta(" + pregunta_selected + ")");
	
	var check_icon = $("div#comprobacion_icons").find(".ui-icon-check");
	var error_icon = $("div#comprobacion_icons").find(".ui-icon-closethick");
	
	$("div#listener_pregunta_selected").find("input").val(pregunta_selected).click();
	
	$.each( $("div.panel_pregunta_seguridad"), function( key, value) 
	{		
		var pregunta_description = $(value).find("div.panel_pregunta_description");
		var opciones_cambio      = $(value).find("div.panel_opciones_cambio");
		
		var is_selected = pregunta_description.hasClass("description_" + pregunta_selected);
		
		if(! is_selected)
		{
			pregunta_description.slideUp();
			
			$(value).css("padding","0");
		} 
		
		opciones_cambio.hide();
	});
	
	mostrar_panel("div#panel_pregunta_description_" + pregunta_selected);
	mostrar_panel("div#panel_respuesta_seguridad");
	mostrar_panel("div#comprobar_respuesta");
	
	ocultar_panel("div#panel_opciones_cambio");
	
	check_icon.hide();
	error_icon.hide();
	
	$("#respuesta_seguridad").val("");
}

function cancelar_cambio_pregunta()
{		
	ocultar_panel_edicion();
	
	mostrar_panel("div#panel_pregunta_description");
}

function cancelar_comprobar_respuesta()
{	
	cancelar_cambio_pregunta();
	
	var pregunta_selected = $("div#listener_pregunta_selected").find("input").val();
	
	$.each( $("div.panel_pregunta_seguridad"), function( key, value) 
	{		
		var pregunta_description = $(value).find("div.panel_pregunta_description");
		var opciones_cambio      = $(value).find("div.panel_opciones_cambio");
		
		var is_selected = pregunta_description.hasClass("description_" + pregunta_selected);
		
		if(! is_selected)
		{
			pregunta_description.slideDown();
			
			$(value).css("padding","15px 0");
		} 
		
		opciones_cambio.show();
	});
}

function ocultar_panel_edicion()
{
	ocultar_panel("div#lista_preguntas_seguridad");
	ocultar_panel("div#panel_respuesta_seguridad");
	ocultar_panel("div#panel_respuesta_confirmacion");
	ocultar_panel("div#guardar_cambios_preguntas");
	ocultar_panel("div#comprobar_respuesta");

	ocultar_panel("div#preguntas_error_msg");
	
}

function mostrar_panel(id)
{	
	$(id).slideDown();
}

function ocultar_panel(id)
{
	$(id).slideUp();
}

function callback_listener_catalogo_preguntas(xhr, status, args)
{
	var cambio_pregunta_OK = args.cambio_pregunta_OK;
	
	console.log("callback_listener_catalogo_preguntas(cambio_pregunta_OK): " + cambio_pregunta_OK);
	
	var cambiar   = $("div#guardar_cambios_preguntas").find("a div.buttonPR");
	var error_msg = $("div#preguntas_error_msg");
	
	if(cambio_pregunta_OK)
	{		
		error_msg.slideUp();
		cambiar.show();
		
	} else {
		
		error_msg.slideDown();
		cambiar.hide();
	}
}

function callback_guardar_cambios(xhr, status, args)
{
	var is_saved_OK = args.is_saved_OK;
	
	console.log("callback_guardar_cambios(is_saved_OK): " + is_saved_OK);
	
	if(is_saved_OK)
	{	
		ocultar_panel_edicion();
		
		mostrar_panel("div#confirmacion_cambio_OK");
	} 	
}

function callback_comprobar_respuesta(xhr, status, args)
{
	var is_answer_OK = args.is_answer_OK;
	
	var check_icon = $("div#comprobacion_icons").find(".ui-icon-check");
	var error_icon = $("div#comprobacion_icons").find(".ui-icon-closethick");
	
	console.log("callback_comprobar_respuesta(is_answer_OK): " + is_answer_OK);
		
	check_icon.hide();
	error_icon.hide();
	
	if(is_answer_OK)
	{		
		check_icon.show();
		
	} else {
				
		error_icon.show();
	}		
}

function confirmar_cambio_OK()
{	
	ocultar_panel_edicion();
	
	inicializaValoresPerfil();
}

