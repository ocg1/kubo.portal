package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.OfferRejectionMotive;
import mx.com.kubo.repositories.OfferRejectionMotiveDao;

@Repository
public class OfferRejectionMotiveDaoImp implements Serializable, OfferRejectionMotiveDao {
	
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
	
	public List<OfferRejectionMotive> getOfferRejectionMotiveList(){
		try{
			
			String query = " from OfferRejectionMotive where is_enabled = 'S' order by order_id";
			
			return em.createQuery(query, OfferRejectionMotive.class).getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
	}
	

}
