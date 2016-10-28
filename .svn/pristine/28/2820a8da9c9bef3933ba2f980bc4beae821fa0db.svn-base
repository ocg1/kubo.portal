package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ResidenceService;
import mx.com.kubo.tools.Utilities;

public abstract class DomicilioDMO 
implements DomicilioIMO 
{
	protected        AddressService service_address;
	protected    ProyectLoanService service_proyect_loan;
	protected  NaturalPersonService service_natural_person;	
	protected      ResidenceService service_residence;
	protected     EmploymentService service_employment;
	protected Change_controlService service_change_control;
	
	protected RequestContext request;
	
	protected HtmlInputText     input_text;
	protected HtmlSelectOneMenu select_menu;
	protected HtmlInputTextarea text_area;
	
	protected SessionBean sesion;
	
	protected ProyectLoan proyect_loan;
	protected Employment employment;
	protected Business business;
	
	protected Address address;
	protected NeighborhoodCat neighborhood;
	protected TownCat town;
	
	protected Coincidencia coincidencia;
	protected NaturalPerson natural_person;
	protected gnNaturalPersonPK natural_person_PK;
	protected AddressTokenIMO token;
	

	protected Change_controlPK change_control_PK;
	protected Change_control   change_control;
	protected ChangeBean change_control_bean;
	
	protected List<Change_control>  lista_change_control;
	
	protected List<Address>         lista_address;
	protected List<Coincidencia>    lista_coincidencias;
	protected List<NeighborhoodCat> lista_neighborhood;		
	protected List <Employment>     lista_employment;
	protected List <Business> 	    lista_business;
	
	protected String alarma_NIVEL_1;
	protected String alarma_NIVEL_2;
	protected String zipcode;
	protected String neighborhood_text;
	protected String address_TOKEN;
	protected String address_TOKEN_NEW;
	protected String input_text_value;
	protected String input_text_id;
	protected String IP_address_client;
	
	protected String activity_TOKEN;
	
	protected final String[] afected_tables;
	protected final String[] afected_fields;	
	
	protected Long coincidencias_NIVEL_1;
	protected Long coincidencias_NIVEL_2;	
	
	protected int company_id;
	protected int prospectus_id;
	protected int change_prospectus_id;
	protected int address_type_id;
	protected int neighborhood_id;
	
	protected final int DEFAULT = 0;
	protected final int NIVEL_1 = 1;
	protected final int NIVEL_2 = 2;	
	protected final int NEGOCIO = 3;
	protected final int EMPRESA = 4;
	
	protected boolean zipcode_ENABLED;
	protected boolean address_ENABLED;
	protected boolean change_control_OK;
	protected boolean change_control_ENABLED;
	protected boolean update_OK;
	
	protected DomicilioDMO()
	{
		service_address        = Utilities.findBean("addressServiceImp");
		service_proyect_loan   = Utilities.findBean("proyectLoanServiceImp");
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");
		service_residence      = Utilities.findBean("residenceServiceImp");
		service_employment     = Utilities.findBean("employmentServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");	
		
		afected_tables = new String[]{"gn_address"};
		afected_fields = new String[]{"address_TOKEN"};
	}	
	
	public final void setSesion(SessionBean sesion)
	{
		this.sesion = sesion;
		
		change_prospectus_id = sesion.getProspectus_id();
		
		IP_address_client = sesion.getIP_address_client();
	}	
	
	public void setLista_employment(List<Employment> lista_employment) 
	{
		this.lista_employment = lista_employment;
		
		if(lista_employment != null && !lista_employment.isEmpty()) 
		{			
			address_type_id = EMPRESA;
			
			int index = lista_employment.size() - 1;			
			
			employment = lista_employment.get(index);
				
			if(address == null)
			{
				address = employment.getAddressEmploy();
				
				init_address();
			}
		}
	}

	public void setLista_business(List<Business> lista_business) 
	{
		this.lista_business = lista_business;
		
		if(lista_business != null && !lista_business.isEmpty()) 
		{			
			address_type_id = NEGOCIO;
			
			int index = lista_business.size() - 1;		
			
			business = lista_business.get(index);
			
			if(address == null)
			{
				address = business.getAddressBusiness();	
				
				init_address();
			}
		}			
	}

	public final void setAddress(Address address)
	{
		this.address = address;
		
		init_address();
	}		
	
	private void init_address() 
	{
		if(address != null)
		{
			   company_id = address.getAddressPK().getCompany_id();
			prospectus_id = address.getAddressPK().getProspectus_id();
			   
			   address_type_id = address.getAddress_type_id();
			   
			   address_ENABLED = true;
		}
	}

	public Address getAddress() 
	{
		return address;
	}	

	public ChangeBean getChange_control_bean() 
	{
		return change_control_bean;
	}
		
	public String getActivity_TOKEN() 
	{
		return activity_TOKEN;
	}

	public String getAddress_TOKEN() 
	{
		return address_TOKEN;
	}

	public final Long getCoincidencias_NIVEL_1() 
	{
		return coincidencias_NIVEL_1;
	}

	public final Long getCoincidencias_NIVEL_2() 
	{
		return coincidencias_NIVEL_2;
	}
	
	public final String getAlarma_NIVEL_1() 
	{
		return alarma_NIVEL_1;
	}

	public final String getAlarma_NIVEL_2() 
	{
		return alarma_NIVEL_2;
	}
	
	public final List<Coincidencia> getLista_coincidencias() 
	{
		return lista_coincidencias;
	}

	public List<NeighborhoodCat> getLista_neighborhood() 
	{
		return lista_neighborhood;
	}	
}
