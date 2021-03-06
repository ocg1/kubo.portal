package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.PhoneType;

public interface PhoneDao 
{	
	boolean addPhone(Phone newPhone,int prospectusID,int companyID);
	boolean updatePhone(Phone phone);
	boolean removePhone(PhonePK pkPhone);		
	
	Phone getPhoneByID(PhonePK pk);
	Phone getPhoneByTypeByEmploymentByArea(int prospectusID,int companyID,int phoneType,int employmentID,Character area);
	Phone getPhoneByTypeByBusinessByArea(int prospectusID,int companyID,int phoneType,int businessID,Character area);
	Phone getPhoneByTypeByArea(int prospectusID,int companyID,int phoneType,Character area);
	
	List<PhoneType> loadAllPhoneType();
	List<Phone> loadAllPhones();	
	List<Phone> getPhoneListByNumber( String  phone_str,int prospectus_id , int company_id );
	List<Phone> getPhoneByEmploymentListByArea(int prospectusID,int companyID,int employmentID,Character area);
	List<Phone> getPhoneByBusinessListByArea(int prospectusID,int companyID,int businessID,Character area);
	List<Phone> getPhoneListByType (int prospectusID, int companyID, int phone_type_id);
	List<Phone> getPhoneByProspectusList(int prospectusID, int companyID);	
	
	String existPhone(Phone newPhone, int prospectusID,int companyID );
	
}
