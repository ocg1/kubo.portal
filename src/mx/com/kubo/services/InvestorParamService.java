package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.InvestorParam;
import mx.com.kubo.model.InvestorParamPK;

public interface InvestorParamService {

	public InvestorParam getInvestorParamByPK( InvestorParamPK pk );
	
	public List<InvestorParam> getInvestorParamByProspectus( int prospectus_id, int company_id );
	
	public boolean updateInvestorParam( InvestorParam model );
	
	public boolean saveInvestorParam( InvestorParam model );
	
}
