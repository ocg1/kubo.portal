package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InvestmentProgress;
import mx.com.kubo.repositories.InvestmentProgressDao;
import mx.com.kubo.services.InvestmentProgressService;

@Service
public class InvestmentProgressServiceImp implements Serializable,InvestmentProgressService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private InvestmentProgressDao dao;
	
	@Override
	public boolean saveInvestmentProgress( InvestmentProgress ip ){
		return dao.saveInvestmentProgress(ip);
	}
	
	@Override
	public boolean updateInvestmentProgress( InvestmentProgress ip ){
		return dao.updateInvestmentProgress(ip);
	}
	
	@Override
	public List<InvestmentProgress> getInvestmentProgressByDate( Date date ){
		return dao.getInvestmentProgressByDate( date );
	}
}
