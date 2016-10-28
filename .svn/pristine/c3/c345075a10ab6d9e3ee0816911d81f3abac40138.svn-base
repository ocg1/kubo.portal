package mx.com.kubo.portal.telefonos;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.registro.ChangeControlDMO;
import mx.com.kubo.services.PhoneService;

public abstract class ChangePhoneDMO extends ChangeControlDMO
implements ChangePhoneIMO
{
	protected PhoneService service_phone;
	
	protected String phone_number;
	protected String phone_extension;
	
	protected Integer phone_type_id;
	
	protected boolean update_OK;
	
	public final void init_focus_date(AjaxBehaviorEvent e){}
	
	public final void setService_phone(PhoneService service) 
	{
		service_phone = service;
	}

	public final void setPhone_type_id(Integer phone_type_id) 
	{
		this.phone_type_id = phone_type_id;
	}
}
