package mx.com.kubo.services.impl;

import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.services.PhoneService;

import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImp extends ServicePhoneDMO
implements PhoneService 
{
	public boolean addPhone(Phone newPhone,int prospectusID,int companyID) 
	{
		return dao.addPhone(newPhone,prospectusID,companyID);
	}
	
	public boolean updatePhone(Phone phone) 
	{
		return dao.updatePhone(phone);
	}

	public boolean removePhone(PhonePK pkPhone) 
	{
		return dao.removePhone(pkPhone);
	}
		
	public String existPhone(Phone newPhone, int prospectusID,int companyID )
	{
		return dao.existPhone( newPhone,  prospectusID, companyID );
	}	
}
