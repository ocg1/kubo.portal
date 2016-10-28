package mx.com.kubo.portal.telefonos;

import mx.com.kubo.registro.ChangeControlIMO;
import mx.com.kubo.services.PhoneService;

public interface ChangePhoneIMO extends ChangeControlIMO
{
	void setService_phone(PhoneService service);
	
	void setPhone_type_id(Integer phone_type_id);
}
