package mx.com.kubo.portal.simulador;

import mx.com.kubo.model.SimulatorBean;

public interface CreditSimulatorIMO 
{	
	void setAmmount(double ammount);
	
	void setFrequency_id(int frequency_id);
	
	//void simulaCred(boolean isSafi);
	
	void init_cuota_by_formula();		
	void init_cat_SAFI();
	
	void setProspectus_id(int prospectus_id);
	void setCompany_id(int company_id);
	
	public void setTasaTotal(Double tasaTotal);
	public void setComisionApertura(Double comisionApertura);
	
	SimulatorBean getSimulation();
}
