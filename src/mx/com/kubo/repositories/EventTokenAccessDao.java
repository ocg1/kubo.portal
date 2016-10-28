package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.EventTokenAccess;
import mx.com.kubo.model.EventTokenAccessPK;

public interface EventTokenAccessDao {
	
	public List<EventTokenAccess> getEventTokenAccessListByArea( String area );
	
	public EventTokenAccess getEventTokenAccessByPK( EventTokenAccessPK pk );
		
}
