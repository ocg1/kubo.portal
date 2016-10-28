package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InvestmentProgressDet;
import mx.com.kubo.repositories.InvestmentProgressDetDao;
import mx.com.kubo.services.InvestmentProgressDetService;

@Service
public class InvestmentProgressDetServiceImp implements InvestmentProgressDetService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InvestmentProgressDetDao dao;
	
	@Override
	public boolean saveInvestmentProgressDet(InvestmentProgressDet ipd){
		return dao.saveInvestmentProgressDet(ipd);
	}
	
	public boolean updateInvestmentProgressDet(InvestmentProgressDet ipd){
		return dao.updateInvestmentProgressDet(ipd);
	}
	
}
