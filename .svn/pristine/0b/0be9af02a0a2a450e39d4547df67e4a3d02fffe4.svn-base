package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ProyectInfo;
import mx.com.kubo.repositories.ProyectInfoDao;
import mx.com.kubo.services.ProyectInfoService;

@Service
public class ProyectInfoServiceImp implements ProyectInfoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProyectInfoDao dao;
	
	public ProyectInfo getProyectInfoByProyectLoan( Integer proyect_loan_id ){
		return dao.getProyectInfoByProyectLoan( proyect_loan_id );
	}
	
}
