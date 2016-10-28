package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.IdentifiedCredit;
import mx.com.kubo.model.IdentifiedCreditPK;
import mx.com.kubo.repositories.IdentifiedCreditDao;
import mx.com.kubo.services.IdentifiedCreditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentifiedCreditServiceImp implements IdentifiedCreditService,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IdentifiedCreditDao identifiedcreditRepository;
	
	@Override
	public boolean saveIdentifiedCredit(IdentifiedCredit credit){
		return identifiedcreditRepository.saveIdentifiedCredit(credit);
	}
	
	@Override
	public List<IdentifiedCredit> getIdentifiedCreditListByProspectus(int company_id, int prospectus_id){
		return identifiedcreditRepository.getIdentifiedCreditListByProspectus(company_id, prospectus_id);
	}
	
	@Override
	public boolean updateIdentifiedCredit(IdentifiedCredit credit){
		return identifiedcreditRepository.updateIdentifiedCredit(credit);
	}
	
	@Override
	public IdentifiedCredit getIdentifiedCreditByPK(IdentifiedCreditPK pk){
		return identifiedcreditRepository.getIdentifiedCreditByPK(pk);
	}

}
