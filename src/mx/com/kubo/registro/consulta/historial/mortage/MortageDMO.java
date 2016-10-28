package mx.com.kubo.registro.consulta.historial.mortage;

import mx.com.kubo.model.Bank;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.registro.ChangeControlDMO;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.CreditHistoryService;

public abstract class MortageDMO extends ChangeControlDMO
implements MortageIMO
{	
	protected CreditHistoryService service_credit_history;
	protected BankService service_bank;		
	
	protected Bank bank;
	
	protected CreditHistory credit_history;
	
	protected String company_ENABLED = "none";
	protected String creditcard_four_digits;
	
	protected String mortgage_company;
	protected String company_name_ORIGINAL;

	protected Integer is_principal;
	
	protected boolean update_OK;
	protected boolean change_control_ENABLED;
	protected boolean change_control_OK;
	
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
		
		is_principal = credithistory.getMortgage_is_principal() == null ? 0 : credithistory.getMortgage_is_principal();
		
		company_name_ORIGINAL = credit_history.getMortgage_company() != null ? credit_history.getMortgage_company() : "";
		
		if(is_principal != null)
		{
			if(is_principal  == 0)
			{
				company_ENABLED = "none";
					
			} else {
				
				company_ENABLED = "block";
			}
		}		
	}
	
	public final String getCompany_ENABLED()
	{
		return company_ENABLED;
	}
}
