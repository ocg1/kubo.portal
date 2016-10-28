package mx.com.kubo.mesa.solicitud.promo;

import mx.com.kubo.model.Promo;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;

public interface PromocionIMO 
{
	void setService_catalogos(ServiceCatalogosIMO service);
	void setProyect_loan(ProyectLoan proyect_loan);
	
	void init();
	
	boolean isPromo_ENABLED();
	
	Promo getPromo();
}
