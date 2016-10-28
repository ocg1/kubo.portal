package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ContratoCreditoCollector;
import mx.com.kubo.repositories.ContratoCreditoCollectorDao;
import mx.com.kubo.services.ContratoCreditoCollectorService;

@Service
public class ContratoCreditoCollectorServiceImp implements ContratoCreditoCollectorService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContratoCreditoCollectorDao repository;
	
	public ContratoCreditoCollector getContratoCreditoCollectorByCreditId( String safi_credit_id ){
		
		return repository.getContratoCreditoCollectorByCreditId(safi_credit_id);
		
	}
	
}
