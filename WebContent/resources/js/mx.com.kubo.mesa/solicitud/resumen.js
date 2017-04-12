console.log("resumen.js");

Resumen.rate_oncomplete = function(xhr, status, args)
{
	$("#editor-rate-motive").val("").blur();				
	$("#update-loan-rate").trigger("click");
	$("#update-loan-rate-investor").trigger("click");
	
	console.log("Resumen.rate_oncomplete(): OK");
};	

Resumen.rate_investor_oncomplete = function(xhr, status, args)
{
	$("#editor-rate-investor-motive").val("").blur();				
	$("#update-loan-rate-investor").trigger("click");
	
	console.log("Resumen.rate_investor_oncomplete(): OK");
};	

Resumen.opening_commission_oncomplete = function(xhr, status, args)
{
	$("#editor-commission-motive").val("").blur();				
	$("#update-editor-commission").trigger("click");
	
	console.log("Resumen.opening_commission_oncomplete(): OK");
};

Resumen.loan_score_oncomplete = function(xhr, status, args)
{
	$("#editor-score-motive").val("").blur();				
	$("#update-editor-score").trigger("click");
	
	console.log("Resumen.loan_score_oncomplete(): OK");
};

Resumen.loantype_oncomplete = function(xhr, status, args)
{
	$("#editor-tipo-credito-motivo").val("").blur();				
	$("#cmdTipoCred").trigger("click");
	
	console.log("Resumen.loantype_oncomplete(): OK");
};

Resumen.proyect_purpose_oncomplete = function(xhr, status, args)
{
	$("#editor-purpose-motive").val("").blur();				
	$("#update-proyect-purpose").trigger("click");
	
	console.log("Resumen.proyect_purpose_oncomplete(): OK");
};

Resumen.showInvestors = function(safi_credit_id, safi_mx_solicitud_id)
{
	var inversionistas = 'templates/perfil_completo/listaInversionistas/listaInversionistas.xhtml';
	
	var proyectloan = '?proyectLoan=' + safi_credit_id + '_' + safi_mx_solicitud_id;
	
	var href = inversionistas + proyectloan;
	
	console.log("Resumen.showInvestors(): " + proyectloan);
	
	$.fancybox(
	{
		'showCloseButton': false,
		'width': 680,
		'height': '90%',
		'type':'iframe',
		'speedIn': 20,
		'speedOut': 10,
		'modal': false,
		'scrolling': 'auto',
		'centerOnScroll': true,
		'href': href,
		'overlayColor': '#333333',
		'hideOnOverlayClick': true,
		'enableEscapeButton': true
	});
};

Resumen.init_buro_reprocess = function()
{
	$("#init-buro-reprocess").trigger("click");
};

Resumen.buro_reprocess_oncomplete = function(xhr, status, args)
{
	var buro_reproccess_OK = args.buro_reproccess_OK;
	var response_msg = args.response_msg;
	var response_status = args.response_status;
	
	console.log("Resumen.buro_reprocess_oncomplete(): ");
	console.log(" > buro_reproccess_OK = " + buro_reproccess_OK);
	console.log(" > response_status = " + response_status);
	console.log(" > response_msg = " + response_msg);
	
	closeFancy();
	
	$("#update-solicitud-resumen").trigger("click");
};