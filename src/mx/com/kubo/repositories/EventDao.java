package mx.com.kubo.repositories;

import mx.com.kubo.model.Event;
import mx.com.kubo.model.EventPK;

public interface EventDao {
	public abstract Event getEventByID(EventPK eventPK);
}
