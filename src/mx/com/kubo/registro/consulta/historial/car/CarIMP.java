package mx.com.kubo.registro.consulta.historial.car;

import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public final class CarIMP extends CarAMO 
implements CarIMO
{
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init_is_principal(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		car_is_principal = (Integer) select_radio.getValue();
		
		change_control_OK = false;
		
		original_value = credit_history.getCar_is_principal() != null ? credit_history.getCar_is_principal() + "" : "";
		
		new_value = car_is_principal + "";
		
		init_change_control("gn_credit_history", "car_is_principal", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{			
			credit_history.setCar_is_principal(car_is_principal);
			
			if(car_is_principal != null)
			{
				if(car_is_principal == 0)
				{				
					credit_history.setCar_company(null);
					credit_history.setCar_number(null);
					
					company_ENABLED = "none";
					
				} else {
					
					company_ENABLED = "block";
				}
			}
			
			credit_history = service_credit_history.merge(credit_history);
		
			update_OK = true;
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("car_is_principal", car_is_principal);	
	}
	
	public final void init_company(SelectEvent event) 
	{
		request = RequestContext.getCurrentInstance();
		
		car_company = event.getObject().toString();
		
/*		
		bank = service_bank.getBankByShortName(car_company);
		
		if(bank != null)
		{
			car_company = bank.getShort_name();			
			
		} else {
			
			car_company = null;						
		}
*/		
		
		change_control_OK = false;
		
		original_value = company_name_ORIGINAL;
		
		new_value = car_company != null ? car_company : "";
		
		init_change_control("gn_credit_history", "car_company", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{					
			credit_history.setCar_company(car_company);
			
			credit_history = service_credit_history.merge(credit_history);
			
			company_name_ORIGINAL = credit_history.getCar_company() != null ? credit_history.getCar_company() : "";
			
			update_OK = true;
		}
		
		boolean replace_ENABLED = car_company.indexOf("&") > 0;
		
		if(replace_ENABLED)
		{
			car_company = car_company.replaceAll("&", " AND ");
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("car_company", car_company);
	}
	
	public final void init_company_name(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		car_company = input_text.getValue().toString();
		
		change_control_OK = false;
		
		original_value = company_name_ORIGINAL;
		
		new_value = car_company != null ? car_company : "";
		
		init_change_control("gn_credit_history", "car_company", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{					
			credit_history.setCar_company(car_company);
			
			credit_history = service_credit_history.merge(credit_history);
			
			company_name_ORIGINAL = credit_history.getCar_company() != null ? credit_history.getCar_company() : "";
			
			update_OK = true;
		}
		
		boolean replace_ENABLED = car_company.indexOf("&") > 0;
		
		if(replace_ENABLED)
		{
			car_company = car_company.replaceAll("&", " AND ");
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("car_company", car_company);
	}
}
