package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InvestorParam;
import mx.com.kubo.model.InvestorParamPK;
import mx.com.kubo.repositories.InvestorParamDao;
import mx.com.kubo.services.InvestorParamService;

@Service
public class InvestorParamServiceImp implements Serializable, InvestorParamService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private InvestorParamDao dao;
	
	public InvestorParam getInvestorParamByPK( InvestorParamPK pk ){
		return dao.getInvestorParamByPK(pk);
	}
	
	public List<InvestorParam> getInvestorParamByProspectus( int prospectus_id, int company_id ){
		return dao.getInvestorParamByProspectus( prospectus_id, company_id );
	}
	
	public boolean updateInvestorParam( InvestorParam model ){
		return dao.updateInvestorParam(model);
	}
	
	public boolean saveInvestorParam( InvestorParam model ){
		return dao.saveInvestorParam(model);
	}
	
	
}
