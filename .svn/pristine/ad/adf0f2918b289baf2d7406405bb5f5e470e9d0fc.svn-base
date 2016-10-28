console.log("loan-negotiation.js");

IncomesExpenses.init_loan_negotiation = function()
{
	$("#btnInitialize").trigger("click");
	
	$("#negotiationDv").slideToggle("slow");
};

IncomesExpenses.complete_set_iniciales_negotiation = function(xhr, status, args)
{
	var monto = args.monto_inicial;
	var plazo = args.plazo;
	
	$("#ammountNeg").val(monto);
	$("#termNeg").val(plazo);
	
	console.log("IncomesExpenses.complete_set_iniciales_negotiation(): ");
	console.log(" > monto = " + monto);
	console.log(" > plazo = " + plazo);
}

IncomesExpenses.acepta_condiciones_oncomplete = function(xhr, status, args)
{
	var acepta_condiciones_OK = args.acepta_condiciones_OK;
	
	console.log("IncomesExpenses.acepta_condiciones_oncomplete: " + acepta_condiciones_OK);
	
	$("#init-modifier-indicadores").trigger("click");
}

IncomesExpenses.modifier_indicadores_oncomplete = function(xhr, status, args)
{
	var indicadores_OK = args.indicadores_OK;
	
	console.log("IncomesExpenses.modifier_indicadores_oncomplete: " + indicadores_OK);
}