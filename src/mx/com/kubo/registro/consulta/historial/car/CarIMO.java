package mx.com.kubo.registro.consulta.historial.car;

import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.registro.consulta.historial.HistorialIMO;
import mx.com.kubo.services.BankService;

public interface CarIMO extends HistorialIMO
{	 	 
	 void setService_bank(BankService service);
	 
	 void setCredit_history(CreditHistory credithistory);
	 
	 void init_is_principal(AjaxBehaviorEvent evento);
	 
	 void init_company(SelectEvent event);
	 
	 String getCompany_ENABLED();
}
