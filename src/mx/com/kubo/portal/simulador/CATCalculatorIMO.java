package mx.com.kubo.portal.simulador;

import mx.com.kubo.managedbeans.TablaAmortizacion;

public interface CATCalculatorIMO 
{
	//void setSesion(SessionBean sesion);
	void setAmortization(TablaAmortizacion amortization);
	void setTotalPagar(Double totalPagar);
	void setSafiSimulation(boolean isSafiSimulation);	
	
	public void setTasaTotal(Double tasaTotal);
	public void setComisionApertura(Double comisionApertura);
	
	void init();
	
	Double getCat();
}
