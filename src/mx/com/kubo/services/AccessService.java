package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Screen;

public interface AccessService 
{
	Access loadMaxAccess(Integer prospectus_id, Integer company_id);
	Access getAccessById(int id);	
	Access getMaxAccess(Integer prospectus_id, Integer company_id);
	Access getMaxAccessByScreen(int prospectus_id, int company_id, int screen_id );
	
	
	List<AccessCollector> loadMenu(Integer prospectus_id, Integer company_id, Character area);	
	List<AccessCollector> loadControlMenu(Integer role_id, Integer company_id);
	List<AccessCollector> getMenuIncomplete(Integer prospectus_id, Integer company_id, Character area);
	
	void add   (Access newAccess, boolean valid);
	void add (int screen_id, int porcentaje, SessionBean sesion, boolean valid);
	void update(Access newAccess);
	
	Screen getLastAccessMenuScreen( int prospectus_id, int company_id, String area  );
	
}
