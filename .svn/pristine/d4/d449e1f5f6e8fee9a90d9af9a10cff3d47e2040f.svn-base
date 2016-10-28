package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.Prospector;
import mx.com.kubo.repositories.ProspectorDao;
import mx.com.kubo.services.ProspectorService;

@Service
public class ProspectorServiceImp implements Serializable, ProspectorService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ProspectorDao dao;

	@Override
	public boolean saveProspector( Prospector prospector ){
		return dao.saveProspector(prospector);
	}
	
	@Override
	public boolean updateProspector( Prospector prospector ){
		
		return dao.updateProspector(prospector);
		
	}
	
	@Override
	public Prospector getMaxProspector( int prospectus_id, int company_id ){
		
		return dao.getMaxProspector(prospectus_id, company_id);
		
	}
	
	
}
