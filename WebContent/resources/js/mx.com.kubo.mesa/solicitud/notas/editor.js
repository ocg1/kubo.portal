console.log("editor.js");

/*
define(function()
{
	var modulo = 
	{		
		init: function()
		{
			console.log("nota_editor.init(): OK");
		}
	};
	
	return modulo;
});
*/

Nota.listener_editar_nota_SELECTED = function()
{
	console.log("Nota.listener_editar_nota_SELECTED(): OK");
	
/*
 * 	var prioridad_id    = args.prioridad_id;
	var tipo_nota_id    = args.tipo_nota_id;
	var tipo_motivo_id  = args.tipo_motivo_id;
	
	console.log("> tipo_nota_id   = " + tipo_nota_id);
	console.log("> prioridad_id   = " + prioridad_id);	
	console.log("> tipo_motivo_id = " + tipo_motivo_id);
		
	var edicion_TOKEN = tipo_nota_id + "::" + prioridad_id + "::" + tipo_motivo_id;
	
	$("#update_edicion_nota").attr("value", edicion_TOKEN);
*/	
	
	$("#update_edicion_nota").trigger("click");			
};

Nota.procesar_lista_meta_info = function(meta_info_JSON)
{
	console.log("Nota.procesar_lista_meta_info():");
	
	var lista_meta_info = eval('( '+ meta_info_JSON + ')');
	
	for(index in lista_meta_info)
	{		
		if(index != 'remove')
		{
			var field_name  = lista_meta_info[index].field_name;
			var field_value = lista_meta_info[index].field_value;
			
			console.log("> field_name  = " + field_name);
			console.log("> field_value = " + field_value);
			
			if(field_value == "S")
			{
				var selector_ID = "#" + field_name + " span";
				
				$(selector_ID).addClass("ui-icon ui-icon-check");	
				
				console.log("> class = " + $(selector_ID).attr("class"));
			}				
		}
	}	
};

Nota.mostrar_panel_edicion = function(tipo_nota_id)
{
	console.log("Nota.mostrar_panel_edicion(): OK");
	
	$('#btn-add-notes, #section-add-notes').hide('slow');	
	
	$('#main_edicion_nota').show();		

	$('html, body').animate(
	{
        scrollTop: $("#edicion_prioridad_SOM").offset().top - 450
    }, 800);
	
	$('#edicion_prioridad_SOM').find('select').focus().scrollTop();
	
	if(tipo_nota_id == 9)
	{
		$('#edicion_meta_INFO').show("fast");
		
	} else {
		
		$('#edicion_meta_INFO').hide("fast");
	}
	
};

Nota.clean_prioridad_SELECTED = function()
{
	console.log("Nota.clean_prioridad_SELECTED(): OK");
	
	$('#edicion_prioridad_SOM').find('option').filter(function(index)
	{		
		return $(this).attr("selected") == "selected";
		
	}).removeAttr("selected");
};
 
Nota.init_prioridad_SELECTED = function(prioridad_id, tipo_motivo_id)
{	
	console.log("Nota.init_prioridad_SELECTED(): OK");
	
	$('#edicion_prioridad_SOM').find('option').filter(function(index)
	{		
		return $(this).attr("value") == prioridad_id;
		
	}).attr("selected","selected");
};