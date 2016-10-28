console.log("editor-telefono.js");

var EditorTelefono = window.EditorTelefono || {};

EditorTelefono.update_panel_oncomplete = function(xhr, status, args)
{
	var phone_view_OK = args.phone_view_OK;
	
	console.log("EditorTelefono.phone_view_OK = " + phone_view_OK);
};

EditorTelefono.phone_type_oncomplete = function(xhr, status, args)
{
	var phone_type_id = args.phone_type_id;
	var panel_lada_ENABLED = args.panel_lada_ENABLED;
	var panel_extension_ENABLED = args.panel_extension_ENABLED;
	
	console.log("EditorTelefono.phone_type_oncomplete(): ");
	console.log(" > phone_type_id = " + phone_type_id);
	console.log(" > panel_lada_ENABLED = " + panel_lada_ENABLED);
	console.log(" > panel_extension_ENABLED = " + panel_extension_ENABLED);
	
	this.init_panel(panel_lada_ENABLED, panel_extension_ENABLED);
};

EditorTelefono.init_phone_type = function(phone_id, phone_type)
{
	console.log("EditorTelefono.init_phone_type(): " + phone_type);
	console.log(" >      phone_id = " + phone_id);
	console.log(" > phone_type_id = " + phone_type);
	
	var panel_lada_ENABLED;
	var panel_extension_ENABLED;		
			
	$("div#select-phone-type-" + phone_id).find("select").val(phone_type);		
	
	switch (phone_type) 
	{
		case 1:
			panel_lada_ENABLED = false;
			panel_extension_ENABLED = true;
		break;
		
		case 2:
		case 4:
		case 6:
			panel_lada_ENABLED = true;
			panel_extension_ENABLED = false;
		break;

		default:
			panel_lada_ENABLED = false;
			panel_extension_ENABLED = false;
		break;
	}
	
	this.init_panel(panel_lada_ENABLED, panel_extension_ENABLED);
};

EditorTelefono.init_panel = function(panel_lada_ENABLED, panel_extension_ENABLED)
{
	if(panel_lada_ENABLED)
	{
		$(".lada-phone-cell").css("display","block");		
		
	} else {
		
		$(".lada-phone-cell").css("display", "none");
	}

	if(panel_extension_ENABLED)
	{
		$("div.phone_extension").css("display","block");
		
	} else {
		
		$("div.phone_extension").css("display","none");
	}
};