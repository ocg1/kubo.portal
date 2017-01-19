var flagScrollB1 = false;

$(document).ready(function() 
{			
	$("#dvIDProviderQuest").show();
	$("#dvBtnSiAceptoHC").hide();
	$("#dvSi_autorizoRN").show();
	
	$(".content").css('min-height','1051px');
	
	$("#pnlNoteCoach").hide();
	$(".dt_center").hide();
	$(".noOlvides").hide();
	
	$("#dvSi_autorizoPass").click(function()
			{		
				$(".pass-renovaciones").addClass("show");
				//$(".velo").fadeIn();
				//$("#dvPassIDProv").show();
				//$("#dvIDProviderPass").hide();
			});

			$("#dvSi_autorizoRN").click(function()
			{

				$("#dvIDProviderQuest").fadeOut(500, function()
				{ 
					$("#wait").fadeIn(500);
					
					setTimeout(function()
					{  
						ConsultaRenovacion.init_ofertas(); 
						
					}, 3000);
				});
			
			});
	$("#dvNext_autorizoPass").click(function()
	{						
		ConsultaRenovacion.init_prospectBCRisk();
	});		
	
	ConsultaRenovacion.init_redirect_links();	
});

function cerrarAlertPass() 
{
	$(".pass-renovaciones").removeClass("show");
	//$(".velo").fadeOut();
}

function claseMsgPerKubo()
{
	$("#dvKuboPerson").hide();
}

ConsultaRenovacion.init_redirect_links = function()
{
	console.log("ConsultaRenovacion.init_redirect_links(): OK");		
	
	var remote_command = $("div#remote-command-renovaciones");
	
	if(remote_command.find(".redirect-to-oferta-from-init").length > 0)
	{
		remote_command.find(".redirect-to-oferta-from-init").trigger("click");
	}
	
	if(remote_command.find(".redirect-to-registro-from-init").length > 0)
	{
		ConsultaRenovacion.init_access_CONFIG();						
	}
	
	if( remote_command.find(".redirect-to-registro-controlTable").length > 0 ){
		
		console.log("######	ControlTable ######");
		$("#dvIDProviderQuest").hide();
		$("#wait").show();
		$('.nuevoLoader').hide();
		$('.loading-4clicks').hide();
		$('.rejection-4clicks').show();
		$(".contFrm").css("visibility","hidden");
		
	}
		
	if(remote_command.find(".redirect-to-wait").length > 0)
	{
		$("#dvIDProviderQuest").hide();
		
		$("#wait").show();
		
		$('.nuevoLoader').hide();
		$('.loading-4clicks').fadeIn(500);
		
		ConsultaRenovacion.check_isRisk_processed();
	}
};

ConsultaRenovacion.showRedirecRegistro = function()
{		
	$(".contFrm").hide();
	$(".nuevoLoader").fadeOut(500,function(){ $(".pantalla-style-1").fadeIn(500) });	
}

ConsultaRenovacion.init_access_CONFIG = function()
{		
	var user_agent = navigator.userAgent;
	
	var browser_width  = $(window).width();
	var browser_height = $(window).height();
	
	var access_CONFIG = browser_width + "::" + browser_height + "::" + user_agent;		
	
	console.log("ConsultaRenovacion.init_access_CONFIG(): " + access_CONFIG);
	
	var remote_command = $("div#remote-command-renovaciones");
	
	remote_command.find(".init-access-CONFIG").val(access_CONFIG).trigger("click");
};

ConsultaRenovacion.init_access_on_complete = function(xhr, status, args)
{
	var init_access_OK = args.init_access_OK;		
	
	console.log("ConsultaRenovacion.init_access_on_complete(): " + init_access_OK);
	
	var remote_command = $("div#remote-command-renovaciones");
	
	remote_command.find(".redirect-to-registro-ENABLED").trigger("click");
};

ConsultaRenovacion.password_oncomplete = function(xhr, status, args)
{
	var password_ENABLED = args.password_ENABLED;
	
	console.log("ConsultaRenovacion.password_oncomplete():");
	console.log("> password_ENABLED = " + password_ENABLED);
	
	if(password_ENABLED)
	{
		$("#dvNext_autorizoPass").show();
		cerrarAlertPass();
		
	} else {
		
		$("#dvNext_autorizoPass").hide();
	}
};

ConsultaRenovacion.init_IdProvider= function()
{
	$("div#remote-command-renovaciones").find(".init_Id_Provider").trigger("click");
	
	$("#dvIDProviderQuest").fadeOut(500,	function()
	{ 
		$("#wait").fadeIn(500);																
	});	
	
	$('html, body').animate(
	{
		scrollTop: ($('#frm_content').offset().top - 100)
		
	},1400);
};

ConsultaRenovacion.init_prospectBCRisk = function()
{
	$("div#remote-command-renovaciones").find(".init_prospect_BC_risk").trigger("click");
	
	$("#dvPassIDProv").fadeOut(500,	function()
	{ 
		$("#wait").fadeIn(500);																
	});	
	
	$('html, body').animate(
	{
	       scrollTop: ($('#frm_content').offset().top - 100)
	},1400);
};

ConsultaRenovacion.prospectBCRisk_oncomplete = function(xhr, status, args)
{
	console.log( "args: " + args );
	
	var prospect_risk_ENABLED = args.prospect_risk_ENABLED;
	
	var no_hit = args.no_hit;
	
	var maxIntentos = args.maxIntentos;
	
	var msgErrBur = args.msgErrBur;
	
	console.log( "no_hit: " + no_hit + " maxIntentos: " + maxIntentos + " msgErrBur: " + msgErrBur + " prospect_risk_ENABLED: " +prospect_risk_ENABLED );
	
	if( prospect_risk_ENABLED ){
		
		console.log("ConsultaRenovacion.prospectBCRisk_oncomplete():");
		console.log("> prospect_risk_ENABLED = " + prospect_risk_ENABLED);
		
		if(prospect_risk_ENABLED)
		{
			
			$('.nuevoLoader').hide();
			$('.loading-4clicks').fadeIn(500);
					
			ConsultaRenovacion.check_isRisk_processed();
		}
	
	}else if( no_hit ){
		
		$(".nuevoLoader").hide();
		$(".preaprobacion_pantallas.no-hit-renovacion").show();
		
	}else if(maxIntentos){
		
		$(".nuevoLoader").hide();
		$(".error-consulta-renovacion").show();
		$(".error-consulta-mensaje").html(
				
				"Hemos visto que has intentado varias veces introducir tu información y " +
				"que por alguna razón no logramos autenticarte ante Buró de Crédito.<br />" +
				"Por favor, para brindarte un mejor servicio comunícate con nuestro centro de " +
				"contacto, al teléfono <b>(55)62690024</b> o al correo " +
				"<b>soporte@kubofinanciero.com</b>"
				
		);
		console.log(msgErrBur);
		
	}
};

ConsultaRenovacion.check_isRisk_processed = function()
{
	$("div#remote-command-renovaciones").find(".check_isRisk_processed").trigger("click");
};

ConsultaRenovacion.isRisk_processed_oncomplete = function(xhr, status, args)
{
	var is_risk_processed    = args.is_risk_processed;
	var redirect_to_ofert    = args.redirect_to_ofert_ENABLED;
	var redirect_to_registro = args.redirect_to_registro_ENABLED;
	var is_controlTable 	 = args.is_controlTable;
	
	console.log("ConsultaRenovacion.isRisk_processed(): ");
	console.log("> is_risk_processed    = " + is_risk_processed);
	console.log("> redirect_to_ofert    = " + redirect_to_ofert);
	console.log("> redirect_to_registro = " + redirect_to_registro);
	console.log("> is_controlTable = " 		+ is_controlTable);
	
	if(is_risk_processed)
	{
		if(redirect_to_registro)
		{
			//ConsultaRenovacion.init_access_CONFIG();	
			if( !is_controlTable ){
				ConsultaRenovacion.showRedirecRegistro();
			}else{
				console.log("######	ControlTable ######");
				$("#dvIDProviderQuest").hide();
				$("#wait").show();
				$('.nuevoLoader').hide();
				$('.loading-4clicks').hide();
				$('.rejection-4clicks').show();
				$(".contFrm").css("visibility","hidden");
				//alert("Seguir por Editar Formulario");
			}
		} 
		
		if (redirect_to_ofert)
		{			
			$("div#remote-command-renovaciones").find(".ofertasLnk").trigger("click");
		}		
		
	} else {
		
		setTimeout( function()
		{  
			ConsultaRenovacion.check_isRisk_processed();
			
		}, 3000);
	}
};

function regresarHistorial() {
	$("#wait").hide();
	$(".preaprobacion_pantallas.no-hit-renovacion").fadeOut();
	$("#dvIDProviderQuest").fadeIn();
}