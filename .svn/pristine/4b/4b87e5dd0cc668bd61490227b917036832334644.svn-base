package mx.com.kubo.mesa.solicitud.promo;

import mx.com.kubo.model.Promo;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;

public abstract class PromocionDMO 
implements PromocionIMO
{
	protected ServiceCatalogosIMO service_catalogos;
	
	protected Promo promo;
	protected ProyectLoan proyect_loan; 
	
	protected String r_data;
	
	protected boolean promo_ENABLED;

	public final void setService_catalogos(ServiceCatalogosIMO service) 
	{
		service_catalogos = service;
	}
	
	public final void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{		
			r_data = proyect_loan.getR_data();
		}
	}

	public final boolean isPromo_ENABLED() 
	{
		return promo_ENABLED;
	}

	public final Promo getPromo() 
	{
		return promo;
	}
}
