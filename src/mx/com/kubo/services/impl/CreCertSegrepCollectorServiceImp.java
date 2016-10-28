package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.CreCertSegrepCollector;
import mx.com.kubo.repositories.CreCertSegrepCollectorDao;
import mx.com.kubo.services.CreCertSegrepCollectorService;

@Service
public class CreCertSegrepCollectorServiceImp implements Serializable, CreCertSegrepCollectorService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CreCertSegrepCollectorDao repository;
	
	@Override
	public CreCertSegrepCollector getCreCertSegrep( String safi_credit_id ){
		return repository.getCreCertSegrep(safi_credit_id);
	}
	
}
