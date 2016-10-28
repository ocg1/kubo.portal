package mx.com.kubo.managedbeans.portal.password;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.portal.AccessIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;

public interface DesbloqueoPasswordIMO 
{
	DesbloqueoPorCorreoIMO getPor_correo();
	  PreguntaSeguridadIMO getPor_pregunta();
				 AccessIMO getAuditor();
	
	String getDisplay_desbloqueo_MSG();
	
	boolean isSecurity_question_ENABLED();
	
	void setService_membership(MembershipService service);
	void setService_access    (AccessService     service);
	
	void listener_email    (AjaxBehaviorEvent event);
	
	void init_security_question();
	void init_desbloqueo_por_correo();
	void init_login_redirect();
}
