console.log("editor_panel.js");

/*
define(function()
{
	var modulo = 
	{		
		init: function()
		{
			console.log("nota_editor_panel.init(): OK");
		}
	};
	
	return modulo;
});
*/

Nota.listener_init_edicion = function(xhr, status, args)
{
	console.log("Nota.listener_init_edicion(): ");
	
	var prioridad_id    = args.prioridad_id;
	var tipo_nota_id    = args.tipo_nota_id;
	var tipo_motivo_id  = args.tipo_motivo_id;
	
	console.log("> tipo_nota_id   = " + tipo_nota_id);
	console.log("> prioridad_id   = " + prioridad_id);	
	console.log("> tipo_motivo_id = " + tipo_motivo_id);
	
	var meta_info_JSON = args.meta_info_JSON;
	
	if(meta_info_JSON == "EMPTY")
	{
		console.log("> meta_info_JSON = " + meta_info_JSON);
		
	} else {
		
		Nota.procesar_lista_meta_info(meta_info_JSON);		
	}
	
/*	
	var edicion_TOKEN = $("#update_edicion_nota").attr("value").split("::", 3);
	
	var tipo_nota_id   = edicion_TOKEN[0];
	var prioridad_id   = edicion_TOKEN[1];
	var tipo_motivo_id = edicion_TOKEN[2];
	
	console.log("Nota.mostrar_panel_edicion(): " + edicion_TOKEN);
*/	
	Nota.mostrar_panel_edicion(tipo_nota_id);
	Nota.clean_prioridad_SELECTED();
	Nota.init_prioridad_SELECTED(prioridad_id, tipo_motivo_id);
};