package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.RelatedPersonLoan;
import mx.com.kubo.repositories.RelatedPersonLoanDao;

@Repository
public class RelatedPersonLoanDaoImp implements Serializable , RelatedPersonLoanDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	  /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    public RelatedPersonLoan getRelatedPersonLoanByProyectLoanProspectus( Integer prospectus_id, Integer proyect_loan_id ){
    	
    	try{
    		
    		String query = "from RelatedPersonLoan where prospectus_id = ? and proyect_loan_id = ?";
    		return em.createQuery(query, RelatedPersonLoan.class).setParameter(1, prospectus_id).setParameter(2, proyect_loan_id).getSingleResult();
    		
    	}catch( NoResultException nr ){
    		
    		return null;
    		
    	}catch( Exception e ){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
    
    @Transactional
    public boolean saveRelatedPersonLoan( RelatedPersonLoan related ){
    	
    	try{
    		
    		log.info("getMaxRelatedPersonLoan");
    		
    		String query="select MAX(rpl.pk.related_person_loan_id) from RelatedPersonLoan rpl";
    				
    		Integer related_id = (Integer) em.createQuery(query).getSingleResult();
    		
    		if(related_id == null)
    		{
    			related_id = 0;
    		}
    		
    		related_id = related_id + 1;
    		
    		related.getPk().setRelated_person_loan_id( related_id );
    		
    		em.persist(related);
    		
    		return true;
    		
    	}catch( Exception e ){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    	
    }
    
    @Transactional
    public boolean updateRelatedPersonLoan( RelatedPersonLoan related ){
    	
    	try{
    		
    		em.merge(related);
    		return true;
    		
    	}catch( Exception e ){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    	
    }
    
	
}
