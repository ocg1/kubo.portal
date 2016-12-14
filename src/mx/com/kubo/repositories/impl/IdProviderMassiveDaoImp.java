package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.IdProviderMassive;
import mx.com.kubo.model.TrackingCode;
import mx.com.kubo.repositories.IdProviderMassiveDao;

@Repository
public class IdProviderMassiveDaoImp implements Serializable, IdProviderMassiveDao {
	
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

	public List<IdProviderMassive> getIdProviderMassiveByTrackingCode( String code ){
		String query ="from IdProviderMassive where tracking_code = ?";
		return em.createQuery(query, IdProviderMassive.class).setParameter(1, code).getResultList();
		
	}
	
	@Transactional
	public boolean saveIdProviderMassive( IdProviderMassive massive ) {
		try{
			
			em.persist(massive);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean saveTrackingCode( TrackingCode tracking ) {
		try{
			
			em.persist(tracking);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public TrackingCode getTrackingCodeByCode( String  code ) {
		try{
			
			String query = "from TrackingCode where tracking_code = ? ";
			
			return em.createQuery(query, TrackingCode.class).setParameter(1, code).getSingleResult();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	public boolean updateIdProviderMassive( IdProviderMassive massive ) {
		
		try{
			
			em.merge(massive);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
}
