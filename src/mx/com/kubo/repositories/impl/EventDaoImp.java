package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Event;
import mx.com.kubo.model.EventPK;
import mx.com.kubo.repositories.EventDao;

import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImp implements EventDao{
	private EntityManager em = null;
	
	@Override
	public Event getEventByID(EventPK eventPK) {
		try{
			return em.find(Event.class, eventPK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em){
		this.em = em;
	}

}
