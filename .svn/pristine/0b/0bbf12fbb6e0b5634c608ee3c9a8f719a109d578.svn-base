package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.LegalStatusPK;
import mx.com.kubo.model.Legal_Status;
import mx.com.kubo.repositories.LegalStatusDao;
import mx.com.kubo.services.LegalStatusService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LegalStatusServiceImp 
implements LegalStatusService
{	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private LegalStatusDao dao;
		
	public Legal_Status getLegalStatusById(LegalStatusPK pk) 
	{
		return dao.loadSelectedLegalStatus(pk);
	}
	
	public void add(Legal_Status newLegalStatus) 
	{
		dao.saveLegalStatus(newLegalStatus);		
	}
	
	public List<Legal_Status> getLegalStatusList() 
	{
		return dao.loadLegalStatusList();
	}
}
