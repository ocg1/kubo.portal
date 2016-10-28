package mx.com.kubo.repositories;

import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;

public interface  LoanNegotiationDao {
	
	public LoanNegotiation loadSelectedLoanNegotiation(LoanNegotiationPK pk);
	public void saveLoanNegotiation(LoanNegotiation newLoanNegotiation);
	public LoanNegotiation loadMaxLoanNegotiation(Integer prospectus_id, Integer company_id, Integer proyect_loan_id, Integer proyect_id);
	public void update(LoanNegotiation newLoanNegotiation);
	
}
