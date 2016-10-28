package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.PromoParameters;
import mx.com.kubo.repositories.PromoParametersDao;

import org.springframework.stereotype.Repository;

@Repository
public class PromoParametersDaoImp implements Serializable, PromoParametersDao {

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
    
    public List<PromoParameters> getPromoParametersByCode( String code ){
    	
    	try{
    		
    		String query = "from PromoParameters where pk.promo_code = ? order by pk.position ";
    		
    		List<PromoParameters> lst =  em.createQuery(query, PromoParameters.class).setParameter(1, code).getResultList();
    		
    		return lst;
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
	
}
