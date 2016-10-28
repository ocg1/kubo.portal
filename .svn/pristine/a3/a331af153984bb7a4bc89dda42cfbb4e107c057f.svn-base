package mx.com.kubo.portal.telefonos;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.PhoneReviewDMO;
import mx.com.kubo.model.Phone;

public final class ChangePhoneIMP extends ChangePhoneAMO
implements ChangePhoneIMO
{
	public void init_phone(PhoneReviewDMO view)
	{
		request = RequestContext.getCurrentInstance();
		
		Phone phone = view.getPhone();
		
		phone_type_id   = phone.getPhone_type_id() != null ? phone.getPhone_type_id() : 0;
		phone_number    = phone.getPhone_number()  != null ? phone.getPhone_number()  : "";		
		phone_extension = phone.getExtension()     != null ? phone.getExtension()     : "";
		
		request.addCallbackParam("phone_id", phone.getPhonePk().getPhone_id());
		request.addCallbackParam("phone_number", phone_number);
		request.addCallbackParam("phone_type_id", phone_type_id);
		request.addCallbackParam("phone_extension", phone_extension);
	}
	
	public void init_phone_number(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		phone_number = input_text.getValue().toString();
		
		request.addCallbackParam("phone_number", phone_number);
	}
	
	public void init_phone_extension(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		phone_extension = input_text.getValue().toString();
		
		request.addCallbackParam("phone_extension", phone_extension);
	}
	
	public void init_why_change_data(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_textarea = (HtmlInputTextarea) event.getComponent();
		
		comments = input_textarea.getValue().toString();
		
		request.addCallbackParam("whyChangeData", comments);
	}
	
	public void init_update(PhoneReviewDMO view)
	{
		request = RequestContext.getCurrentInstance();
		
		Phone phone = view.getPhone();
		
		     new_value = phone_number != null ? phone_number : "";
		original_value = phone.getPhone_number() != null ? phone.getPhone_number() : "";
		
		change_control_ENABLED = !original_value.equals(new_value);
		
		if(change_control_ENABLED)
		{
			phone.setPhone_number(phone_number);
			
			field = "phone_number";
			
			afected_table_type = "gn_phone_type";
			
			field_type_id = phone_type_id;
			
			init_change_control(original_value, new_value, comments);
		}						
		
			  new_value = phone_type_id.toString();
		 original_value = phone.getPhone_type_id() != null ? phone.getPhone_type_id().toString() : "";
	
		change_control_ENABLED = !original_value.equals(new_value);
	
		if(change_control_ENABLED)
		{
			phone.setPhone_type_id(phone_type_id);
			
			field = "phone_type_id";
			
			afected_table_type = null;
			
			field_type_id = null;
			
			init_change_control(original_value, new_value, comments);
		}
		
		update_OK = false;
		
		if(change_control_OK)
		{									
			update_OK = service_phone.updatePhone(phone);
		}
	
		request.addCallbackParam("update_OK", update_OK);
	}
}
