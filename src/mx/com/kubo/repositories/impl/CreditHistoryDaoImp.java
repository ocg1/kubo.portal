package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryPK;
import mx.com.kubo.repositories.CreditHistoryDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CreditHistoryDaoImp implements CreditHistoryDao
{

	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
	public CreditHistory loadSelectedCreditHistory(CreditHistoryPK pk) 
	{
		log.info("FindCreditHistory.CreditHistoryDaoImp loadSelectedNaturalPerson prospectus: "+pk.getProspectus_id());
		
		return em.find(CreditHistory.class,pk);
	}

	@Transactional
	public void saveCreditHistory(CreditHistory newCreditHistory) 
	{
		log.info("saveCreditHistory.CreditHistoryDaoImp");
		
		em.merge(newCreditHistory);
	}
	
	@Transactional
	public void updateCreditHistory(CreditHistory newCreditHistory) 
	{
		log.info("updateCreditHistory.CreditHistoryDaoImp");
		
		em.merge(newCreditHistory);
	}
		
	@Transactional
	public CreditHistory merge(CreditHistory credit_history) 
	{			
		try
		{						
			credit_history = em.merge(credit_history);
						
		} catch(Exception e) {
			
			credit_history = null;
		}
		
		return credit_history;
	}	
}
