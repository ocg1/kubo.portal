console.log("guardar_edicion_vivienda.js");

function guardar_edicion_vivienda(xhr, status, args)
{
	var vivienda_TOKEN = args.vivienda_TOKEN;
		
	console.log("guardar_edicion_vivienda(): " + vivienda_TOKEN);
	
	$("#container_editar_vivienda_TOKEN").hide("slow");
		
	closeFancy();

	$(document).scrollTop($("#container_vivienda_TOKEN").offset().top);
	
	$("div#motivo_edicion_vivienda").find("textarea").val("");	
	
	$("div#remote-domicilio-actividad").find("a.init-domicilio-actividad").trigger("click");
}

function cancelar_edicion_vivienda()
{
	displayMessageProcessing('msgprocessing',false);
	
	$("#container_editar_vivienda_TOKEN").hide("slow");
	
	$("div#motivo_edicion_vivienda").find("textarea").val("");
	
	$(document).scrollTop($("#container_vivienda_TOKEN").offset().top);
	
	closeFancy();
}