package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.RetornoAnual;
import mx.com.kubo.repositories.RetornoAnualDao;
import mx.com.kubo.services.RetornoAnualService;

@Service
public class RetornoAnualServiceImp implements Serializable, RetornoAnualService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RetornoAnualDao dao;
	
	public RetornoAnual getRetornoAnual( int cuentaAhoId ){
		return dao.getRetornoAnual(cuentaAhoId);
	}
	
}
