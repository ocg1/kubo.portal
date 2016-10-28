package mx.com.kubo.registro.consulta;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Phone;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.CreditHistoryService;

public interface PersonDataIMO 
{
	void setService_access(AccessService service);
	void setService_credit_history(CreditHistoryService service);
	
	void setSesion(SessionBean sesion);
	void setMembership(Membership membership);
	void setCredit_history(CreditHistory credithistory);
	void setAddress(Address address);
	void setPhone(Phone phone);
	
	
	void init();
	void getPersonData();
}
