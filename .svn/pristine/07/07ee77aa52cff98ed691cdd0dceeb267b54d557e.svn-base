package mx.com.kubo.services.impl;

import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryPK;
import mx.com.kubo.repositories.CreditHistoryDao;
import mx.com.kubo.services.CreditHistoryService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditHistoryServiceImp implements CreditHistoryService 
{	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private CreditHistoryDao dao;
	
	public CreditHistory getCreditHistoryById(CreditHistoryPK pk) 
	{
		return dao.loadSelectedCreditHistory(pk);
	}

	public void add(CreditHistory newCreditHistory) 
	{
		log.info("add.CompanyServiceImp");
		
		dao.saveCreditHistory(newCreditHistory);		
	}
		
	public void update(CreditHistory newCreditHistory) 
	{
		log.info("update.CreditHistoryServiceImp");
		
		dao.updateCreditHistory(newCreditHistory);		
	}
		
	public CreditHistory merge(CreditHistory credit_history) 
	{			
		return dao.merge(credit_history);		
	}

}
