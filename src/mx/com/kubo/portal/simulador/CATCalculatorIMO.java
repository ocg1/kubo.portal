package mx.com.kubo.portal.simulador;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.TablaAmortizacion;

public interface CATCalculatorIMO 
{
	void setSesion(SessionBean sesion);
	void setAmortization(TablaAmortizacion amortization);
	void setTotalPagar(Double totalPagar);
	void setSafiSimulation(boolean isSafiSimulation);	
	
	void init();
	
	Double getCat();
}
