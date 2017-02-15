package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.PendingNotification;
import mx.com.kubo.repositories.PendingNotificationDao;

@Repository
public class PendingNotificationDaoImp implements Serializable, PendingNotificationDao {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public boolean savePendingNotification( PendingNotification pending ){
		try{
			
			em.persist(pending);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Transactional
	public boolean updatePendingNotification( PendingNotification pending ){
		try{
			
			em.merge(pending);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public List<PendingNotification> getPendingNotificationStatusCero( ){
		try{
			
			return em.createQuery("from PendingNotification where status_id = 0", PendingNotification.class).getResultList(); 
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean existPendingNotification( int company_id, int prospectus_id, int event_id ){
		
		try{
			
			PendingNotification  pn = em.createQuery("from PendingNotification where company_id = ? and prospectus_id = ? and  event_id = ? and status_id = 0 ", PendingNotification.class)
												.setParameter(1, company_id).setParameter(2, prospectus_id).setParameter(3, event_id).getSingleResult();
			
			if(pn != null){
				
				return true;
				
			}else{
				
				return false;
				
			}
			
		}catch( NoResultException nre  ){
			return false;
		}catch( EntityNotFoundException nf ){
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
}
