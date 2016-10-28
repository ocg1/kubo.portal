package mx.com.kubo.services.fondeo.cliente;

import mx.com.kubo.bean.AltaClienteRequest;
import mx.com.kubo.model.IdentificationCollector;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.fondeo.ServiceFondeoDMO;
import mx.com.kubo.tools.Utilities;

public abstract class AltaClienteDMO extends ServiceFondeoDMO
{
	protected NaturalPerson person;
	
	protected IdentificationCollector objIFE;
	
	protected AltaClienteRequest request;	
	
	protected Integer nacionalidad;
	protected Integer tipo_identificacion;	
	protected Integer safi_tipo_identi_id;
		
	protected final String SAFI_ALTA_CLIENTE_INIT;
	protected final String SAFI_ALTA_CLIENTE_OK;
	protected final String SAFI_ALTA_CLIENTE_ERROR;
	protected final String SAFI_ALTA_CLIENTE_ERROR_INFO;
	protected final String SAFI_ALTA_CLIENTE_ERROR_RESP;
	
	protected final String OFICIAL = "S";
	protected final String NO_OFICIAL = "N";
	
	protected int safi_numero_caracteres;
	
	protected final int EXTRANJERO = 0;
		
	protected final int PASAPORTE = 3;
	protected final int CARTILLA_MILITAR   = 4;
	protected final int LICENCIA_MANEJO    = 5;
	protected final int CEDULA_PROFESIONAL = 6;	
	
	abstract void init_tipo_identificacion();
		
	protected AltaClienteDMO()
	{
		service_calling        = Utilities.findBean("serviceCallingServiceImp");
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");		
		
		SAFI_ALTA_CLIENTE_INIT       = "Invocando el procedimiento almacenado microfin.IDENTIFICLIENTEALT";
		SAFI_ALTA_CLIENTE_OK         = "Regresando Satisfactoriamente de invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT: ";
		SAFI_ALTA_CLIENTE_ERROR      = "Error al invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT";
		SAFI_ALTA_CLIENTE_ERROR_INFO = "El objeto es null. Error en el DAO NaturalPerson.getIdentificationCollector()";
		SAFI_ALTA_CLIENTE_ERROR_RESP = "Error al dar de alta los datos de identificación en Safi.";			
	}
		
	public void setAcreditado(ProyectLoan proyect_loan) 
	{	
		this.proyect_loan  = proyect_loan;
		
		if(proyect_loan != null)
		{		
			person = proyect_loan.getPerson();
			
			company_id    = person.getNatPerPK().getCompany_id();
			prospectus_id = person.getNatPerPK().getProspectus_id();
			
			nacionalidad        = person.getCitizenship();
			SAFI_client_id      = person.getSafi_client_id();
			tipo_identificacion = person.getIdentification_type_id();	
			acreditado_ID       = person.getMx_ife_cveelector();
			
			request = new AltaClienteRequest(); 
			
			init_tipo_identificacion();								
		}
		
		alta_cliente_ENABLED = false;
		
		if(acreditado_ID == null)
		{							
			lista_errores.add("El cliente no cuenta con identificación.");
		}
		
		if(SAFI_client_id == null)
		{							
			lista_errores.add("No existe el id del cliente en Safi.");
		}
		
		if(acreditado_ID != null && SAFI_client_id != null)
		{
			alta_cliente_ENABLED = true;
		}						
	}
}
