package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.InstitutionalInvestor;
import mx.com.kubo.model.InstitutionalInvestorPK;
import mx.com.kubo.model.InvestorCategory;
import mx.com.kubo.repositories.InstitutionalInvestorDao;
import mx.com.kubo.services.InstitutionalInvestorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionalInvestorServiceImp 
implements InstitutionalInvestorService
{
	@Autowired
	private InstitutionalInvestorDao dao;
	
	public List<InstitutionalInvestor> getListInstInvestor() 
	{
		return dao.getListInstInvestor();
	}
	
	public List<InvestorCategory> getLista_investor_category(int institutional_investor_id)
	{
		return dao.getLista_investor_category(institutional_investor_id);
	}
	
	public InstitutionalInvestor getInstInvestorByID(InstitutionalInvestorPK pK) 
	{
		return dao.getInstInvestorByID(pK);
	}

}
