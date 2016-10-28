package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.EventTokenAccess;
import mx.com.kubo.model.EventTokenAccessPK;
import mx.com.kubo.repositories.EventTokenAccessDao;

@Repository
public class EventTokenAccessDaoImp implements Serializable, EventTokenAccessDao {

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
	
	@Column
	public List<EventTokenAccess> getEventTokenAccessListByArea( String area ){
		
		String query = "from EventTokenAccess where area = ? ";
		
		try{
		
			return em.createQuery( query , EventTokenAccess.class  ).setParameter(1, area).getResultList();
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return  null;
			
		}
		
	}
	
	@Column
	public EventTokenAccess getEventTokenAccessByPK( EventTokenAccessPK pk ){
		
		String query = "from EventTokenAccess where pk.event_token_id = ? and pk.company_id = ? ";
		
		try{
		
			return em.createQuery( query , EventTokenAccess.class  )
									.setParameter(1, pk.getEvent_token_id())
									.setParameter(2, pk.getCompany_id())
									.getSingleResult();
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return  null;
			
		}
		
	}
	
}
