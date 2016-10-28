package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Screen;
import mx.com.kubo.repositories.impl.AccessIPCheckIMO;
import mx.com.kubo.repositories.impl.AccessIPCheckIMP;
import mx.com.kubo.services.AccessService;

import org.springframework.stereotype.Service;

@Service
public class AccessServiceImp extends ServiceAccessAMO
implements AccessService 
{	
	public void add(Access newAccess, boolean valida) 
	{		
		if( valida ){
		Access access_LAST = dao.loadMaxAccess(newAccess.getProspectus_id(), newAccess.getCompany_id());		
		
		AccessIPCheckIMP check = new AccessIPCheckIMP();
		check.setDAO_prospectus    (dao_prospectus);
		check.setDAO_change_control(dao_change_control);
		check.setDAO_system_param  (dao_system_param);
		check.setAccess_LAST(access_LAST);
		check.setAccess_NEW (newAccess);		
		check.init_ip_address_CHANGED();
		}
		dao.saveAccess(newAccess);		
	}
	
	public List<AccessCollector> loadControlMenu(Integer role_id, Integer company_id){
		
		return dao.loadControlMenu( role_id, company_id);
		
	}
	
	public void add(int screen_id, int porcentaje, SessionBean sesion, boolean va) 
	{				
		Access access_NEW  = init_access_NEW(screen_id, porcentaje, sesion);
		
		Access access_LAST = dao.loadMaxAccess(access_NEW.getProspectus_id(), access_NEW.getCompany_id());		
		
		AccessIPCheckIMO check = new AccessIPCheckIMP();
		check.setDAO_prospectus    (dao_prospectus);
		check.setDAO_change_control(dao_change_control);
		check.setDAO_system_param  (dao_system_param);
		check.setAccess_LAST(access_LAST);
		check.setAccess_NEW (access_NEW);		
		check.init_ip_address_CHANGED();
		
		dao.saveAccess(access_NEW);		
	}
		
	public void update(Access newAccess)
	{
		dao.update(newAccess);
	}
	
	public Screen getLastAccessMenuScreen( int prospectus_id, int company_id, String area  )
	{
		return dao.getLastAccessMenuScreen(prospectus_id, company_id, area);
	}
	
}
