package mx.com.kubo.portal.ofertas;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;

public interface ParserRenovacionAutomaticaIMO 
{
	void setScore(Scoring score);
	
	void setSesion(SessionBean sesion);		
	
	void init();	
	
	boolean is_ofert_ENABLED();
	
	SimulatorBean getSimulation();
	
	RenovacionBean getRenovacion(String SAFI_credit_id);
	
	String getTerm_frequency_TOKEN();	
	
	String getLoan_type_id();
}
