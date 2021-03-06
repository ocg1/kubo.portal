package mx.com.kubo.registro.consulta.historial.mortage;

import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public final class MortageIMP extends MortageAMO
implements MortageIMO
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
		
		is_principal = (Integer) select_radio.getValue();
		
		change_control_OK = false;
		
		original_value = company_name_ORIGINAL;
		
		new_value = is_principal + "";
		
		init_change_control("gn_credit_history", "mortgage_is_principal", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{	
			credit_history.setMortgage_is_principal(is_principal);
			
			if(is_principal != null)
			{
				if(is_principal == 0)
				{
					company_ENABLED = "none";
					
					credit_history.setMortgage_company(null);
					credit_history.setMortgage_number(null);
					
				} else {
					
					company_ENABLED = "block";
				}
			}
			
			credit_history = service_credit_history.merge(credit_history);
			
			update_OK = true;
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("mortgage_is_principal", is_principal);	
	}
	
	public void init_company(SelectEvent event) 
	{
		request = RequestContext.getCurrentInstance();
		
		mortgage_company = event.getObject().toString();
		
/*		
		bank = service_bank.getBankByShortName(mortgage_company);
		
		if(bank != null)
		{
			mortgage_company = bank.getShort_name();			
			
		} else {
			
			mortgage_company = null;
		}
*/		
		
		change_control_OK = false;
						
		original_value = company_name_ORIGINAL;				
		
		new_value = mortgage_company != null ? mortgage_company : "";
		
		init_change_control("gn_credit_history", "mortgage_company", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{			
			credit_history.setMortgage_company(mortgage_company);
			
			credit_history = service_credit_history.merge(credit_history);
			
			company_name_ORIGINAL = credit_history.getMortgage_company() != null ? credit_history.getMortgage_company() : "";
			
			update_OK = true;
		}
		
		boolean replace_ENABLED = mortgage_company.indexOf("&") > 0;
		
		if(replace_ENABLED)
		{
			mortgage_company = mortgage_company.replaceAll("&", " AND ");
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("mortgage_company", mortgage_company);
	}
	
	public final void init_company_name(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		mortgage_company = input_text.getValue().toString();
		
		change_control_OK = false;
		
		original_value = company_name_ORIGINAL;				
		
		new_value = mortgage_company != null ? mortgage_company : "";
		
		init_change_control("gn_credit_history", "mortgage_company", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{			
			credit_history.setMortgage_company(mortgage_company);
			
			credit_history = service_credit_history.merge(credit_history);
			
			company_name_ORIGINAL = credit_history.getMortgage_company() != null ? credit_history.getMortgage_company() : "";
			
			update_OK = true;
		}
		
		boolean replace_ENABLED = mortgage_company.indexOf("&") > 0;
		
		if(replace_ENABLED)
		{
			mortgage_company = mortgage_company.replaceAll("&", " AND ");
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("mortgage_company", mortgage_company);
	}
}
