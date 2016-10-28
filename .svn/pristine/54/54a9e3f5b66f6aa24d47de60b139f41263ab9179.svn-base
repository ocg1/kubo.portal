package mx.com.kubo.services.fondeo.cliente;

import java.util.List;

import safisrv.ws.ClienteServicios.AltaConocimientoCteResponse;
import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.tools.Utilities;

public abstract class AltaConocimientoClienteDMO extends ServiceFondeoDMO 
{	
	protected AltaConocimientoCteResponse response;
	
	protected PrevencionLD pld;
	protected BmxEconActivityCat bmx;
	
	protected List<Income>     lstIng;
	protected List<Business>   listB;
	protected List<Employment> listE;
	
	protected String principal_fuente_ingresos;
	
	protected Double resInc;	
	
	protected final String SAFI_ALTA_PROSPECTO_ERROR;
	
	protected AltaConocimientoClienteDMO()
	{
		service_SAFI       = Utilities.findBean("serviciosSAFIServiceImp");	
		service_PLD        = Utilities.findBean("prevencionLDServiceImp");	
		service_calling    = Utilities.findBean("serviceCallingServiceImp");
		service_employment = Utilities.findBean("employmentServiceImp");
		service_ingresos   = Utilities.findBean("incomeServiceImp");
		service_business   = Utilities.findBean("businessServiceImp");
		
		SAFI_ALTA_PROSPECTO_ERROR = "SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaConocimientoCte(): ERROR ";
	}	
	
	public void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan  = proyect_loan;
		
		if(proyect_loan != null)
		{		
			acreditado = proyect_loan.getPerson();
			
			company_id     = acreditado.getNatPerPK().getCompany_id();
			prospectus_id  = acreditado.getNatPerPK().getProspectus_id();
			area           = acreditado.getProspectus().getArea();			
			SAFI_client_id = acreditado.getSafi_client_id();			
			
			pld = service_PLD.getPrevencionLDByProspectus(prospectus_id, company_id);
			
			if(pld != null)
			{		
				SAFI_client_ENABLED = SAFI_client_id != null && !SAFI_client_id.equals("") && !SAFI_client_id.equals(" ");
			}
		}
	}
}
