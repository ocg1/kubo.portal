package mx.com.kubo.services.fondeo.prospecto;

import safisrv.ws.CreditosServicios.AltaProspectoRequest;
import safisrv.ws.CreditosServicios.AltaProspectoResponse;
import safisrv.ws.CreditosServicios.SAFIServicios;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.services.fondeo.ServiceFondeoIMO;
import mx.com.kubo.tools.Utilities;

public abstract class AltaProspectoDMO extends ServiceFondeoDMO
implements ServiceFondeoIMO
{	
	protected SAFIServiciosServiceLocator locator;
	protected SAFIServicios service;
	
	protected AltaProspectoRequest  request;
	protected AltaProspectoResponse response;
	
	protected Prospectus prospecto;
	protected NaturalPerson cliente_SAFI;
	
	protected NeighborhoodCat   colonia;
	protected NeighborhoodCatPK colonia_PK;
	
	protected TownCat   delegacion;
	protected TownCatPK delegacion_PK;
	
	protected Phone celular;
	
	protected String RFC;
	protected String tel;
	protected String colonia_INFO;
	protected String first_name;
	protected String middle_name;
	protected String apellido_materno;
	protected String apellido_paterno;
	protected String calle;
	protected String codigo_postal;
	protected String lote;
	protected String manzana;	
	protected String address_number;
	protected String apt_number;	
	protected String latitud;
	protected String longitud;
	protected String exception_INFO;
	
	protected Character person_type;
	
	protected Integer colonia_id;
	protected Integer delegacion_id;
	protected Integer state_id;	

	protected int SAFI_delegacion_id;
	
	protected final String SAFI_ALTA_PROSPECTO_INIT;
	protected final String SAFI_ALTA_PROSPECTO_OK;
	protected final String SAFI_ALTA_PROSPECTO_ERROR;
		
	protected final int PARTICULAR_CELULAR;
	
	protected AltaProspectoDMO()
	{
		service_address        = Utilities.findBean("addressServiceImp");
		service_neighborhood   = Utilities.findBean("neighborhoodServiceImp");
		service_town           = Utilities.findBean("townServiceImp");
		service_calling        = Utilities.findBean("serviceCallingServiceImp");
		service_prospectus     = Utilities.findBean("prospectusServiceImp");
		service_phone          = Utilities.findBean("phoneServiceImp");
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");
		service_saving_account = Utilities.findBean("savingAccountServiceImp");
		
		SAFI_ALTA_PROSPECTO_INIT    = "SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaProspecto(): INIT ";
		SAFI_ALTA_PROSPECTO_OK      = "SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaProspecto(): OK ";
		SAFI_ALTA_PROSPECTO_ERROR   = "SAFIServiciosServiceLocator.getSAFIServiciosSoap11.altaProspecto(): ERROR ";
		
		PARTICULAR_CELULAR = 6;					
	}
	
	public final void setAcreditado(ProyectLoan proyect_loan)
	{		
		this.proyect_loan  = proyect_loan;
		
		if(proyect_loan != null)
		{		
			acreditado = proyect_loan.getPerson();
			
			company_id    = acreditado.getNatPerPK().getCompany_id();
			prospectus_id = acreditado.getNatPerPK().getProspectus_id();
			area          = acreditado.getProspectus().getArea();			
			RFC           = acreditado.getMx_rfc();
			
			cliente_SAFI = service_natural_person.existNaturalPersonByRFC(RFC);
			
			if(cliente_SAFI != null)
			{
				prospecto = cliente_SAFI.getProspectus();
				
				SAFI_prospectus_id = prospecto.getSafi_prospectus_id();
			}	
			
			SAFI_prospecto_NEW = cliente_SAFI == null || SAFI_prospectus_id == null || (SAFI_prospectus_id + "").length() == 0;			
		}
	}
}
