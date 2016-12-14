package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ContactWay;
import mx.com.kubo.model.ContactWayProspectus;
import mx.com.kubo.repositories.ContactWayProspectusDao;
import mx.com.kubo.services.ContactWayProspectusService;

@Service
public class ContactWayProspectusServiceImp implements ContactWayProspectusService, Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	ContactWayProspectusDao dao;

	public List<ContactWayProspectus> getContactWayProspectusList( int company_id, int propsectus_id ){
		return dao.getContactWayProspectusList(company_id, propsectus_id);
	}
	public List<ContactWay> getContactWayCatEnabled(){
		return dao.getAllContactWayCat();
	}
	public List<ContactWay> getAllContactWayCat(){
		return dao.getAllContactWayCat();
	}
	
	public boolean saveContactWayProspectus( ContactWayProspectus contact ){
		return dao.saveContactWayProspectus(contact);
	}
	public boolean removeContactWayProspectus( ContactWayProspectus contact ){
		
		return dao.removeContactWayProspectus(contact);
		
	}
	
}
