package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.Membership;
import mx.com.kubo.repositories.LoginDaoIMO;

import org.apache.log4j.Logger;

public abstract class LoginDaoDMO 
implements LoginDaoIMO
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;
	
	protected final Integer MAX_FAILED_LOGIN = 5;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	public final Membership getMembershipByEmail(String email) 
	{
		//List<Membership> list_membership;
		
		// TypedQuery<Membership> typed;
		
		Membership membership = null;
		
		List<Membership> list_membership = em.createQuery("from Membership where email = ?", Membership.class)
												.setParameter(1, email)
			 									.getResultList();
		
		if(list_membership.size() > 0)
		{
			membership = list_membership.get(0);	
		}

		return membership;
	}
	
	public Membership getMembershipByfb_id( String fb_id){
		
		Membership membership = null;
		
		List<Membership> list_membership = em.createQuery("from Membership where fb_id = ?", Membership.class)
												.setParameter(1, fb_id)
			 									.getResultList();
		
		if(list_membership.size() > 0)
		{
			membership = list_membership.get(0);	
		}

		return membership;
		
	}
}
