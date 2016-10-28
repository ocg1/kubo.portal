package mx.com.kubo.registro.consulta.historial.card;

import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.CreditHistoryService;

public interface CreditCardIMO 
{
	 void setService_credit_history(CreditHistoryService service);
	 void setService_change_control(Change_controlService service);
	 void setService_bank(BankService service);
	 
	 void setCredit_history(CreditHistory credithistory);
	 
	 void init_is_principal(AjaxBehaviorEvent evento);	 
	 void init_four_digits (AjaxBehaviorEvent evento);
	 
	 void init_company(SelectEvent event);
	 
	 void setSesion(SessionBean sesion);
	 
	 String getCompany_ENABLED();
}