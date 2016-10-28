package mx.com.kubo.listeners.registro;

import java.util.List;

import mx.com.kubo.bean.ActividadEconomicaDMO;
import mx.com.kubo.bean.AddressBean;
import mx.com.kubo.bean.BusinessBean;
import mx.com.kubo.bean.EmploymentBean;
import mx.com.kubo.bean.PhoneBean;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMO;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMP;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Residence;
import mx.com.kubo.model.ResidencePK;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ResidenceService;
import mx.com.kubo.tools.Utilities;

import org.apache.log4j.Logger;

public abstract class ListenerMiPrestamoDMO 
implements ListenerMiPrestamoIMO
{
	protected PhoneService      service_phone;
	protected EmploymentService service_employment;
	protected AddressService    service_address;
	protected BusinessService   service_business;
	protected ResidenceService  service_residencia;
	
	protected Logger log = Logger.getLogger(getClass());
	
	protected SessionBean sesion;
	protected NaturalPerson natural_person;
	protected AddressTokenIMO token;
	
	protected AddressPK   address_PK;
	protected Address     address_CASA;
	protected Address     address;
	protected AddressBean address_bean;
	protected StateCat    estado;
	protected TownCat     delegacion;
	protected Residence   tipo_vivienda;
	protected ResidencePK tipo_vivienda_PK;
	
	protected List<NeighborhoodCat> colonias_por_zipcode;
	
	protected Employment     employment;
	protected EmploymentBean employment_bean;
	protected EmploymentBean employment_bean_NEW;
	protected PhoneBean      employment_phone_FIJO;
	protected PhoneBean      employment_phone_CEL;	
	
	protected List<ActividadEconomicaDMO> lista_employment_bean;
	protected List<Employment>     lista_employment;
	protected List<Phone>          lista_employment_phone;	
	
	protected Business     business;
	protected BusinessBean business_bean;
	protected PhoneBean    business_phone_FIJO;
	protected PhoneBean    business_phone_CEL;
	
	protected Phone phone;
	
	protected List<ActividadEconomicaDMO>  lista_business_bean;
	protected List<Business>      lista_business;
	protected List<Phone>         lista_business_phone;
	
	protected String zipcode;
	protected String latitude;
	protected String longitud;
	protected String phone_number;
	protected String phone_extension;
	protected String address_TOKEN;
	protected String address_CASA_TOKEN;
	protected String antiguedad;
	
	protected String[] phonestr;
	
	protected Integer colonia_id;
	protected Integer phone_type_id;
	protected Integer employment_id;
	protected Integer business_id;
	protected Integer residence_id;
	protected Integer antiguedad_vivienda;
	
	protected Character area;
	
	protected int prospectus_id;
	protected int company_id;
	protected int address_id;
	protected int employment_id_NEW;		
	
	protected boolean update_OK;
	protected boolean saved_OK;
	protected boolean remove_OK;
	protected boolean same_address_ENABLED;
	
	protected final int MEXICO            = 700;
	protected final int CASA              = 1;
	protected final int BUSINESS          = 3;
	protected final int BUSINESS_FIJO     = 3;
	protected final int BUSINESS_CELLULAR = 4;
	
	protected final int EMPLOYMENT          = 4;
	protected final int EMPLOYMENT_FIJO     = 1;
	protected final int EMPLOYMENT_CELLULAR = 2;
	
	protected abstract void init_lista_employment_bean();
	protected abstract void init_lista_business_bean();
	
	protected ListenerMiPrestamoDMO()
	{
		service_phone      = Utilities.findBean("phoneServiceImp");
		service_employment = Utilities.findBean("employmentServiceImp");
		service_address    = Utilities.findBean("addressServiceImp");
		service_business   = Utilities.findBean("businessServiceImp");
		service_residencia = Utilities.findBean("residenceServiceImp");
	}
	
	public void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		area          = sesion.getArea();
	}
	
	public final void setNatural_person(NaturalPerson natural_person) 
	{
		this.natural_person = natural_person;
		
		residence_id        = natural_person.getResidence_id();
		antiguedad_vivienda = natural_person.getLength_of_residence();			
		
		if(residence_id != null)
		{
			tipo_vivienda_PK = new ResidencePK(residence_id, company_id);
			
			tipo_vivienda = service_residencia.getResidenceById(tipo_vivienda_PK);
		}	
	}
	
	public final void setEmployment_bean(EmploymentBean employment_bean)
	{
		employment_bean_NEW = employment_bean;
		
		same_address_ENABLED = employment_bean_NEW.getSame_address_ENABLED();	
		address              = employment_bean_NEW.getAddressbean().getAddress();
		employment           = employment_bean_NEW.getEmployment();	
						
		employment_id = employment.getEmploymentPK().getEmployment_id();		
		business_id   = null;
	}
	
	public final void setBusiness_bean(BusinessBean business_bean) 
	{
		this.business_bean = business_bean;
		
		same_address_ENABLED = business_bean.getSame_address_ENABLED();
		address              = business_bean.getAddressbean().getAddress();
		business             = business_bean.getBusiness();
		
		business_id   = business.getBusinessPK().getBusiness_id();					
		employment_id = null;
	}
	
	public final List<ActividadEconomicaDMO> getLista_employment_bean() 
	{
		address_CASA = service_address.getMaxAddressByType(prospectus_id, company_id, CASA);				
		
		token = new AddressTokenIMP();
		token.setResidence(tipo_vivienda);
		token.setAntiguedad(antiguedad_vivienda);
		token.setAddress(address_CASA);
		
		address_CASA_TOKEN = token.getAddress_TOKEN();
		
		init_lista_employment_bean();
		
		return lista_employment_bean;
	}	
	
	public final List<ActividadEconomicaDMO> getLista_business_bean()
	{
		address_CASA = service_address.getMaxAddressByType(prospectus_id, company_id, CASA);
		
		token = new AddressTokenIMP();
		token.setResidence(tipo_vivienda);
		token.setAntiguedad(antiguedad_vivienda);
		token.setAddress(address_CASA);
		
		address_CASA_TOKEN = token.getAddress_TOKEN();
		
		init_lista_business_bean();
		
		return lista_business_bean;
	}
}
