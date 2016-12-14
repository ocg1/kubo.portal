package mx.com.kubo.portal.ofertas;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.SimulatorBean;

public interface SimuladorIMO 
{			
	void setSesion(SessionBean sesion);
	
	void setRenovacion(RenovacionBean renovacion);
	
	void init();	
	
	boolean isMax_payment_ENABLED();
		
	String getTerm_frequency_TOKEN();
	
	SimulatorBean getSimulation();
}
