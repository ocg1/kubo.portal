package mx.com.kubo.rest.saldos;

import javax.ws.rs.core.Response;

import mx.com.kubo.services.ProyectLoanService;

public interface ProyectLoanActiveIMO 
{
	void setService_proyect_loan(ProyectLoanService service);
	
	void init(String cuenta, String status, char status_delinquentinv);
	
	Response getResponseJSON();
}
