package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.InstitutionalInvestor;
import mx.com.kubo.model.InstitutionalInvestorPK;
import mx.com.kubo.model.InvestorCategory;


public interface InstitutionalInvestorService 
{
	InstitutionalInvestor getInstInvestorByID(InstitutionalInvestorPK pK);
	
	List<InstitutionalInvestor> getListInstInvestor();
	
	List<InvestorCategory> getLista_investor_category(int institutional_investor_id);		
}
