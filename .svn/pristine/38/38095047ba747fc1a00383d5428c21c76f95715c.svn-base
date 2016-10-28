package mx.com.kubo.repositories.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import mx.com.kubo.model.Change_control;

public abstract class DAOChangeControlAMO 
{	
	Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
	protected boolean persist_change_control(Change_control change_control, int prospectusID, int companyID) 
	{
		Integer idChange = 0;
		
		idChange = (Integer) em.createNamedQuery("queryAddChangeControl")
							   .setParameter(1, prospectusID)
							   .setParameter(2, companyID)
							   .getSingleResult();
		
		if(idChange == null)
		{			
			idChange = 1;
			
		} else {
			
			idChange++;
		}
		
		change_control.getChange_controlPK().setChange_id(idChange);
		change_control.setChange_date(new Date());
		
		try 
		{
			em.persist(change_control);
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}	
	}
}
