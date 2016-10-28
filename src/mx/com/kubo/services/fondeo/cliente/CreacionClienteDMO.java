package mx.com.kubo.services.fondeo.cliente;

import safisrv.ws.ClienteServicios.AltaClienteResponse;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ViewClientInfo;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.services.fondeo.ServiceFondeoIMO;
import mx.com.kubo.tools.Utilities;

public abstract class CreacionClienteDMO extends ServiceFondeoDMO
implements ServiceFondeoIMO
{	
	protected AltaClienteResponse response;
	
	protected ViewClientInfo view_client_info;
		
	protected String RFC;
	
	protected final String SAFI_CREACION_CLIENTE_ERROR;
	
	protected CreacionClienteDMO()
	{
		service_calling        = Utilities.findBean("serviceCallingServiceImp");
		service_SAFI           = Utilities.findBean("serviciosSAFIServiceImp");
		service_client_INFO    = Utilities.findBean("viewClientInfoServiceImp"); 
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");
		
		SAFI_CREACION_CLIENTE_ERROR = "SAFIServiciosServiceLocator.serviciosSAFIservice.createClientSAFI(): ERROR";
	}
	
	public final void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		if(proyect_loan != null)
		{		
			acreditado = proyect_loan.getPerson();
			
			company_id    = acreditado.getNatPerPK().getCompany_id();
			prospectus_id = acreditado.getNatPerPK().getProspectus_id();
			
			SAFI_client_id = acreditado.getSafi_client_id();			
			
			SAFI_client_NEW = SAFI_client_id == null || SAFI_client_id.equals("") || SAFI_client_id.equals("0");
		}
	}
	
	public final String getSAFI_client_id() 
	{
		return SAFI_client_id;
	}
}
