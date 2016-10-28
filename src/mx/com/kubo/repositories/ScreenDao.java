package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;

public interface ScreenDao {
	
	public Screen loadSelectedScreen(ScreenPK pk);
	public List<Screen> getLstScreenByArea(int company_id,String area);
	
}
