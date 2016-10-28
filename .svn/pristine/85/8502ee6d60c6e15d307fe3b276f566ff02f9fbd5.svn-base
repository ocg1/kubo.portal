package mx.com.kubo.registro.datos.domicilio;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Residence;
import mx.com.kubo.registro.datos.DatosPersonalesDMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ResidenceService;

public abstract class DomicilioDMO extends DatosPersonalesDMO
implements DomicilioIMO
{
	protected    AddressService service_address;
	protected EmploymentService service_employment;
	protected  ResidenceService service_residence;
	protected ProspectusService service_prospectus;
	
	protected Address   address;
	protected AddressPK address_PK;
	
	protected DomicilioIMO to_compare;
	
	protected ListNeighborhoodBean colonia;	
	
	protected ArrayList<ListNeighborhoodBean> lista_de_colonias;	
	
	protected List<NeighborhoodCat> colonias_por_codigo_postal;
	protected List<Residence> residencelist;
	
	protected String zipcode;		
	protected String estado;	
	protected String ciudad;
	protected String delegMun;
	protected String neighborhood_text;
	protected String street;
	protected String address_number;
	protected String apt_number;
	protected String mx_manzana;
	protected String mx_lote;
	protected String first_reference;
	protected String second_reference;
	protected String description;
	protected String latLong;
	protected String colonias_JSON;
	
	protected Integer residence_id;
	protected Integer country_id;	
	protected Integer stateId;
	protected Integer townId;
	protected Integer neighborhood_id;
	
	protected int address_type_id;
	protected int address_id;
	
	protected final int DOMICILIO_CASA    = 1;
	protected final int DOMICILIO_EMPRESA = 8;
	protected final int DOMICILIO_FISCAL  = 9;
	
	protected boolean residence_ENABLED;	
	protected boolean neighborhood_text_ENABLED;
	protected boolean coverage_zone_ENABLED;
	protected boolean save_address_OK;
	protected boolean inversionista_ENABLED;
	protected boolean state_ENABLED;
	protected boolean isValid;
	protected boolean same_address_ENABLED;

	public final void setService_address(AddressService service) 
	{
		service_address = service;
	}

	public final void setService_employment(EmploymentService service) 
	{
		service_employment = service;
	}
	
	public final void setService_residence(ResidenceService service) 
	{
		service_residence = service;
	}

	public final void setService_prospectus(ProspectusService service)
	{
		service_prospectus = service;
	}

	public final void setPerson(NaturalPerson person) 
	{
		this.person = person;
		
		company_id    = person.getNatPerPK().getCompany_id();
		prospectus_id = person.getNatPerPK().getProspectus_id();
		
		inversionista_ENABLED = person.getProspectus().getArea() == 'I';
	}
	
	public final void setAddress_type_id(int address_type_id) 
	{
		this.address_type_id = address_type_id;
	}
	
	public final void setResidence_ENABLED(boolean residence_ENABLED) 
	{
		this.residence_ENABLED = residence_ENABLED;
	}
	
	public final void setNeighborhood_text_ENABLED(boolean neighborhood_text_ENABLED)
	{
		this.neighborhood_text_ENABLED = neighborhood_text_ENABLED;
	}
	
	public final List<NeighborhoodCat> getColonias_por_codigo_postal()
	{
		return colonias_por_codigo_postal;
	}

	public final List<Residence> getResidencelist() 
	{
		return residencelist;
	}
	
	public final Address getAddress() 
	{
		return address;
	}

	public final String getZipcode() 
	{
		return zipcode;
	}
	
/*	
	public void setZipcode(String zipcode) 
	{
		this.zipcode = zipcode;
	}
*/	

	public final String getLatLong() 
	{
		return latLong;
	}
	public final void setLatLong(String latLong) 
	{
		this.latLong = latLong;
	}
	public final String getDelegMun() 
	{
		return delegMun;
	}
	
	public final String getEstado() 
	{
		return estado;
	}

	public final boolean equals(Object object) 
	{		
		same_address_ENABLED = false;
		
		if(object instanceof DomicilioIMO)
		{
			to_compare = (DomicilioIMO) object;
			
			init_same_address_ENABLED();
		}				
		
		return same_address_ENABLED;
	}

	protected abstract void init_same_address_ENABLED();

	public final Integer getNeighborhood_id() 
	{
		return neighborhood_id;
	}

	public final boolean isCoverage_zone_ENABLED() 
	{
		return coverage_zone_ENABLED;
	}

	public final boolean isNeighborhood_text_ENABLED() 
	{
		return neighborhood_text_ENABLED;
	}

	public final boolean isResidence_ENABLED() 
	{
		return residence_ENABLED;
	}

	public final boolean isSave_address_OK() 
	{
		return save_address_OK;
	}
}
