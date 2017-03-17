console.log("editor-documentos.js");

var Documentacion = window.Documentacion || {};

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

Documentacion.init_file_type_id = function(file_type_id)
{
	console.log("Documentacion.init_file_type_id(): " + file_type_id);
	
	$("div#editor-file-type-id").find("select").val(file_type_id).trigger("change");
};

Documentacion.file_type_on_complete = function(xhr, status, args)
{
	var file_type_id = args.file_type_id;
	var reca_ENABLED = args.reca_ENABLED;
	var size_limit   = args.size_limit;
	
	console.log("Documentacion.file_type_on_complete(): ")
	console.log("> file_type_id = " + file_type_id);
	console.log("> reca_ENABLED = " + reca_ENABLED);	
	console.log("> size_limit   = " + size_limit);
};

Documentacion.reca_id_on_complete = function(xhr, status, args)
{
	var reca_id = args.reca_id;
	
	console.log("Documentacion.reca_id_on_complete(): " + reca_id);
};

Documentacion.init_access_CONFIG = function()
{		
	var user_agent = navigator.userAgent;
	
	var browser_width  = $(window).width();
	var browser_height = $(window).height();
	
	var access_CONFIG = browser_width + "::" + browser_height + "::" + user_agent;
	
	console.log("Documentacion.init_access_CONFIG(): " + access_CONFIG);
	
	$("#init-access-CONFIG").val(access_CONFIG).trigger("click");
};

Documentacion.init_access_on_complete = function(xhr, status, args)
{
	var init_access_OK = args.init_access_OK;		
	
	console.log("Documentacion.init_access_on_complete(): " + init_access_OK);
};

Documentacion.toggle_section_file = function()
{
	$("#section-add-file").show();
	$(".veloE").fadeIn();
};

Documentacion.init_file_upload = function()
{
	console.log("Documentacion.init_file_upload(): OK");
	
	$("div#init-file-upload").find("label.ui-button").click();	
};

Documentacion.cancel_add_file = function()
{
	$("#section-add-file").hide();
	$(".veloE").fadeOut();
};

Documentacion.file_upload_oncomplete = function()
{
	$("#section-add-file").hide();
	$(".veloE").fadeOut();
};

Documentacion.add_file_on_complete = function(xhr, status, args)
{
	var change_control_OK = args.change_control_OK;
	var persist_OK = args.persist_OK;
	
	console.log("Documentacion.add_file_on_complete(): ");
	console.log(" > persist_OK = " + persist_OK);
	console.log(" > change_control_OK = " + change_control_OK);
	
	
	$("#select-type_phone").val("0").trigger("change");
	$("#addnumphone").val("");
	$("#phone_extension").val("");
	$("#txt-reason-add-phone").val("");
	
	$("#section-add-file").hide();
	$(".veloE").fadeOut();
};

Documentacion.show_cropper = function(xhr, status, args)
{	

	var WDivRotate;
	var HDivRotate;	
	
	if(args.format=="PDF"){
		WDivRotate=550;
		HDivRotate=510;
	}else{
		var widthWindows=Number($(window).width())-30;
		var heightWindows=Number($(window).height())-30;			
		
		var WimgRotate=Number(args.Height);
		var HimgRotate=Number(args.Width);
			
		if(WimgRotate>widthWindows){
			WDivRotate=widthWindows-170;
		}else if(WimgRotate<widthWindows && WimgRotate>=500){
			WDivRotate=WimgRotate;
		}else{
			WDivRotate=500;
		}
		
		if(HimgRotate>heightWindows){
			HDivRotate=heightWindows-140;
		}else if(HimgRotate<heightWindows && HimgRotate>=500){
			HDivRotate=heightWindows-200;
		}else{
			HDivRotate=500;
		}	
	}
	
	
	$.fancybox({		
		padding : 	0,
		margin 	:	0,
		width	:	(WDivRotate+30)<=500?500:WDivRotate+30,
		height  :	(HDivRotate+120)<=500?500:HDivRotate+120,
		transitionIn: 'none',
		transitionOut : 'none',	
		'modal' : true,
		type : 'iframe',
		scrolling : 'auto',
		centerOnScroll : true,
		hideOnOverlayClick : false,
		showCloseButton : false,
		href: '../jsf/templates/croppedImage.xhtml',
		overlayColor: '#333333'

	});
};