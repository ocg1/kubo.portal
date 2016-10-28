package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InfoScreen;
import mx.com.kubo.model.InfoScreenPK;
import mx.com.kubo.repositories.InfoScreenDao;
import mx.com.kubo.services.InfoScreenService;

@Service
public class InfoScreenServiceImp implements InfoScreenService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InfoScreenDao dao;
	
	public InfoScreen getInfoScreenById( InfoScreenPK pk ){
		return dao.getInfoScreenById(pk);
	}
	
}
