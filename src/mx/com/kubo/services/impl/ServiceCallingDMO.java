package mx.com.kubo.services.impl;

import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.repositories.ServiceCallingDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServiceCallingDMO 
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected ServiceCalling srvCall;
	
	@Autowired
	protected ServiceCallingDao dao;
	
	public ServiceCalling loadSelectedServiceCall(int servicecalling_id)
	{
		return dao.loadSelectedServiceCall(servicecalling_id);
	}
		
	public ServiceCalling loadServiceCallByProspectus(Integer prospectus_id,Integer company_id)
	{
		return dao.loadServiceCallByProspectus(prospectus_id, company_id);
	}
	
	public boolean updateServiceCall(ServiceCalling newServiceCall)
	{
		return dao.updateServiceCall( newServiceCall ); 
	}
}
