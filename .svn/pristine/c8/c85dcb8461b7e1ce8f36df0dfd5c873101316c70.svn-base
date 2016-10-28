package mx.com.kubo.registro.consulta.historial.car;

import mx.com.kubo.model.Bank;

import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.registro.consulta.historial.HistorialDMO;
import mx.com.kubo.services.BankService;

public abstract class CarDMO extends HistorialDMO
implements CarIMO
{		
	protected BankService service_bank;
	
	protected Bank bank;
	
	protected CreditHistory credit_history;
	
	protected String company_ENABLED = "none";
	protected String car_company;
	protected String company_name_ORIGINAL;
	
	protected Integer car_is_principal;
	
	protected boolean update_OK;
	
	public void setService_bank(BankService service) 
	{
		service_bank = service;
	}
	
	public void setCredit_history(CreditHistory credithistory) 
	{
		credit_history = credithistory;
		
		car_is_principal = credithistory.getCar_is_principal() == null ? 0 : credithistory.getCar_is_principal();
		company_name_ORIGINAL = credit_history.getCar_company() != null ? credit_history.getCar_company() : "";
		
		if(car_is_principal != null)
		{
			if(car_is_principal == 0)
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
