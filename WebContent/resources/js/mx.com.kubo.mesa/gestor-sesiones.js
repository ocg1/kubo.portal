console.log("gestor-sesiones.js");

GestorSesiones.listener_usuario_SELECTED = function(prospectus_id)
{
	console.log("listener_usuario_SELECTED(): " + prospectus_id);
		
	$("#input_listener_usuario_selected").val(prospectus_id).trigger("click");
}

GestorSesiones.procesar_usuario_SELECTED = function(xhr, status, args)
{
	var remove_OK = args.remove_OK;
	
	console.log("procesar_usuario_SELECTED(): " + remove_OK);
	
	$("#update-panel-gestor-sesiones").trigger("click");
}

GestorSesiones.filter_type_oncomplete = function(xhr, status, args)
{
	var filter_type_id = args.filter_type_id;
	
	console.log("GestorSesiones.filter_type = " + filter_type_id);
	
	$("#update-panel-gestor-sesiones").trigger("click");
};