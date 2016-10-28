package mx.com.kubo.portal.simulador;

import mx.com.kubo.managedbeans.SessionBean;

public interface SAFICuotaCreditoIMO 
{
	void setSesion(SessionBean sesion);
	
	void init();
	
	Double getCat();
	Double getMontoCuota();
	Double getTotalPagar();
	
	int getNumCuota();
	
	boolean isFlagSaveSimulationCache();
}
