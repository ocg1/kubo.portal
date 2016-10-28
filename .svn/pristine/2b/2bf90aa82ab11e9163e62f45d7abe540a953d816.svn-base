package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.EventNotification;
import mx.com.kubo.repositories.EventNotificationDao;
import mx.com.kubo.services.EventNotificationService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventNotificationServiceImp implements EventNotificationService {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private EventNotificationDao eventnotificationRepository;
	
	@Override
	public List<EventNotification> loadListProspectusByEvent(int event){
		return eventnotificationRepository.loadListProspectusByEvent(event);
	}
}
