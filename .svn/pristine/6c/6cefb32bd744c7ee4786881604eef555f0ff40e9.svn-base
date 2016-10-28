package mx.com.kubo.services.fondeo;

import java.text.SimpleDateFormat;
import java.util.List;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.ClabeAccountService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ResidenceService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.ServiciosSAFIService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.ViewClientInfoService;

public abstract class ServiceFondeoDMO 
implements ServiceFondeoIMO
{
	protected ServiciosSAFIService  service_SAFI;
	protected PrevencionLDService   service_PLD;
	protected ViewClientInfoService service_client_INFO;
	protected ServiceCallingService service_calling;
	protected NaturalPersonService  service_natural_person;
	protected ProyectLoanService    service_proyect_loan;	
	protected ClabeAccountService   service_clabe_account;
	protected SavingAccountService  service_saving_account;
	protected AddressService        service_address;
	protected NeighborhoodService   service_neighborhood;
	protected TownService           service_town;
	protected PhoneService          service_phone;
	protected ProspectusService     service_prospectus;
	protected EmploymentService     service_employment;
	protected IncomeService         service_ingresos;
	protected BusinessService       service_business;
	protected ResidenceService      service_residencia;
	protected SystemParamService    service_system_param;
	
	protected SessionBean sesion;	
		
	protected NaturalPerson acreditado;
	
	protected ProyectLoan proyect_loan;
	
	protected SavingAccount   saving_account;
	protected SavingAccountPK saving_account_PK;
	
	protected List<SavingAccount> lista_saving_account;
	
	protected Address address;
	
	protected String codigo_respuesta;	
	protected String mensaje_respuesta;
	protected String exception_INFO;
	protected String pr_version;
	
	protected String SAFI_client_id;
	protected String SAFI_account_id;
	protected String SAFI_credit_id;
	protected String SAFI_related_person;
	protected String SAFI_seguro_id;
	protected String acreditado_ID;
	
	protected Integer SAFI_prospectus_id;
	protected Integer SAFI_solicitud_id;	
	protected Integer SAFI_cuenta_autorizada;
	
	protected SimpleDateFormat date_format;
	
	protected List<String> lista_errores;
	
	protected Character area;
	
	protected int company_id;
	protected int prospectus_id;	
	
	protected boolean SAFI_prospecto_NEW;
	protected boolean SAFI_client_NEW;
	protected boolean SAFI_account_NEW;
	protected boolean SAFI_solicitud_NEW;
	protected boolean SAFI_credit_NEW;
	protected boolean SAFI_related_person_NEW;
	protected boolean SAFI_seguro_NEW;
	
	protected boolean cuenta_OK;
	
	protected boolean alta_prospecto_OK;
	protected boolean solicitud_credito_OK;
	protected boolean alta_cliente_OK;
	protected boolean alta_cliente_ENABLED;
	protected boolean creacion_credito_OK;
	protected boolean creacion_seguro_OK;
		
	protected boolean SAFI_client_ENABLED;
	protected boolean SAFI_solicitud_ENABLED;
	
	protected final String SUCCESS;
	protected final String SAFI_FINANZAS_ID;
	
	protected final int INIT;
	protected final int RESPONSE;	
	protected final int ERROR;
	
	protected final int ACTIVADA = 1;	
	protected final int OTRA = 0;	
	protected final int SAFI_ELECTOR = 1;
	protected final int SAFI_COMERCIANTE;
	protected final int SAFI_EMPLEADO;
	protected static final int IFE = 1;
	protected static final int INE = 2;
	protected static final int TRANSFERENCIA = 1;	
	
	protected ServiceFondeoDMO()
	{				
		SUCCESS = "0";				
		
		INIT     = 1;
		RESPONSE = 2;
		ERROR    = 3;
		
		SAFI_FINANZAS_ID = "17";
		
		SAFI_COMERCIANTE =  710;
		SAFI_EMPLEADO    =  139;
	}
	
	public void setAcreditado(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
	}
	
	public final void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}
	
	public void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
		
		pr_version = sesion.getVersion_description();
	}

	public final void setProspectus_id(int prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}

	public final void setLista_errores(List<String> lista_errores) 
	{
		this.lista_errores = lista_errores;
	}
	
	public final List<String> getLista_errores() 
	{	
		return lista_errores;
	}
	
	public String getSAFI_client_id() 
	{
		return SAFI_client_id;
	}
	
	public String getSAFI_account_id() 
	{	
		return SAFI_account_id;
	}
	
	public final Integer getSAFI_prospectus_id() 
	{
		return SAFI_prospectus_id;
	}

	public final boolean isAlta_prospecto_OK() 
	{
		return alta_prospecto_OK;
	}

	public final boolean isSolicitud_credito_OK() 
	{
		return solicitud_credito_OK;
	}

	public final boolean isAlta_cliente_OK() 
	{
		return alta_cliente_OK;
	}
	
	public final boolean isAlta_cliente_ENABLED() 
	{
		return alta_cliente_ENABLED;
	}
	
	public final boolean isCreacion_credito_OK() 
	{
		return creacion_credito_OK;
	}
	
	public final boolean isCreacion_seguro_OK() 
	{
		return creacion_seguro_OK;
	}

	public boolean isCuenta_OK() 
	{
		return cuenta_OK;
	}
}
