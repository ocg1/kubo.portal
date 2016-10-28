package mx.com.kubo.managedbeans.perfil;

import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;

public interface EditorCorreoIMO 
{
	void setService_membership(MembershipService service);
	void setService_login(LoginServiceIMO service);
	void setService_change_control(Change_controlService service);
}
