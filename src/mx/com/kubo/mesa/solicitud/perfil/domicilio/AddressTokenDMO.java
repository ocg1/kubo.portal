package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Residence;
import mx.com.kubo.model.ResidencePK;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.tools.Utilities;

public abstract class AddressTokenDMO 
implements AddressTokenIMO
{	
	protected EmploymentService service_employment;
	
	protected Residence   residence;
	protected ResidencePK residence_PK;
	
	protected Address address;
	
	protected NaturalPerson person;
	
	protected Employment employment;
	protected Business business;
	
	protected StringBuilder sb;
	
	protected String activity_name;
	protected String activity_TOKEN;	
	protected String address_TOKEN;
	protected String antiguedad;
	protected String residence_TOKEN = "";
	protected String street = "";	
	protected String address_number = "";
	protected String apt_number = "";
	protected String mx_manzana = "";
	protected String mx_lote = "";
	protected String colonia = "";
	protected String town = "";
	protected String estado = "";
	protected String zip_code = "";
	protected String first_street_reference = "";
	protected String second_street_reference = "";
	protected String description = "";
	protected String address_text;
	protected String country;
	
	protected String auxTenure;
	protected String auxYears;
	
	protected Integer years_since_when;
	protected Integer months_since_when;
	
	protected int company_id = 1;
	protected int address_type_id;	
	
	private final int PAIS_ORIGEN = 7;
	
	protected boolean female; 
	
	abstract void init_address();
	abstract void init_business_antiguedad();
	abstract void init_employ_permanencia();
	abstract void init_pais_origen_TOKEN();
	
	protected AddressTokenDMO()
	{
		service_employment = Utilities.findBean("employmentServiceImp");	
	}
	
	public void setPerson(NaturalPerson person)
	{
		if(person != null && person.getGender_id() != null && person.getGender_id() == 2)
		{			
			female = true;
			
		} else {
			
			female = false;
		}
	}
		
	public void setEmployment(Employment employment) 
	{
		this.employment = employment;
		
		activity_name = employment.getEmployer();
		
		init_employ_permanencia();
		
		activity_TOKEN = (female) ? "Empleada" : "Empleado";
		activity_TOKEN += " de " + activity_name + " por " + auxTenure;		
	}

	public void setBusiness(Business business) 
	{
		this.business = business;
		
		activity_name = business.getBusiness_name();
		
		init_business_antiguedad();
		
		activity_TOKEN = (female) ? "Empresaria" : "Empresario";
		activity_TOKEN += " de " + activity_name + " por " + auxYears;
	}

	public final void setResidence(Residence residence) 
	{
		this.residence = residence;
		
		if(residence != null)
		{		
			residence_TOKEN = residence.getDescription().toLowerCase();			
		}
	}
	
	public final void setAntiguedad(Integer length_residence)
	{					
		 if(length_residence == null)
		 {
			 antiguedad = "";
			 
		 } else if (length_residence == 1) {
			 
			 antiguedad = length_residence + " año ";
				 
		 } else {
			
			 antiguedad = length_residence + " años ";
		 }
	}

	public final void setAddress(Address address) 
	{
		this.address = address;
		
		if(address != null && address.getAddress_type_id() != null)
		{
			init_address(); 		
		}
	}
	
	public String getAddressTOKEN()
	{
		switch(address_type_id)
		{
			case PAIS_ORIGEN:
				init_pais_origen_TOKEN();
			break;
			
			default: 
				getAddress_TOKEN(); 
			break;
		}
		
		return address_TOKEN;
	}
	
	public String getActivity_TOKEN() 
	{
		return activity_TOKEN;
	}		
}
