package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.FormAnalytics;
import mx.com.kubo.repositories.FormAnalyticsDao;

@Repository
public class FormAnalyticsDaoImp implements Serializable, FormAnalyticsDao {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	
	@Transactional
	public boolean addFormAnalytics( FormAnalytics analytic ){
		
		try{
		
			em.persist(analytic);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
}
