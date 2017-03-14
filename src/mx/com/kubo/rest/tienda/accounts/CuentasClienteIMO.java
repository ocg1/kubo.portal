package mx.com.kubo.rest.tienda.accounts;

import java.util.List;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;

public interface CuentasClienteIMO 
{
	void setSesion(SessionBean sesion);
	
	void init();
	
	Double getSaldoTotal();
	
	List<InvestorsAccounts> getListInvAccounts();
}
