console.log("editor-nombre.js");

function cancelar_edicion_nombre(xhr, status, args)
{					
	var first_name       = args.first_name;
	var middle_name      = args.middle_name;
	var father_last_name = args.father_last_name;
	var mother_last_name = args.mother_last_name;
	
	$("#person_first_name" ).val(first_name);
	$("#person_middle_name").val(middle_name);
	$("#person_father_last_name").val(father_last_name);
	$("#person_mother_last_name").val(mother_last_name);
	
	var full_name = first_name + " " + middle_name + " " + father_last_name + " " + mother_last_name;
	
	console.log("cancelar_edicion_nombre(): " + full_name);
	
	closeFancy();
}

function guardar_edicion_nombre(xhr, status, args)
{
	var full_name                      = args.full_name;
	var change_control_OK              = args.change_control_OK;	
	var error_first_name_ENABLED       = args.error_first_name_ENABLED;
	var error_father_last_name_ENABLED = args.error_father_last_name_ENABLED;
	var error_MSG = "";
					
	console.log("> full_name: " + full_name);
	console.log("> change_control_OK:              " + change_control_OK);	
	console.log("> error_first_name_ENABLED:       " + error_first_name_ENABLED);
	console.log("> error_father_last_name_ENABLED: " + error_first_name_ENABLED);
		
	if(error_first_name_ENABLED || error_father_last_name_ENABLED)
	{				
		if(error_first_name_ENABLED)
		{
			error_MSG += "<li>Se requiere especificar el <b>Nombre</b></li>";						
		}
		
		if(error_father_last_name_ENABLED)
		{
			error_MSG += "<li>Se requiere especificar el <b>Apellido Paterno</b></li>";			
		}				
		
		$("div#error_MSG_editor_nombre ul").html(error_MSG).parent().show();
		
	} else {
			
		$("div#error_MSG_editor_nombre ul").html("").parent().hide();				
	}
	
	console.log("guardar_edicion_nombre(): OK");
	
	closeFancy();						
}