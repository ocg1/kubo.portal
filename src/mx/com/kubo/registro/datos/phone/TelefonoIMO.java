package mx.com.kubo.registro.datos.phone;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.model.Phone;
import mx.com.kubo.registro.ChangeControlIMO;

public interface TelefonoIMO extends ChangeControlIMO
{		
	
	void setInfusion_id(Integer infusion_id);
	
	void init();
	
	void update(AjaxBehaviorEvent event);
	
	public Phone getThisPhoneFixed();
	
	public Phone getThisPhoneCell();
	
}