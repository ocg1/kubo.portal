package mx.com.kubo.portal.simulador;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.SimulatorBean;

public interface CreditSimulatorIMO 
{
	void setSesion(SessionBean sesion);	 

	void setAmmount(double ammount);
	
	void setFrequency_id(int frequency_id);
	
	void simulaCred(boolean isSafi);
	
	void init_cuota_by_formula();		
	void init_cat_SAFI();
	
/*	
	Double getPayment();
	Double getCat();
*/	
	
	SimulatorBean getSimulation();
}
