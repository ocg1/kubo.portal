package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.ContractSignature;
import mx.com.kubo.model.ContractSignaturePK;
import mx.com.kubo.repositories.ContractSignatureDao;

@Repository
public class ContractSignatureDaoImp implements Serializable, ContractSignatureDao {

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
	
   
    public ContractSignature getContractSignature ( ContractSignaturePK pk ){
    	
    	try{
    		
    		return em.find(ContractSignature.class, pk);
    		
    	}catch(Exception e){
    		
    		System.out.println("Sin Contrato !!");
    		return null;
    		
    	}
    	
    }
    
    @Transactional
    public boolean addContractSignature ( ContractSignature contractSig ){
    	
    	try{
    		
    		em.persist( contractSig );
    		return true;
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    	
    }
    
    @Transactional
    public boolean updateContractSignature ( ContractSignature contractSig ){
    	
    	try{
    		
    		em.merge( contractSig );
    		return true;
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    	
    }
    
}
