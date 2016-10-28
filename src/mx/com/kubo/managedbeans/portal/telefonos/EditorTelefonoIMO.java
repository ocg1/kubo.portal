package mx.com.kubo.managedbeans.portal.telefonos;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.AddPhoneBean;
import mx.com.kubo.bean.PhoneReviewEditor;
import mx.com.kubo.model.PhoneType;
import mx.com.kubo.portal.telefonos.ChangePhoneIMO;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.PhoneService;

public interface EditorTelefonoIMO 
{	
	void setService_change_control(Change_controlService service);
	void setService_phone  (PhoneService service);		
	
	void init_add_phone();
	void init_phone_view();
	void init_phone_type(AjaxBehaviorEvent event);
	
	ChangePhoneIMO getChanger();
	
	AddPhoneBean getNewAddPhone();
	
	List <PhoneType> getListPhoneType();
	List<PhoneReviewEditor> getLista_phone_view();
}
