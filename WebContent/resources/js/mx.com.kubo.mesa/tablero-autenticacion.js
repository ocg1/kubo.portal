console.log("mx.com.kubo.mesa/tablero-autenticacion.js");

function filtro_area_on_complete(xhr, status, args)
{
	var filtro_area_SELECTED = args.filtro_area_SELECTED;
	
	console.log("filtro_area_on_complete(): " + filtro_area_SELECTED);
}

function busqueda_prospecto_on_complete(xhr, status,args)
{
	var prospectus_OK = args.prospectus_OK;
	
	console.log("busqueda_prospecto_on_complete(): " + prospectus_OK);
	
	if(prospectus_OK)
	{
		$("input#init-pool-autenticacion").trigger("click");
	}
	
	closeMessageProcessing();
}

function pool_autenticacion_on_complete(xhr, status,args)
{
	var pool_autenticacion_ENABLED = args.pool_autenticacion_ENABLED;
	
	console.log("init_pool_autenticacion_on_complete(): " + pool_autenticacion_ENABLED);
}

function init_autenticacion_TOKEN()
{
	var autenticacion_id_TOKEN = $("div#autenticacion-id-TOKEN").find("input").val();
	
	console.log("init_autenticacion_TOKEN(): " + autenticacion_id_TOKEN);
	
	var lista_autenticacion = autenticacion_id_TOKEN.split("::", 3);
	
	var authentication_id_1 = lista_autenticacion[0];
	var authentication_id_2 = lista_autenticacion[1];
	var authentication_id_3 = lista_autenticacion[2];
	
	console.log("> authentication_id_1: " + authentication_id_1);
	console.log("> authentication_id_2: " + authentication_id_2);
	console.log("> authentication_id_3: " + authentication_id_3);	
	
	$("div#autenticacion-INPUT-1").html($("#authentication-id-" + authentication_id_1).html());
	$("div#autenticacion-INPUT-2").html($("#authentication-id-" + authentication_id_2).html());
	$("div#autenticacion-INPUT-3").html($("#authentication-id-" + authentication_id_3).html());
	
	
}

function init_autenticacion(id)
{
	$("div#init-autenticacion-" + id).find("input").val(id).trigger("click");
	
	console.log("init_autenticacion(): " + id);
}

function autenticacion_on_complete(xhr, status,args)
{
	var autenticacion_OK   = args.autenticacion_OK;
	var change_control_OK  = args.change_control_OK;
	var respuesta_id       = args.autenticacion_ID;
	var contador_CORRECTAS = args.contador_CORRECTAS;
	var desbloquear_password_ENABLED = args.desbloquear_password_ENABLED;
	
	console.log("autenticacion_on_complete(): ");
	console.log(" > respuesta_id:       " + respuesta_id);
	console.log(" > autenticacion_OK:   " + autenticacion_OK);
	console.log(" > change_control_OK:  " + change_control_OK);
	console.log(" > contador_CORRECTAS: " + contador_CORRECTAS);
	console.log(" > desbloquear_password_ENABLED: " + desbloquear_password_ENABLED);
	
	if(autenticacion_OK)
	{
		$("div#autenticacion-icon-" + respuesta_id).find("span.ui-icon-check").css("display", "block");
		$("div#autenticacion-icon-" + respuesta_id).find("span.ui-icon-closethick").hide();
		$("p#autenticacion-MSG-" + respuesta_id).html("CORRECTO");
		
	} else {
		
		$("div#autenticacion-icon-" + respuesta_id).find("span.ui-icon-closethick").css("display", "block");
		$("div#autenticacion-icon-" + respuesta_id).find("span.ui-icon-check").hide();
		$("p#autenticacion-MSG-" + respuesta_id).html("INCORRECTO");
	}
	
	if(desbloquear_password_ENABLED)
	{
		$("input#init-panel-desbloquear-password").trigger("click");
	}
	
	closeMessageProcessing();
}

function panel_desbloquear_password_on_complete(xhr, status, args)
{
	var desbloquear_password_ENABLED = args.desbloquear_password_ENABLED;
	
	console.log("panel_desbloquear_password_on_complete(): " + desbloquear_password_ENABLED);
	
	closeMessageProcessing();
}

function init_desbloquear_password()
{		
	$("#init-desbloquear-password").trigger("click");
	
	console.log("init_desbloquear_password(): OK");
}

function desbloquear_password_on_complete(xhr, status, args)
{
	var desbloquear_password_OK = args.desbloquear_password_OK;
	
	console.log("desbloquear_password_on_complete(): " + desbloquear_password_OK);
	
	if(desbloquear_password_OK)
	{
		$("div#desbloqueo-password-icon").find("span").css("display", "block");
	}
	
	closeMessageProcessing();
}

function comparar_birthday_on_complete(xhr, status, args)
{
	var fecha_ENABLED = args.fecha_ENABLED;
	
	console.log("comparar_birthday_on_complete(): " + fecha_ENABLED);
}

function state_id_on_complete(xhr, status, args)
{
	var birthplace_ENABLED = args.birthplace_ENABLED;
	
	console.log("state_id_on_complete(): " + birthplace_ENABLED);
}

function town_id_on_complete(xhr, status, args)
{
	var town_ENABLED = args.town_ENABLED;
	
	console.log("town_id_on_complete(): " + town_ENABLED);
}

function zipcode_on_complete(xhr, status, args)
{
	var zipcode_ENABLED = args.zipcode_ENABLED;
	
	console.log("zipcode_on_complete(): " + zipcode_ENABLED);
}

function celular_on_complete(xhr, status, args)
{
	var celular_ENABLED = args.celular_ENABLED;
	
	console.log("celular_on_complete(): " + celular_ENABLED);
}

function email_on_complete(xhr, status, args)
{
	var email_ENABLED = args.email_ENABLED;
	
	console.log("email_on_complete(): " + email_ENABLED);
}
