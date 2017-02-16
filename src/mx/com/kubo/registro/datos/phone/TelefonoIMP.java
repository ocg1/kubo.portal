package mx.com.kubo.registro.datos.phone;

import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

public class TelefonoIMP extends TelefonoAMO
implements TelefonoIMO
{
	public void init() 
	{
		thisPhoneFixed = phoneService.getPhoneByTypeByArea(prospectus_id, company_id, 5, area.charAt(0));
		thisPhoneCell  = phoneService.getPhoneByTypeByArea(prospectus_id, company_id, 6, area.charAt(0));
		
		init_phone_fixed();
		init_phone_cell();
	}
	
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		String id = event.getComponent().getId();
		
		if(id.equals("focus-phone-cell"))
		{		
			phone_type_id = PARTICULAR_CELULAR;
			
		} else {
			
			phone_type_id = PARTICULAR_FIJO;
		}
		
		focus_date = new Date();
		
		request.addCallbackParam("phone_type_id", phone_type_id);
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public void update(AjaxBehaviorEvent event) 
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		new_value = input_text.getValue().toString();
		
		switch(phone_type_id)
		{
			case PARTICULAR_CELULAR:
				phoneCellProspectus = new_value;
				
				original_value = thisPhoneCell.getPhone_number();
				
				updatePhoneCellProspectus();
			break;
			
			case PARTICULAR_FIJO:
				phoneFixedPropectus = new_value;
				
				original_value = thisPhoneFixed.getPhone_number();
				
				updatePhoneProspectus();
			break;
		}				
		
		init_change_control("phone_number", original_value, new_value);		
		
		request.addCallbackParam("phone_number", new_value);
		request.addCallbackParam("phone_type_id", phone_type_id);
		request.addCallbackParam("change_control_OK", change_control_OK);
	}
}
