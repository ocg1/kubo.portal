console.log("Portal/resources/js/desbloqueo-password/security-answer.js");

DesbloqueoPassword.listener_security_answer = function(xhr, status, args)
{
	var security_answer_OK            = args.security_answer_OK;
	var failed_question_attempts      = args.failed_question_attempts;
	var max_question_attempts_ENABLED = args.max_question_attempts_ENABLED;;
	
	console.log("DesbloqueoPassword.listener_security_answer(): " + security_answer_OK);
	console.log("DesbloqueoPassword.listener_security_answer(): " + failed_question_attempts);
	console.log("DesbloqueoPassword.listener_security_answer(): " + max_question_attempts_ENABLED);
	
	if(security_answer_OK)
	{		
		this.cerrar("desbloquear-password");
		this.mostrar_modal("modal-init-login");
	}		
	
	if(max_question_attempts_ENABLED)
	{
		$("div#max-question-attempts-ENABLED").show();
		$("div#security-question").hide();
	}
	
	this.hide_process_icon();
};

DesbloqueoPassword.listener_init_login = function(xhr, status, args)
{
	var login_OK = args.login_OK;
	
	console.log("DesbloqueoPassword.listener_init_login(): " + login_OK);
	
	if(login_OK)
	{		
		$("div#modal-init-login a.close_lightbox").trigger("click");
		
		location.reload();
	}	
};