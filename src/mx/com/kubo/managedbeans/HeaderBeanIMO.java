package mx.com.kubo.managedbeans;

import mx.com.kubo.model.Membership;
import mx.com.kubo.services.AccessService;

public interface HeaderBeanIMO 
{	
	String getFecha_acceso_ACTUAL();
	String getFecha_acceso_LAST();
	
	String iniciaSesion();
	void activeSession();
	
	String iniciaSesionCoach(Membership member_coach, Membership member_user, boolean inicia_con_usuario );
	
	void setService_access(AccessService service);
}
