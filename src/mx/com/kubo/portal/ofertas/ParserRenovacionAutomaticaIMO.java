package mx.com.kubo.portal.ofertas;

import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;

public interface ParserRenovacionAutomaticaIMO 
{
	void setScore(Scoring score);
	
	void setProspectus_id(int prospectus_id);

	void setCompany_id(int company_id);
	
	void init( );	
	
	boolean is_ofert_ENABLED();
	
	SimulatorBean getSimulation();
	
	RenovacionBean getRenovacion(String SAFI_credit_id);
	
	String getTerm_frequency_TOKEN();	
	
	String getLoan_type_id();
}
