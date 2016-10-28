package mx.com.kubo.repositories;

import mx.com.kubo.model.InfoScreen;
import mx.com.kubo.model.InfoScreenPK;

public interface InfoScreenDao {

	public InfoScreen getInfoScreenById( InfoScreenPK pk );
	
}
