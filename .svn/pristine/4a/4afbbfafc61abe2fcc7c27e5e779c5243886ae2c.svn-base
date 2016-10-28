package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ProyectLoanInfo;
import mx.com.kubo.model.ProyectLoanInfoPK;
import mx.com.kubo.repositories.ProyectLoanInfoDao;
import mx.com.kubo.services.ProyectLoanInfoService;

@Service
public class ProyectLoanInfoServiceImp implements Serializable, ProyectLoanInfoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private ProyectLoanInfoDao dao;
	
	@Override
	public boolean saveProyectLoanInfo( ProyectLoanInfo proyectloaninfo ){
		return dao.saveProyectLoanInfo(proyectloaninfo);
	}
	
	@Override
	public boolean updateProyectLoanInfo( ProyectLoanInfo proyectloaninfo ){
		return dao.updateProyectLoanInfo(proyectloaninfo);
	}
	public ProyectLoanInfo getProyectLoanInfo( ProyectLoanInfoPK pk ){
		return dao.getProyectLoanInfo(pk);
	}
	
}
