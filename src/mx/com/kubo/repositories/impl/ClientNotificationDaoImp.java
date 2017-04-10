package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.ClientNotification;
import mx.com.kubo.model.CoachPublicationsCollector;
import mx.com.kubo.model.NotificaCreditosDesembolsadosTresDiasCollector;
import mx.com.kubo.model.PublicadosSinAutorizar;
import mx.com.kubo.repositories.ClientNotificationDao;

@Repository
public class ClientNotificationDaoImp implements Serializable,ClientNotificationDao  {

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
	
    @Transactional
	public boolean updateClientNotification( ClientNotification cn ){
		
		try{
			
			em.merge(cn);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<ClientNotification> getClientNotificationWithOutCoach(){
		
		try{
			
			String query = "from ClientNotification where prospectus_id_coach is null and notification_type_id = 1  and assign_coach_status = 0"; 
			
			return em.createQuery(query, ClientNotification.class).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean callSPClientNotification(){
		
		try{
			
			em.createNamedQuery("collectorClientNotification", CoachPublicationsCollector.class)
					.setParameter("par_Inserta", "S")
					.setParameter("par_TipoOperacion", "A")
					.setParameter("par_TipoNotifica", "S")
					.getResultList();
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ClientNotification getClientNotification( Integer prospectus_id , Integer notification_type_id ){
		
		try{
			
			String query = "from ClientNotification where prospectus_id = ? and notification_type_id = ? "; 
			
			return em.createQuery(query, ClientNotification.class)
							.setParameter(1, prospectus_id)
							.setParameter(2, notification_type_id) 
							.getSingleResult();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
public List<PublicadosSinAutorizar> getPublicadosSinAutorizar( String send_type  ){
		
		try{
			
			return em.createNamedQuery("collectorPublicadosSinAutorizar", PublicadosSinAutorizar.class)
					.setParameter("Par_Inserta", "I")
					.setParameter("Par_TipoNotifica", send_type)
					.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<NotificaCreditosDesembolsadosTresDiasCollector> getNotificaCreditosDesembolsadosTresDias( String send_type  ){
		
		try{
			
			return em.createNamedQuery("collectorNotificaCreditosDesembolsadosTresDia", NotificaCreditosDesembolsadosTresDiasCollector.class)
					.setParameter("Par_Inserta", "I")
					.setParameter("Par_TipoNotifica", send_type)
					.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
