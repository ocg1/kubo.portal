package mx.com.kubo.registro.consulta.historial;

import mx.com.kubo.registro.ChangeControlDMO;
import mx.com.kubo.services.CreditHistoryService;

public abstract class HistorialDMO extends ChangeControlDMO 
implements HistorialIMO
{
	protected CreditHistoryService  service_credit_history;
	
	public void setService_credit_history(CreditHistoryService service) 
	{
		service_credit_history = service;
	}		
}
