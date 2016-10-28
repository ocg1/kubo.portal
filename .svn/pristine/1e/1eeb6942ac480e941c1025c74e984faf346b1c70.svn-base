package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.TicketConfig;
import mx.com.kubo.repositories.TicketConfigDao;
import mx.com.kubo.services.TicketConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketConfigServiceImp implements Serializable, TicketConfigService  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private TicketConfigDao ticketconfigrepository;
	
	@Override
	public  List<TicketConfig> getTicketConfigLst(){
		
		return ticketconfigrepository.getTicketConfigLst();
		
	}
	
}
