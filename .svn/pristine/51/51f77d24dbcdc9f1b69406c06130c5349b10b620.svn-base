console.log("editor-identification-type.js")


IdentificationType.show_panel_editor_identification = function()
{
	$("div#editor-identification-type").show();
	$("div#section-data-identification-type").hide();	
};

IdentificationType.type_id_oncomplete = function(xhr, status, args)
{
	var type_id = args.type_id;
	var label_ENABLED = args.label_ENABLED;
	
	console.log("IdentificationType.type_id_oncomplete():");
	console.log("      type_id = " + type_id);
	console.log("label_ENABLED = " + label_ENABLED);
	
	displayMessageProcessing('msgprocessing',false);
	
	$("div#remote-identification-type").find("a.init-label-panel").trigger("click");
};

IdentificationType.init_label_panel = function()
{		
	console.log("IdentificationType.init_label_panel() = OK");		
	
	$("div#editor-identification-type").show();
	
	closeFancy();
};

IdentificationType.edit_oncomplete = function(xhr, status, args)
{
	var input_text_value = args.input_text_value;
	
	console.log("IdentificationType.edit_oncomplete() = " + input_text_value);
};


IdentificationType.update_oncomplete = function(xhr, status, args)
{
	var update_OK = args.update_OK;
	
	console.log("IdentificationType.update_oncomplete() = " + update_OK);
	
	$("div#remote-identification-type").find("a.update-section-data-ife").trigger("click");
};

IdentificationType.section_data_oncomplete = function(xhr, status, args)
{
	var editor_ife_ENABLED = args.editor_ife_ENABLED;
	
	console.log("IdentificationType.section_data_oncomplete() = " + editor_ife_ENABLED);
	
	$("div#editor-identification-type").hide();	
	
	closeFancy();
};


IdentificationType.update = function()
{
	displayMessageProcessing('msgprocessing',false);
	
	$("div#remote-identification-type").find("a.update-data").trigger("click");
};

IdentificationType.cancel = function()
{
	$("div#editor-identification-type").hide();
	$("div#section-data-identification-type").show();
};
