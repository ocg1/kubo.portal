package mx.com.kubo.repositories;

import mx.com.kubo.model.RelatedPersonLoan;

public interface RelatedPersonLoanDao {

	public RelatedPersonLoan getRelatedPersonLoanByProyectLoanProspectus( Integer prospectus_id, Integer proyect_loan_id );
    
    public boolean saveRelatedPersonLoan( RelatedPersonLoan related );
    
    public boolean updateRelatedPersonLoan( RelatedPersonLoan related );
	
}
