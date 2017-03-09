package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.UltimosDesembolsos;
import mx.com.kubo.repositories.UltimosDesembolsosDao;

@Repository
public class UltimosDesembolsosDaoImp implements Serializable,UltimosDesembolsosDao  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
    public List<UltimosDesembolsos> getUltimosDesembolsos(){
    
    	try{
    	
    		return em.createNamedQuery("getUltimosDesembolsos",UltimosDesembolsos.class).getResultList();
    	
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    
    }
	
}
