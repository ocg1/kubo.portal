package mx.com.kubo.investor.settings;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.rest.tienda.SAFIInvestmentIMP;
import mx.com.kubo.rest.tienda.accounts.CuentasClienteIMP;

public class AutomaticInvestmentIMP extends AutomaticInvestmentAMO 
implements AutomaticInvestmentIMO
{
	public void init()
	{
		purposelst = purposeservice.getPurposeList();
		
		automatic_investment_list = service_automatic_investment.getAutomaticInvestmentListByProspect(prospectus_id);
		
		cuenta = new CuentasClienteIMP();
		cuenta.setSesion(sesion);
		cuenta.init();
		
		saldoTotal      = cuenta.getSaldoTotal();
		listInvAccounts = cuenta.getListInvAccounts();
		
		account = listInvAccounts.get(0).getAccount();
		
		inversion = new SAFIInvestmentIMP();
		inversion.setSaldoTotal(saldoTotal);
		inversion.setListInvAccounts(listInvAccounts);
		inversion.init();
				
		inicializaSaldos();
		
    	init_filter_default();
    	
    	inversion.generaScript(ultimoFiltro, flagRisk);
    	
    	scriptStatus = inversion.getScriptStatus();
	}
	
	public void init_label(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input = (HtmlInputText) event.getComponent();
		
		label = input.getValue().toString();
		
		request.addCallbackParam("label", label);
	}
	
	public void init_frequency(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select = (HtmlSelectOneMenu) event.getComponent();
		
		frequency = select.getValue().toString();
		
		if(frequency.equals("D"))
		{
			frequency_label = DIARIA;
		}
		
		else if(frequency.equals("S"))
		{
			frequency_label = SEMANAL;
		}
		
		request.addCallbackParam("frequency", frequency);
		request.addCallbackParam("frequency_label", frequency_label);
	}
	
	public void save_automatic_investment()
	{
		request = RequestContext.getCurrentInstance();
		
		external = FacesContext.getCurrentInstance().getExternalContext();
		
    	map = external.getRequestParameterMap();
    	
    	init_filter();    	     	    	
    	init_automatic_investment();    	    	
    	
    	if(save_OK)
    	{
    		automatic_investment_list = service_automatic_investment.getAutomaticInvestmentListByProspect(prospectus_id);
    	}
    	
    	inversion.generaScript(strQuery, flagRisk);
    	
    	scriptStatus = inversion.getScriptStatus();
    	
    	init_user_filter();
    	    
		request.addCallbackParam("save_OK", save_OK);
	}
}
