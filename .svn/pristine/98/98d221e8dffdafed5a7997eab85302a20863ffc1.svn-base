package mx.com.kubo.services.impl;

import mx.com.kubo.model.Event;
import mx.com.kubo.model.EventPK;
import mx.com.kubo.repositories.EventDao;
import mx.com.kubo.services.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImp implements EventService{

	@Autowired
	private EventDao eventDao;
	
	@Override
	public Event getEventByID(EventPK eventPK) {
		return eventDao.getEventByID(eventPK);
	}
	
}
