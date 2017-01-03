package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.RelatedPersonLoan;
import mx.com.kubo.repositories.RelatedPersonLoanDao;
import mx.com.kubo.services.RelatedPersonLoanService;

@Service
public class RelatedPersonLoanServiceImp implements RelatedPersonLoanService, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	RelatedPersonLoanDao dao;
	
	public RelatedPersonLoan getRelatedPersonLoanByProyectLoanProspectus( Integer prospectus_id, Integer proyect_loan_id ){
		
		return dao.getRelatedPersonLoanByProyectLoanProspectus(prospectus_id, proyect_loan_id);
		
	}
    
    public boolean saveRelatedPersonLoan( RelatedPersonLoan related ){
    	
    	return dao.saveRelatedPersonLoan(related);
    	
    }
    
    public boolean updateRelatedPersonLoan( RelatedPersonLoan related ){
    	
    	return dao.updateRelatedPersonLoan(related);
    	
    }
	
}
