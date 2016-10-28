package mx.com.kubo.registro.consulta.historial.mortage;

import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.CreditHistoryService;

public interface MortageIMO 
{
	 void setService_credit_history(CreditHistoryService service);
	 void setService_change_control(Change_controlService service);
	 void setService_bank(BankService service);
	 
	 void setSesion(SessionBean sesion);
	 
	 void setCredit_history(CreditHistory credithistory);
	 
	 void init_is_principal(AjaxBehaviorEvent evento);
	 
	 void init_company(SelectEvent event);
	 
	 String getCompany_ENABLED();
}
