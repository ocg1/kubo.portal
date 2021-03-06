package mx.com.kubo.registro.consulta.historial.card;

import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

public final class CreditCardIMP extends CreditCardAMO
implements CreditCardIMO
{	
	public final void init_focus_date(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		focus_date = new Date();
		
		request.addCallbackParam("focus_date", date_format.format(focus_date));
	}
	
	public final void init_have_credit(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		have_credit = null;
		
		select_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		have_credit = (Integer) select_radio.getValue();
		
		credit_history.setHave_credit(have_credit);
		
		service_credit_history.update(credit_history);
		
		request.addCallbackParam("have_credit", have_credit);				
	}
	
	public final void init_is_principal(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		creditcard_is_principal = null;
		
		select_radio = (HtmlSelectOneRadio) evento.getComponent();
		
		creditcard_is_principal = (Integer) select_radio.getValue();
		
		change_control_OK = false;
		
		original_value = credit_history.getCreditcard_is_principal() != null ? credit_history.getCreditcard_is_principal() + "" : "";
		
		new_value = creditcard_is_principal + "";
		
		init_change_control("gn_credit_history", "creditcard_is_principal", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{	
			credit_history.setCreditcard_is_principal(creditcard_is_principal);
			
			if(creditcard_is_principal != null)
			{
				if(creditcard_is_principal == 0)
				{
					company_ENABLED = "none";
					
					credit_history.setCreditcard_company(null);
					credit_history.setCreditcard_limit(null);
					credit_history.setCreditcard_four_digits(null);
						
				} else {
					
					company_ENABLED = "block";
				}
			}
			
			credit_history = service_credit_history.merge(credit_history);
			
			update_OK = true;
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("creditcard_is_principal", creditcard_is_principal);		
	}

	public final void init_company(SelectEvent event) 
	{		
		request = RequestContext.getCurrentInstance();
		
		creditcard_company = event.getObject().toString();
		
/*		
		bank = service_bank.getBankByShortName(creditcard_company);
		
		if(bank != null)
		{
			creditcard_company = bank.getShort_name();
						
		} else {
			
			creditcard_company = null;
		}
*/		
		
		change_control_OK = false;
		
		original_value = company_name_ORIGINAL;
		
		new_value = creditcard_company != null ? creditcard_company : "";
		
		init_change_control("gn_credit_history", "creditcard_company", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{						
			credit_history.setCreditcard_company(creditcard_company);
			
			credit_history = service_credit_history.merge(credit_history);	
			
			company_name_ORIGINAL = credit_history.getCreditcard_company() != null ? credit_history.getCreditcard_company() : "";
			
			update_OK = true;
		}
		
		boolean replace_ENABLED = creditcard_company.indexOf("&") > 0;
		
		if(replace_ENABLED)
		{
			creditcard_company = creditcard_company.replaceAll("&", " AND ");
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("creditcard_company", creditcard_company);
	}
	
	public final void init_company_name(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		creditcard_company = input_text.getValue().toString();
		
		change_control_OK = false;
		
		original_value = company_name_ORIGINAL;
		
		new_value = creditcard_company != null ? creditcard_company : "";
		
		init_change_control("gn_credit_history", "creditcard_company", original_value, new_value);
		
		update_OK = false;
		
		if(change_control_OK)
		{						
			credit_history.setCreditcard_company(creditcard_company);
			
			credit_history = service_credit_history.merge(credit_history);	
			
			company_name_ORIGINAL = credit_history.getCreditcard_company() != null ? credit_history.getCreditcard_company() : "";
			
			update_OK = true;
		}
						
		boolean replace_ENABLED = creditcard_company.indexOf("&") > 0;
		
		if(replace_ENABLED)
		{
			creditcard_company = creditcard_company.replaceAll("&", " AND ");
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("creditcard_company", creditcard_company);
	}
	
	public final void init_four_digits(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) evento.getComponent();
		
		creditcard_four_digits = input_text.getValue().toString();
		
		change_control_OK = false;
		
		original_value = credit_history.getCreditcard_four_digits() != null ? credit_history.getCreditcard_four_digits() + "" : "";
		
		new_value = creditcard_four_digits + "";
		
		init_change_control("gn_credit_history", "creditcard_four_digits", original_value, new_value);
				
		update_OK = false;
		
		if(change_control_OK)
		{	
			credit_history.setCreditcard_four_digits(creditcard_four_digits);
			
			credit_history = service_credit_history.merge(credit_history);
			
			update_OK = true;
		}
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("creditcard_four_digits", creditcard_four_digits);		
	}
}
