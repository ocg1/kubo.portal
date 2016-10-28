console.log("Portal/resources/js/desbloqueo-password/por-correo.js");

DesbloqueoPassword.init_desbloqueo_por_correo = function()
{
	$("div#email-desbloqueo-por-correo").show();
	$("div#radio-security_question").hide();
	$("div#radio-desbloqueo-por-correo").hide();
	
	var email = $("input#email").val();
	
	console.log("DesbloqueoPassword.init_desbloqueo_por_correo(): " + email);
		
	$("div#email-desbloqueo-por-correo").find("input").val(email).trigger("blur");		
};

DesbloqueoPassword.listener_desbloqueo_por_correo = function(xhr, status, args)
{
	var desbloqueo_por_correo_OK = args.desbloqueo_por_correo_OK;
	
	console.log("DesbloqueoPassword.listener_desbloqueo_por_correo(): " + desbloqueo_por_correo_OK);
	
	if(desbloqueo_por_correo_OK)
	{
		this.cerrar("email-desbloqueo-por-correo");		
		this.cerrar("desbloqueo-password-INPUT");
		
		$("div#confirmacion-desbloqueo-por-correo").show();
	}
	
	this.hide_process_icon();
};

DesbloqueoPassword.cerrar_confirmacion_correo = function()
{
	$("div#modal-init-login a.close_lightbox").trigger("click");	
	
	location.reload();
};