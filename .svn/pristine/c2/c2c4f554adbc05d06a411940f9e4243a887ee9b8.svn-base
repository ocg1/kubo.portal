package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.Referred;
import mx.com.kubo.model.ReferredPK;
import mx.com.kubo.repositories.ReferredDao;
import mx.com.kubo.services.ReferredService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferredServiceImp implements Serializable, ReferredService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReferredDao referredrepository;
	@Override
	public boolean addReferred( Referred referred ){
	
		return referredrepository.addReferred(referred);
		
	}
	
	@Override
	public boolean removeReferred( Referred referred ){
		
		return referredrepository.removeReferred(referred);
		
	}
	
	@Override
	public boolean updateReferred( Referred referred ){
		
		return referredrepository.updateReferred(referred);
		
	}
	
	@Override
	public Referred getReferred( ReferredPK pk ){
		
		return referredrepository.getReferred( pk );
		
		
	}
	
	@Override
	public Referred getQuienLoRecomendo( int prospectus_id_destiny,int company_id ){
		return referredrepository.getQuienLoRecomendo(prospectus_id_destiny,company_id);
		
	}

}
