console.log("credito-adicional.js");

function loan_type_id_oncomplete(xhr, status, args)
{
	var loan_type_id = args.loan_type_id;
	
	console.log("loan_type_id = " + loan_type_id);
}

function consulta_ENABLED_oncomplete(xhr, status, args)
{
	var consulta_ENABLED = args.consulta_ENABLED;
	
	console.log("consulta_ENABLED = " + consulta_ENABLED);
}

function loader()
{	
	$("#resultadosSimNeg").css("display","none");
	$("#loaderSimNeg").css("display","block");
	
	 return true;
}

function showRes()
{	
	console.log("showRes()");
	
	$("#loaderSim").css("display","none");
	$("#resultadosSim").css("display","block");
	
	return true;
}