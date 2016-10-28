package mx.com.kubo.test.login;

import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;

public interface LoginTestIMO 
{
	void iniciar_sesion();
	
	void setService_login      (LoginServiceIMO   service);
	void setService_membership (MembershipService service);
	
	boolean isSesion_ENABLED();
}
