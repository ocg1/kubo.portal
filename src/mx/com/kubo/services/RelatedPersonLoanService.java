package mx.com.kubo.services;

import mx.com.kubo.model.RelatedPersonLoan;

public interface RelatedPersonLoanService {

	public RelatedPersonLoan getRelatedPersonLoanByProyectLoanProspectus( Integer prospectus_id, Integer proyect_loan_id );
    
    public boolean saveRelatedPersonLoan( RelatedPersonLoan related );
    
    public boolean updateRelatedPersonLoan( RelatedPersonLoan related );
	
}
