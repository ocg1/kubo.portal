console.log("Portal/resources/js/editar-telefono/add-phone.js");


EditorTelefono.toggle_section_phone = function()
{
	$("#section-add-phone").show();
	$(".veloE").fadeIn();
};

EditorTelefono.cancel_add_phone = function()
{
	$("#section-add-phone").hide();
	$(".veloE").fadeOut();
};

EditorTelefono.init_add_phone = function()
{
	$("#init-add-phone").click();
};

EditorTelefono.add_phone_on_complete = function(xhr, status, args)
{
	var change_control_OK = args.change_control_OK;
	var persist_OK = args.persist_OK;
	
	console.log("EditorTelefono.add_phone_on_complete(): ");
	console.log(" > persist_OK = " + persist_OK);
	console.log(" > change_control_OK = " + change_control_OK);
	
	
	$("#select-type_phone").val("0").trigger("change");
	$("#addnumphone").val("");
	$("#phone_extension").val("");
	$("#txt-reason-add-phone").val("");
	
	$("#section-add-phone").hide();
	$(".veloE").fadeOut();
};