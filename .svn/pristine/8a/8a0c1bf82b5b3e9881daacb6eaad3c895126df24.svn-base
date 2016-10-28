package mx.com.kubo.managedbeans.portal.password;

import javax.faces.component.html.HtmlInputSecret;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.tools.Utilities;

public final class PreguntaSeguridadIMP extends PreguntaSeguridadAMO
implements PreguntaSeguridadIMO
{	
	public final void listener_security_answer(AjaxBehaviorEvent event)
	{
		input_secret = (HtmlInputSecret) event.getComponent();
		
		security_answer_INPUT = input_secret.getValue().toString();
		
		security_answer_UPP = Utilities.encrypt(security_answer_INPUT.toUpperCase());
		security_answer_LOW = Utilities.encrypt(security_answer_INPUT.toLowerCase());
	}
		
	public final void init_security_answer()
	{
		request = RequestContext.getCurrentInstance();
		
		security_answer_OK = security_answer.equals(security_answer_UPP) || security_answer.equals(security_answer_LOW);
		
		if(security_answer_OK)
		{				
			init_security_question_pool();
			init_membership();						
			init_notificar_evento();
			
		} else {
			
			failed_question_attempts++;
			
			membership.setFailed_question_attempts(failed_question_attempts);			
			
			service_membership.update(membership);	
			
			if(failed_question_attempts < max_question_attempts)
			{		
				max_question_attempts_ENABLED = false;
				
			} else {
				
				max_question_attempts_ENABLED = true;
			}
		}
		
		request.addCallbackParam("security_answer_OK", security_answer_OK);
		request.addCallbackParam("failed_question_attempts", failed_question_attempts);
		request.addCallbackParam("max_question_attempts_ENABLED", max_question_attempts_ENABLED);
	}

	public final void listener_init_login()
	{
		request = RequestContext.getCurrentInstance();
		
		login_OK = false;
		
		if(security_answer_OK)
		{	
			init_login();
		}
		
		request.addCallbackParam("login_OK", login_OK);
	}
}
