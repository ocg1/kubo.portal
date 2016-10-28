package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.PagareCollector;
import mx.com.kubo.repositories.PagareCollectorDao;
import mx.com.kubo.services.PagareCollectorService;

@Service
public class PagareCollectorServiceImp implements PagareCollectorService, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	PagareCollectorDao repository;
	
	public List<PagareCollector> getPagareCollectorList( String safi_credit_id ){
		
		return repository.getPagareCollectorList(safi_credit_id);
		
	}
	
}
