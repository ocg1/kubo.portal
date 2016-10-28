console.log("Portal/resources/js/editar-telefono/audtitoria-access.js");

EditorTelefono.init_access_CONFIG = function()
{		
	var user_agent = navigator.userAgent;
	
	var browser_width  = $(window).width();
	var browser_height = $(window).height();
	
	var access_CONFIG = browser_width + "::" + browser_height + "::" + user_agent;
	
	console.log("EditorTelefono.init_access_CONFIG(): " + access_CONFIG);
	
	$("#init-access-CONFIG").val(access_CONFIG).trigger("click");
};

EditorTelefono.init_access_on_complete = function(xhr, status, args)
{
	var init_access_OK = args.init_access_OK;		
	
	console.log("EditorTelefono.init_access_on_complete(): " + init_access_OK);
};