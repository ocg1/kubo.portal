package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.UtmPartnerConversion;
import mx.com.kubo.repositories.RegistrationReasonDao;
import mx.com.kubo.services.RegistrationReasonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationReasonServiceImp 
implements RegistrationReasonService
{
	@Autowired
	private RegistrationReasonDao dao;
	
	public List<RegistrationReason> loadRegistrationReasonList()
	{
		return dao.loadRegistrationReasonList();
	}

	public List<RegistrationReason> loadRegistrationReasonWithoutOtherList()
	{
		return dao.loadRegistrationReasonWithoutOtherList();
	}
	
	public boolean save(RegistrationReason registration_reason)
	{
		int registration_reason_id = dao.getMaxRegistration_reason_id();
		
		registration_reason.getRegPK().setRegistration_reason_id(registration_reason_id);
		
		return dao.save(registration_reason);
	}
	
	public UtmPartnerConversion existUTM( String utm ){
		
		return dao.existUTM( utm );
		
	}
	
}
