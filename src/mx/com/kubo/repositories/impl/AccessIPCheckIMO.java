package mx.com.kubo.repositories.impl;

import mx.com.kubo.model.Access;
import mx.com.kubo.repositories.Change_controlDAO;
import mx.com.kubo.repositories.ProspectusDao;
import mx.com.kubo.repositories.SystemParamDao;

public interface AccessIPCheckIMO 
{
	void setAccess_NEW (Access access);
	void setAccess_LAST(Access access);
	
	void init_ip_address_CHANGED();
	
	void setDAO_prospectus     (ProspectusDao    dao);
	void setDAO_change_control(Change_controlDAO dao);
	void setDAO_system_param  (SystemParamDao    dao);
}
