package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.UtmPartnerConversion;

public interface RegistrationReasonService 
{
	boolean save(RegistrationReason registration_reason);
	
	List<RegistrationReason> loadRegistrationReasonList();	
	List<RegistrationReason> loadRegistrationReasonWithoutOtherList();	
	UtmPartnerConversion existUTM( String utm );
}
