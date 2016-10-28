package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.RecommendationType;
import mx.com.kubo.repositories.RecommendationTypeDao;

import org.springframework.stereotype.Repository;

@Repository
public class RecommendationTypeDaoImp implements RecommendationTypeDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
		
    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.em = em;
    }
	
    @Override
	public List<RecommendationType> getRecommendationTypeLst(){
		
		List<RecommendationType> lst = em.createQuery("from RecommendationType rt order by rt.sequence",RecommendationType.class).getResultList();
		
		return lst;
		
		
	}
	
}
