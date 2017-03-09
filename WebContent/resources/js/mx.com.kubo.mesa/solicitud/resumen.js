console.log("resumen.js");

Resumen.rate_oncomplete = function(xhr, status, args)
{
	$("#editor-rate-motive").val("").blur();				
	$("#update-loan-rate").trigger("click");
	
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

function showInvestors()
{
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
		'href': 'templates/perfil_completo/listaInversionistas/listaInversionistas.xhtml?proyectLoan=#{summaryRequest.actualProyect.safi_credit_id}_#{summaryRequest.actualProyect.safi_mx_solicitud_id}',
		'overlayColor': '#333333',
		'hideOnOverlayClick': true,
		'enableEscapeButton': true
	});
}