package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.IdProviderMassive;
import mx.com.kubo.model.TrackingCode;
import mx.com.kubo.repositories.IdProviderMassiveDao;
import mx.com.kubo.services.IdProviderMassiveService;

@Service
public class IdProviderMassiveServiceImp implements Serializable,IdProviderMassiveService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	IdProviderMassiveDao dao;
	
	public List<IdProviderMassive> getIdProviderMassiveByTrackingCode( String code ){
		return dao.getIdProviderMassiveByTrackingCode(code);
	}
	
	public boolean saveIdProviderMassive( IdProviderMassive massive ){
		return dao.saveIdProviderMassive(massive);
	}
	
	public boolean saveTrackingCode( TrackingCode tracking ){
		
		return dao.saveTrackingCode( tracking );
		
	}
	
	public TrackingCode getTrackingCodeByCode( String  code ){
		
		return dao.getTrackingCodeByCode( code );
		
	}
	
	public boolean updateIdProviderMassive( IdProviderMassive massive ){
		return dao.updateIdProviderMassive( massive );
	}
	
}
