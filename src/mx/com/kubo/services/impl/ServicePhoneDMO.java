package mx.com.kubo.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.PhoneType;
import mx.com.kubo.repositories.PhoneDao;
import mx.com.kubo.services.PhoneService;

public abstract class ServicePhoneDMO 
implements PhoneService 
{
	protected Logger log = Logger.getLogger(getClass());
	
	@Autowired
	protected PhoneDao dao;	
	
	public List<Phone> loadAllPhones()
	{
		return dao.loadAllPhones();
	}
	
	public List<PhoneType> loadAllPhoneType() 
	{
		return dao.loadAllPhoneType();
	}
	
	public Phone getPhoneByID(PhonePK pk) 
	{
		return dao.getPhoneByID(pk);
	}
	
	public List<Phone> getPhoneListByNumber( String  phone_str,int prospectus_id , int company_id )
	{
		return dao.getPhoneListByNumber( phone_str,  prospectus_id, company_id );
	}

	public List<Phone> getPhoneByEmploymentListByArea(int prospectusID,int companyID, int employmentID,Character area) 
	{	
		return dao.getPhoneByEmploymentListByArea(prospectusID, companyID, employmentID,area);
	}
	
	public List<Phone> getPhoneByBusinessListByArea(int prospectusID, int companyID,int businessID,Character area) 
	{
		return dao.getPhoneByBusinessListByArea(prospectusID, companyID, businessID,area);
	}
	
	public List<Phone> getPhoneByProspectusList(int prospectusID, int companyID) 
	{
		return dao.getPhoneByProspectusList(prospectusID, companyID);
	}
	
	public Phone getPhoneByTypeByEmploymentByArea(int prospectusID, int companyID,int phoneType, int employmentID,Character area) 
	{	
		return dao.getPhoneByTypeByEmploymentByArea(prospectusID, companyID, phoneType, employmentID,area);
	}

	public Phone getPhoneByTypeByBusinessByArea(int prospectusID, int companyID,int phoneType, int businessID,Character area) 
	{		
		return dao.getPhoneByTypeByBusinessByArea(prospectusID, companyID, phoneType, businessID,area);
	}
	
	public Phone getPhoneByTypeByArea(int prospectusID, int companyID,int phoneType,Character area) 
	{		
		return dao.getPhoneByTypeByArea(prospectusID, companyID, phoneType,area);
	}
	
	public List<Phone> getPhoneListByType (int prospectusID, int companyID, int phone_type_id)
	{
		return dao.getPhoneListByType (prospectusID, companyID, phone_type_id);
	}
}
