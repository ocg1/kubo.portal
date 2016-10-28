console.log("mx.com.kubo.mesa/solicitud/telefonos.js");

Telefonos.cancel = function(index)
{
	$("div#modal-change-phone-" + index).hide();
};

Telefonos.save = function(index)
{				
	$("div#save-phone-" + index).find("a").trigger("click");
	
	$("div#modal-change-phone-" + index).hide();
};

Telefonos.init_phone = function(index, phone_type)
{
	console.log("Telefonos.init_phone(): " + index + " " + phone_type);
	
	$("div#init-phone-" + index).find("a").trigger("click");
	
	$("div#modal-change-phone-" + index).show();
};

Telefonos.phone_onstart = function(index)
{
	console.log("Telefonos.init_onstart(): " + index);
};

Telefonos.phone_oncomplete = function(xhr, status, args)
{
	var phone_type_id   = args.phone_type_id;
	var phone_number    = args.phone_number;
	var phone_extension = args.phone_extension;
	
	console.log("Telefonos.phone_oncomplete(): ");
	console.log(" > phone_type_id   = " + phone_type_id);
	console.log(" > phone_number    = " + phone_number);
	console.log(" > phone_extension = " + phone_extension);		
	
	this.init_phone_type(phone_type_id);
};

Telefonos.init_phone_type = function(phone_type)
{
	console.log("Telefonos.init_phone_type(): " + phone_type)		
	
	var panel_lada_ENABLED;
	var panel_extension_ENABLED;		
			
	$("div.select-type-phone").find("select").val(phone_type);
	
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
	
	init_panel(panel_lada_ENABLED, panel_extension_ENABLED);
};

Telefonos.phone_type_oncomplete = function(xhr, status, args)
{
	var phone_type_id = args.phone_type_id;
	var panel_lada_ENABLED = args.panel_lada_ENABLED;
	var panel_extension_ENABLED = args.panel_extension_ENABLED;
	
	console.log("Telefonos.phone_type_oncomplete(): ");
	console.log(" > phone_type_id = " + phone_type_id);
	console.log(" > panel_lada_ENABLED = " + panel_lada_ENABLED);
	console.log(" > panel_extension_ENABLED = " + panel_extension_ENABLED);
	
	init_panel(panel_lada_ENABLED, panel_extension_ENABLED);
};

function init_panel(panel_lada_ENABLED, panel_extension_ENABLED)
{
	if(panel_lada_ENABLED)
	{
		$(".lada-phone-cell-save").css("display","block");		
		
	} else {
		
		$(".lada-phone-cell-save").css("display", "none");
	}

	if(panel_extension_ENABLED)
	{
		$("div.phone_extension").css("display","block");
		
	} else {
		
		$("div.phone_extension").css("display","none");
	}
}

Telefonos.phone_number_oncomplete = function(xhr, status, args)
{
	var phone_number = args.phone_number;
	
	console.log("Telefonos.phone_number_oncomplete(): " + phone_number);
};

Telefonos.phone_extension_oncomplete = function(xhr, status, args)
{
	var phone_extension = args.phone_extension;
	
	console.log("Telefonos.phone_extension_oncomplete(): " + phone_extension);
};

Telefonos.whyChangeData_oncomplete = function(xhr, status, args)
{
	var whyChangeData = args.whyChangeData;
	
	console.log("Telefonos.whyChangeData_oncomplete(): " + whyChangeData);
};

Telefonos.update_oncomplete = function(xhr, status, args)
{
	var update_OK = args.update_OK;
	
	console.log("Telefonos.update_oncomplete(): " + update_OK);
	
	if(update_OK)
	{
		$("#update-section-phone").trigger("click");
	}
};

