package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Screen;

public interface AccessDao 
{
		
	void saveAccess(Access newAccess);
	void update(Access newAccess);
	
	Access loadSelectedAccess(int id);
	Access loadMaxAccess(Integer prospectus_id, Integer company_id);
	Access getMaxAccessByScreen(int prospectus_id, int company_id, int screen_id );
	
	List<AccessCollector> loadMenu(Integer prospectus_id, Integer company_id,Character area);
	List<AccessCollector> getMenuIncomplete(Integer prospectus_id, Integer company_id,Character area);
	
	Screen getLastAccessMenuScreen( int prospectus_id, int company_id, String area  );
	
	List<AccessCollector> loadControlMenu(Integer role_id, Integer company_id);
	
}
