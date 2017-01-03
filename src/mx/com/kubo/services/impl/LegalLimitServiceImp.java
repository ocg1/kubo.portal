package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.LegalLimit;
import mx.com.kubo.model.LegalLimitPK;
import mx.com.kubo.repositories.LegalLimitDao;
import mx.com.kubo.services.LegalLimitService;

@Service
public class LegalLimitServiceImp implements Serializable,LegalLimitService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	LegalLimitDao dao;

	public LegalLimit getLegalLimitByPK( LegalLimitPK lpk ){
		return dao.getLegalLimitByPK(lpk);
	}
	
}
