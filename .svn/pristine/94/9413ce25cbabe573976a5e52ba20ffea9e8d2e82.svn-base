package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.UtmPartnerConversion;

public interface RegistrationReasonDao 
{
	int getMaxRegistration_reason_id();
	
	boolean save(RegistrationReason registration_reason);
	
	List<RegistrationReason> loadRegistrationReasonList();
	List<RegistrationReason> loadRegistrationReasonWithoutOtherList();
	
	UtmPartnerConversion existUTM( String utm );
	
}
