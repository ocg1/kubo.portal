package mx.com.kubo.portal.simulador;


public interface SAFICuotaCreditoIMO 
{
//	void setSesion(SessionBean sesion);
	
	void init();
	
	Double getCat();
	Double getMontoCuota();
	Double getTotalPagar();
	
	int getNumCuota();
	
	boolean isFlagSaveSimulationCache();
	
	public void setCompany_id(int company_id);
	public void setProspectus_id(int prospectus_id);
}
