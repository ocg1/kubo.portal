package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.InfoNotification;
import mx.com.kubo.repositories.InfoNotificationDao;

@Repository
public class InfoNotificationDaoImp implements InfoNotificationDao, Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	public List<InfoNotification> getInfoNotificationBy( Integer company_id, Integer prospectus_id, Integer proyect_loan_id, int screen_info_id )
	{
		try{
		
		String query = "from InfoNotification where company_id = ? and prospectus_id = ? and proyect_loan_id = ? and info_screen_id = ?";
		
		return em.createQuery(query , InfoNotification.class)
			.setParameter(1, company_id)
			.setParameter(2, prospectus_id)
			.setParameter(3, proyect_loan_id)
			.setParameter(4, screen_info_id)
			.getResultList();
		
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public List<InfoNotification> getInfoNotificationByProspectus( Integer company_id, Integer prospectus_id, int screen_info_id ){
		
		try{
			
			String query = "from InfoNotification where company_id = ? and prospectus_id = ? and info_screen_id = ?";
			
			return em.createQuery(query , InfoNotification.class)
				.setParameter(1, company_id)
				.setParameter(2, prospectus_id)
				.setParameter(3, screen_info_id)
				.getResultList();
			
			}catch(Exception e){
				
				e.printStackTrace();
				return null;
				
			}
		
	}
	
	@Transactional
	public boolean saveInfoNotificationBy( InfoNotification info  ){
		
		try{
			
			em.persist(info);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	@Transactional
	public boolean updateInfoNotificationBy( InfoNotification info  ){
		
		try{
			
			em.merge(info);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
}
