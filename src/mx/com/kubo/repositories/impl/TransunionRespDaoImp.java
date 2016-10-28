package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.TransunionResp;
import mx.com.kubo.repositories.TransunionRespDao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransunionRespDaoImp implements TransunionRespDao {

	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
	String ExceptionOnFunding = new String("");
	
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    @Transactional
    public TransunionResp getTransunionByBurSolNum( String bursolnum ){
    	
    	TransunionResp trans=null;
    	
    	try{
    	
    		trans = em.createQuery("from TransunionResp where mx_solicitud_buro = ?",TransunionResp.class).setParameter(1, bursolnum).getSingleResult();
    		
    	}catch(Exception e){
    		
    		System.out.println( e.getMessage() );
    		trans = null;
    		
    	}
    	
    	if( trans != null ){
    		
    		try{
				
				Hibernate.initialize(trans.getAjuste1());
				
			}catch(EntityNotFoundException nF){
				
				trans.setAjuste1(null);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			try{
				
				Hibernate.initialize(trans.getAjuste2());
				
			}catch(EntityNotFoundException nF){
				
				trans.setAjuste2(null);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
    		
    	}
    	
    	return trans;
    	
    }
	
	
}
