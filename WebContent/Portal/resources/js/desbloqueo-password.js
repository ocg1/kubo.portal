console.log("Portal/resources/js/desbloqueo-password.js");

var DesbloqueoPassword = window.DesbloqueoPassword || {};

DesbloqueoPassword.init_access_CONFIG = function()
{		
	var user_agent = navigator.userAgent;
	
	var browser_width  = $(window).width();
	var browser_height = $(window).height();
	
	var access_CONFIG = browser_width + "::" + browser_height + "::" + user_agent;
	
	console.log("DesbloqueoPassword.init_access_CONFIG(): " + access_CONFIG);
	
	$("#init-access-CONFIG").val(access_CONFIG).trigger("blur");
};

DesbloqueoPassword.init_access_on_complete = function(xhr, status, args)
{
	var init_access_OK = args.init_access_OK;
	
	console.log("DesbloqueoPassword.init_access_on_complete(): " + init_access_OK);
};

DesbloqueoPassword.show_process_icon = function()
{
	$("div#desbloquear-password").find("div.loader").show();
};

DesbloqueoPassword.hide_process_icon = function()
{
	$("div#desbloquear-password").find("div.loader").hide();
};

DesbloqueoPassword.mostrar_modal = function(id)
{			
	var modal = "div#" + id;
	
	$(modal).css("display","block").fadeIn();
	$(".abrir_sesion").fadeOut();
	
	$(modal).css(
	{
	   	 position:'absolute',
	   	 left: ($(window).width() - $(modal).outerWidth()) / 2,
	});
	
	console.log("DesbloqueoPassword.mostrar_modal(): " + modal);
};

DesbloqueoPassword.regresar = function()
{
	$("div#radio-security_question").show();
	$("div#radio-desbloqueo-por-correo").show();
	$("div#confirmacion-security-question").hide();
	$("div#max-question-attempts-ENABLED").hide();
};

DesbloqueoPassword.cerrar = function(id)
{			
	var modal = "div#" + id;
	
	$(modal).hide();
	
	console.log("DesbloqueoPassword.cerrar(): " + modal);
};

