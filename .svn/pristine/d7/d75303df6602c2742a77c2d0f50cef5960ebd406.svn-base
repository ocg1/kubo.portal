console.log("estatus/listener_estatus_selected.js");

function listener_estatus_selected(e)
{		
	var RECHAZADO      = 4;
	var POSPUESTO      = 5;
	var DESISTIDO      = 7;
	var PRE_AUTORIZADO = 10;
	
	e.preventDefault();
	
	var estatus_selected = parseInt($(this).val());

	var fecha_pospuesta        = ($("#input_cambio_estatus_TOKEN").val()).split("::")[3];
	var fecha_pre_autorizada   = ($("#input_cambio_estatus_TOKEN").val()).split("::")[4];
	
	console.log("listener_estatus_selected(): " + estatus_selected);
	
	$("#msg_fecha_valida").hide("fast");
	
	switch(estatus_selected)
	{
		case RECHAZADO:
			$(".descriptionAddMotive").html("Motivo de rechazo");
			$("#main_motive_select").show("fast");
			$("#main_select_fecha").hide("fast");
			
			$("#input_listener_estatus_SELECTED").val(RECHAZADO).click();
			
			asignar_valores_TOKEN(estatus_selected);
		break;
		
		case POSPUESTO:
			$(".descriptionAddMotive").html("Motivo de caso pospuesto");
			$("#main_motive_select").show("fast");
			$("#main_select_fecha").show("fast");
			
			$("#input_listener_estatus_SELECTED").val(POSPUESTO).click();	
			
			asignar_valores_TOKEN(estatus_selected);
			asignar_fecha_TOKEN(fecha_pospuesta);
		break;
		
		case DESISTIDO:
			$(".descriptionAddMotive").html("Motivo de caso desistido");
			$("#main_motive_select").show("fast");
			$("#main_select_fecha").hide("fast");
			
			$("#input_listener_estatus_SELECTED").val(DESISTIDO).click();
			
			asignar_valores_TOKEN(estatus_selected);
		break;
		
		case PRE_AUTORIZADO:
			$(".descriptionAddMotive").html("Motivo de caso pre-autorizado");
			$("#main_motive_select").show("fast");
			$("#main_select_fecha").show("fast");
			
			$("#input_listener_estatus_SELECTED").val(PRE_AUTORIZADO).click();
			
			asignar_valores_TOKEN(estatus_selected);
			asignar_fecha_TOKEN(fecha_pre_autorizada);
		break;
		
		default:
			$(".descriptionAddMotive").html("");
			$("#main_select_fecha").hide("fast");
			$("#main_motive_select").hide("fast");
			
			$("#input_listener_estatus_SELECTED").val("").click();
			
			asignar_valores_TOKEN(estatus_selected);			
		break;
	}	
}

function asignar_valores_TOKEN(estatus_selected)
{
	setTimeout(function()
	{
		var estatus_TOKEN          = ($("#input_cambio_estatus_TOKEN").val()).split("::")[0];
		var descripcion_del_motivo = ($("#input_cambio_estatus_TOKEN").val()).split("::")[1];	
		var motive_id              = ($("#input_cambio_estatus_TOKEN").val()).split("::")[2];
		
		if(estatus_selected == parseInt(estatus_TOKEN))
		{
			console.log("\ninit_listener_cambio_estatus_TOKEN(): " + motive_id);
			
			$("#select_lista_de_motivos").val(motive_id).trigger("change");
			$("#txt-reason-status-proyect1").val(descripcion_del_motivo).trigger("blur");
			
		} else {
			
			$("#txt-reason-status-proyect1").val("").trigger("blur");
			$("#select_lista_de_motivos").val("");
		}
		
	}, 1200);
}

function asignar_fecha_TOKEN(fecha_TOKEN)
{
	var dd   = parseInt(fecha_TOKEN.split("/")[0]);
	var mm   = parseInt(fecha_TOKEN.split("/")[1]);
	var yyyy = parseInt(fecha_TOKEN.split("/")[2]);
	
	console.log("\nasignar_fecha_pre_autorizada(): " + dd + "/" + mm + "/" +yyyy);
		
	$("#select_fecha_lista_dd").find('select').val(dd).trigger("change");
	$("#select_fecha_lista_mm").find('select').val(mm).trigger("change");	
	$("#select_fecha_lista_yyyy").find('select').val(yyyy).trigger("change");
}