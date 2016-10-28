console.log("Portal/resources/js/desbloqueo-password/security-question.js");

DesbloqueoPassword.security_question_init = function()
{	
	$("div#email-pregunta-seguridad").show();
	$("div#radio-security_question").hide();
	$("div#radio-desbloqueo-por-correo").hide();
	
	var email = $("input#email").val();
	
	console.log("DesbloqueoPassword.security_question.init(): " + email);
		
	$("div#email-pregunta-seguridad").find("input").val(email).trigger("blur");		
};

DesbloqueoPassword.security_question_on_complete = function(xhr, status, args)
{
	var security_question_ENABLED = args.security_question_ENABLED;
	
	if(security_question_ENABLED)
	{
		$("div#email-pregunta-seguridad").hide();
		$("div#security-question").show();
		
	} else {
		
		$("div#confirmacion-security-question").show();
		$("div#email-pregunta-seguridad").hide();
	}
	
	console.log("DesbloqueoPassword.security_question.on_complete(): " + security_question_ENABLED);
	
	this.hide_process_icon();
};