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
		
		     investment_frequency = service_automatic_investment.getInvestmentFrequencyLst();
		automatic_investment_list = service_automatic_investment.getAutomaticInvestmentListByProspect(prospectus_id);
		
		init_access(AUTOMATIC_INVESTMENT);
		
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
	
	public void init_select_one(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		String id = event.getComponent().getId();
		
		if(id.equals("investment-frequency"))
		{
			select = (HtmlSelectOneMenu) event.getComponent();
			
			frequency = select.getValue().toString();
			
			request.addCallbackParam("frequency", frequency);	
		}				
	}
	
	public void init_type_search(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input = (HtmlInputText) event.getComponent();
		
		typeSearch = Integer.parseInt(input.getValue().toString());
		
		request.addCallbackParam("typeSearch", typeSearch);
	}
	
	public void init_previous_type(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input = (HtmlInputText) event.getComponent();
		
		previousType = input.getValue().toString();
		
		request.addCallbackParam("previousType", previousType);
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
