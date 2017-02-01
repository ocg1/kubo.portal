package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.MailLog;
import mx.com.kubo.model.SystemNotificationLog;
import mx.com.kubo.repositories.MailLogDAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MailLogDAOImp 
implements MailLogDAO
{	
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
    @Transactional
	public boolean saveMailLog(MailLog mail) 
    {
    	try
    	{
    		em.persist(mail);
    		
    		return true;
    		
    	} catch(Exception e) {
    		
    		e.printStackTrace();
    		
    		return false;
    	}
	}
    
    public MailLog getMaxMailLog(Integer event_id)
    {		
    	Integer id = 0;
    	
		String query = "select MAX(m.mail_log_id) from MailLog m WHERE m.event_id = ?";		
		
		id = (Integer) em.createQuery(query).setParameter(1, event_id).getSingleResult();
		
		if(id == null)
		{
			return null;
			
		} else {
			
			return em.find(MailLog.class, id);
		}
    }
    
    public final List<SystemNotificationLog> getSystem_notification_log(int prospectus_id)
    {
    	List<SystemNotificationLog> bitacora;
    	TypedQuery<SystemNotificationLog> typed;
    	
    	typed = em.createQuery("from SystemNotificationLog where prospectus_id = ?", SystemNotificationLog.class);
    	typed.setParameter(1, prospectus_id);
    	
    	bitacora = typed.getResultList();
    	
    	return bitacora;
    }

}
