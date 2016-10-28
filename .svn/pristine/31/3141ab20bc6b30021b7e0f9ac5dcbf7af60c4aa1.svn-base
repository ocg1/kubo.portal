package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.InvestmentFilter;
import mx.com.kubo.repositories.InvestmentFilterDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InvestmentFilterDaoImp implements Serializable,InvestmentFilterDao {

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	/**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
    @Transactional
    @Override
	public boolean addFilterUsed( InvestmentFilter filter ){
    	Integer i = 0;
    	try{
    		
    		String query="select MAX(s.pk.filter_id) from InvestmentFilter s";
    		
    		i=(Integer) em.createQuery(query).getSingleResult();	
	    	}catch( Exception e ){
	    		e.printStackTrace();
	    		return false;
	    		
	    	}
    	
	    	if(i==null)
		    	i=1;
	    	else
	    		i++;
	    	
	    	filter.getPk().setFilter_id(i);
	    	
	    	em.persist(filter);
	    	
	    	return true;
    	
    	
    	
    }
    
}
