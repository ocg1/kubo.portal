package mx.com.kubo.services.impl;

import mx.com.kubo.repositories.AddressDao;
import mx.com.kubo.services.AddressService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AddressServiceDMO 
implements AddressService
{
	protected Logger log;
	
	@Autowired
	protected AddressDao dao;
		
	protected final int NIVEL_1 = 1;
	protected final int NIVEL_2 = 2; 
	
	protected AddressServiceDMO()
	{
		log = Logger.getLogger(getClass());
	}
}
