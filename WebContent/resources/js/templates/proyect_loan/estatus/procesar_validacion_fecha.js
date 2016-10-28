console.log("estatus/procesar_validacion_fecha.js");

function procesar_validacion_fecha(xhr, status, args)
{
	console.log("procesar_validacion_fecha(): args.isFechaValida: " + args.isFechaValida);
	
	var error_msg = "Fecha incorrecta";
	
	if(args.isFechaValida)
	{
		$("#msg_fecha_valida").hide("slow");
		$("#dvBtnChangeStatus").show("fast");
		
	} else {
		
		$("#dvBtnChangeStatus").hide("fast");
		$("#msg_fecha_valida").html(error_msg).show("slow");
	}
}