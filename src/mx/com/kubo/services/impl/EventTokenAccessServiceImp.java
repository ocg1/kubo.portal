package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.EventTokenAccess;
import mx.com.kubo.model.EventTokenAccessPK;
import mx.com.kubo.repositories.EventTokenAccessDao;
import mx.com.kubo.services.EventTokenAccessService;

@Service
public class EventTokenAccessServiceImp implements Serializable,EventTokenAccessService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EventTokenAccessDao repository; 
	
	@Override
	public List<EventTokenAccess> getEventTokenAccessListByArea( String area ){
		
		return repository.getEventTokenAccessListByArea( area );
		
	}
	
	@Override
	public EventTokenAccess getEventTokenAccessByPK( EventTokenAccessPK pk ){
		
		return repository.getEventTokenAccessByPK( pk );
		
	}
	
}
