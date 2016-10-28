package mx.com.kubo.managedbeans.portal;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.SystemParamService;

public interface MembershipCheckIMO 
{
	void setService_login(LoginServiceIMO service);
	void setService_system_param(SystemParamService service);
	void setMemmbership(String email);
	public void setFBMembership(String fb_id,String fb_mail) ;
	void setSesion(SessionBean sesion);
	
	void init();
	
	String getCheck_msg_TOKEN();
	
	boolean isMembership_ENABLED();
	boolean isDesbloqueo_password_ENABLED();	
	boolean isBlocked_person_ENABLED();
	boolean isKubo_person_ENABLED();
	
	public Membership getMembership();
}
