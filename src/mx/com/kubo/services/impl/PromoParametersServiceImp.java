package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.PromoParameters;
import mx.com.kubo.repositories.PromoParametersDao;
import mx.com.kubo.services.PromoParametersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoParametersServiceImp implements Serializable, PromoParametersService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PromoParametersDao dao;
	
	public List<PromoParameters> getPromoParametersByCode( String code ){
		
		return dao.getPromoParametersByCode( code );
		
	}
	
	
}
