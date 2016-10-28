package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.Operating_cost_typePK;
import mx.com.kubo.repositories.OperationCostTypeDao;

import org.springframework.stereotype.Repository;


@Repository
public class OperationCostTypeDaoImp implements Serializable, OperationCostTypeDao  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Operating_cost_type getOperatingCostTypeById(Operating_cost_typePK pk){
    	try{
    		
    		return em.find(Operating_cost_type.class, pk);
    		
    	}catch(Exception e){
    		return null;
    	}
    }
	
}
