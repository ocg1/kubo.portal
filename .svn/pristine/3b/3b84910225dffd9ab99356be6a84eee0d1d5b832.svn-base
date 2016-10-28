console.log("mx.com.kubo.mesa/tablero-control.js");

function init_listener_partner_id()
{
	var partner_id = $("div#partner_id input").val();
	
	console.log("init_listener_partner_id(): " + partner_id);
	
	$("div#listener_partner_id input").val(partner_id).trigger("blur");
	
	$("div.alta-partner.partner-id.msg-confirmacion").html("").hide();
}

function listener_partner_id(xhr, status, args)
{
	var partner_ENABLED = args.partner_ENABLED;
	var partner_NEW     = args.partner_NEW;
	
	console.log("listener_partner_id(partner_ENABLED): " + partner_ENABLED);
	console.log("listener_partner_id(partner_NEW):     " + partner_NEW);
	
	if(partner_ENABLED)
	{	
		if(partner_NEW)
		{
			$("#partnerId_btn").click(function () {
				$("#partner-info").hide();
				$("#partner-nuevo-info").slideDown("slow");
			});
			
		} else {
			
			var partner_name        = args.partner_name;
			var partner_descripcion = args.partner_descripcion;
			
			console.log("listener_partner_id(partner_name):       "  + partner_name);
			console.log("listener_partner_id(partner_descripcion): " + partner_descripcion);
			$("#partnerId_btn").click(function () {
				$("#partner-info").slideDown("slow");
				$("#partner-nuevo-info").hide();
				
				$("p#partner-name").html(partner_name);
				$("p#partner-description").html(partner_descripcion);
			});
		}
		
	} else {
		$("#partnerId_btn").click(function () {
			$("#partner-info").hide("slow");
			$("#registration-reason-info").hide("slow");
			$(".alta-partner.guardar").hide("slow");
		});
	}
	
	closeFancy();
}

function listener_partner_name(xhr, status, args)
{
	var partner_name_OK              = args.partner_name_OK;
	var registration_reason_ENABLED  = args.registration_reason_ENABLED;
	
	console.log("listener_partner_name(): " + partner_name_OK);
	
	init_registration_reason_INFO(registration_reason_ENABLED);
}

function listener_partner_descripcion(xhr, status, args)
{
	var partner_descripcion_OK       = args.partner_descripcion_OK;
	var registration_reason_ENABLED  = args.registration_reason_ENABLED;
	
	console.log("listener_partner_descripcion(): " + partner_descripcion_OK);
	
	init_registration_reason_INFO(registration_reason_ENABLED);
}

function init_registration_reason_INFO(registration_reason_ENABLED)
{
	console.log("listener_partner_name(): " + registration_reason_ENABLED);
	
	if(registration_reason_ENABLED)
	{
		$("#registration-reason-info").show("slow");
		$(".alta-partner.guardar").show("slow");
		
	} else {
		
		$("#registration-reason-info").hide("slow");
		$(".alta-partner.guardar").hide("slow");
	}
}

function listener_guardar_partner()
{
	displayMessageProcessing('msgprocessing',false);
	
	$("#listener_alta_partner").click();		
}

function procesar_alta_partner(xhr, status, args)
{
	var save_OK = args.save_OK;
	
	console.log("procesar_alta_partner(): " + save_OK);
	
	if(save_OK)
	{
		$("#form-alta-partner").get(0).reset();
		$("#partner-nuevo-info").hide();
		$("#registration-reason-info").hide("slow");
		$(".alta-partner.guardar").hide("slow");
		$("div.alta-partner.partner-id.msg-confirmacion").html("Se guardó el partner").show();
	}
	
	closeFancy();
}
