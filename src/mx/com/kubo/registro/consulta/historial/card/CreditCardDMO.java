package mx.com.kubo.registro.consulta.historial.card;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.Bank;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.registro.ChangeControlDMO;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.CreditHistoryService;

public abstract class CreditCardDMO extends ChangeControlDMO
implements CreditCardIMO
{
	protected RequestContext request;
	
	protected CreditHistoryService  service_credit_history;	
	protected BankService service_bank;
		
	protected CreditHistory credit_history;
	protected Bank bank;
	
	protected String company_ENABLED = "none";
	protected String creditcard_four_digits;
	protected String creditcard_company;
	protected String company_name_ORIGINAL;
		
	protected Integer have_credit;	
	protected Integer creditcard_is_principal;

	protected boolean update_OK;
	
	public void setService_credit_history(CreditHistoryService service) 
	{
		service_credit_history = service;
	}
	
	public void setService_bank(BankService service) 
	{
		service_bank = service;
	}
	
	public void setCredit_history(CreditHistory credithistory) 
	{
		credit_history = credithistory;
		
		creditcard_is_principal = credit_history.getCreditcard_is_principal() == null ? 0 : credit_history.getCreditcard_is_principal();
		
		company_name_ORIGINAL = credit_history.getCreditcard_company() != null ? credit_history.getCreditcard_company() : "";
		
		if(creditcard_is_principal != null)
		{
			if(creditcard_is_principal == 0)
			{
				company_ENABLED = "none";
				
			} else {
				
				company_ENABLED = "block";
			}
		}
	}
	
	public String getCompany_ENABLED()
	{
		return company_ENABLED;
	}
}
