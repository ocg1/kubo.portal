package mx.com.kubo.services.impl;

import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;
import mx.com.kubo.repositories.LoanNegotiationDao;
import mx.com.kubo.services.LoanNegotiationService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoanNegotiationServiceImp implements LoanNegotiationService {
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private LoanNegotiationDao loanNegotiationRepository;

	public LoanNegotiationDao getLoanNegotiationRepository() {
		return loanNegotiationRepository;
	}
	
	public void setLoanNegotiationRepository(
			LoanNegotiationDao loanNegotiationRepository) {
		this.loanNegotiationRepository = loanNegotiationRepository;
	}
	@Override
	public LoanNegotiation loadSelectedLoanNegotiation(LoanNegotiationPK pk){
		return loanNegotiationRepository.loadSelectedLoanNegotiation(pk);
	}
	@Override
	public void saveLoanNegotiation(LoanNegotiation newLoanNegotiation){
		loanNegotiationRepository.saveLoanNegotiation(newLoanNegotiation);
	}
	@Override
	public LoanNegotiation loadMaxLoanNegotiation(Integer prospectus_id, Integer company_id,Integer proyect_loan_id, Integer proyect_id){
		return loanNegotiationRepository.loadMaxLoanNegotiation(prospectus_id, company_id, proyect_loan_id,  proyect_id);
	}
	@Override
	public void update(LoanNegotiation newLoanNegotiation){
		loanNegotiationRepository.update(newLoanNegotiation);
	}
	
}
