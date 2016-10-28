console.log("Portal/resources/js/editar-telefono/change-phone.js");


EditorTelefono.init_change_phone = function(phone_id)
{
	if(phone_id != undefined)
	{
		console.log("EditorTelefono.init_change_phone(): ");
		console.log(" > phone_id   = " + phone_id);	
		
		$("div#init-change-phone-" + phone_id).find("a").trigger("click");
		
		$("div.ui-inplace-content").hide();
		
		$("div#modal-change-phone-" + phone_id).show();
	}
};

EditorTelefono.phone_oncomplete = function(xhr, status, args)
{
	var phone_id        = args.phone_id;
	var phone_type_id   = args.phone_type_id;
	var phone_number    = args.phone_number;
	var phone_extension = args.phone_extension;
	
	console.log("EditorTelefono.phone_oncomplete(): ");
	console.log(" > phone_id        = " + phone_id);
	console.log(" > phone_type_id   = " + phone_type_id);
	console.log(" > phone_number    = " + phone_number);
	console.log(" > phone_extension = " + phone_extension);		
	
	this.init_phone_type(phone_id, phone_type_id);
	
	alertasCentrarH();
	
	$(".veloE").fadeIn();
};

EditorTelefono.phone_number_oncomplete = function(xhr, status, args)
{
	var phone_number = args.phone_number;
	
	console.log("EditorTelefono.phone_number = " + phone_number);
};

EditorTelefono.phone_extension_oncomplete = function(xhr, status, args)
{
	var phone_extension = args.phone_extension;
	
	console.log("EditorTelefono.phone_extension =  " + phone_extension);
};

EditorTelefono.whyChangeData_oncomplete = function(xhr, status, args)
{
	var whyChangeData = args.whyChangeData;
	
	console.log("EditorTelefono.whyChangeData = " + whyChangeData);
};

EditorTelefono.cancel = function()
{	
	$("div.ui-inplace-content").hide();
	
	$(".veloE").fadeOut();
};

EditorTelefono.save = function(index)
{				
	$("div#save-phone-" + index).find("a").trigger("click");
	
	$("div.ui-inplace-content").hide();
};

EditorTelefono.update_oncomplete = function(xhr, status, args)
{
	var update_OK = args.update_OK;
	
	console.log("EditorTelefono.update_oncomplete(): " + update_OK);
	
	if(update_OK)
	{
		$("#update-panel-phone-view").trigger("click");
	}
	
	$(".veloE").fadeOut();
};


function alertasCentrarH() 
{
	var content = $('.centrar_h');
	var width = $(window).width();
	
	content.css(
	{
		position:'absolute',
		left: ($('.content').width() - content.outerWidth()) / 2
		//top: ($(window).height() - content.outerHeight())/2
	});		
}

$(window).load(function() 
{
	alertasCentrarH();	
});

$(window).resize(function() 
{
	var resizeId2;
	
	clearTimeout(resizeId2);
	
	resizeId2 = setTimeout(alertasCentrarH, 100);
});
