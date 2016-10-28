package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.EventNotification;
import mx.com.kubo.repositories.EventNotificationDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class EventNotificationDaoImp implements EventNotificationDao, Serializable 
{
	
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
	
    @Override
	public List<EventNotification> loadListProspectusByEvent(int event){
		List<EventNotification> lstProspect = em.createQuery(
			    "from EventNotification where event_id = ? ", EventNotification.class)
			    .setParameter(1, event)
			    .getResultList();
		 return lstProspect;
	}
}
