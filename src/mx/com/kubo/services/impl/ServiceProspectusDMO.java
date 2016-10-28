package mx.com.kubo.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.repositories.ProspectusDao;
import mx.com.kubo.services.ProspectusService;

public abstract class ServiceProspectusDMO 
implements ProspectusService 
{
	protected Logger log = Logger.getLogger(getClass());
	
	@Autowired
	protected ProspectusDao dao;

	public Prospectus getProspectusById(ProspectusPK pk) 
	{
		return dao.loadSelectedProspectus(pk);
	}
	
	public List<Prospectus> getProspectusList()
	{
		return dao.loadProspectsList();
	}
		
	public int getMaxProspectus()
	{		
		return dao.getMaxProspectus();		
	}
	
	public Prospectus getProspectByAreaByPersonType(int prospectID,int companyID, char area, char personType) 
	{		
		return dao.getProspectByAreaByPersonType(prospectID, companyID, area, personType);
	}
	
	public Prospectus getProspectByTrackingID(String tracking_id) 
	{		
		return dao.getProspectByTrackingID(tracking_id);
	}
}
