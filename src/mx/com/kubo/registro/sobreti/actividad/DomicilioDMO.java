package mx.com.kubo.registro.sobreti.actividad;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.bean.AddressBean;
import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.registro.ChangeControlDMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EmploymentService;

public abstract class DomicilioDMO extends ChangeControlDMO
implements DomicilioIMO
{
	protected     EmploymentService service_employment;
	protected        AddressService service_address;
	
	protected NaturalPerson person;
	
	protected Employment employment;
	protected Business business;
	
	protected AddressBean address_bean;
	protected Address   address;	
	protected AddressPK address_PK;
	
	protected NeighborhoodCat neighborhood;
	protected TownCat town;
	
	protected ArrayList<ListNeighborhoodBean> catalogo;
	protected List<NeighborhoodCat>  listAsents;
	
	protected String input_text_ID;
	protected String zipcode;
	protected String street;
	protected String address_number;
	protected String apt_number;
	protected String mx_manzana;
	protected String mx_lote;
	protected String first_street_reference;
	protected String second_street_reference;
	protected String description;
	protected String neighborhood_text;
	protected String json_array;
	
	protected int address_type_id;
	protected int neighborhood_id;
	protected int employment_id;
	protected int business_id;
	protected int address_id;		
	protected int field_index;
	
	protected final static int STREET = 6;
	protected final static int ADDRESS_NUMBER = 7;
	protected final static int APT_NUMBER = 8;
	protected final static int MX_MANZANA = 9;
	protected final static int MX_LOTE = 10;
	protected final static int FIRST_STREET_REFERENCE  = 11;
	protected final static int SECOND_STREET_REFERENCE = 12;
	protected final static int DESCRIPTION = 13;
	
	protected boolean zipcode_ENABLED;
	protected boolean flagUpdate;
	protected boolean flagSave;
	
	public void setService_employment(EmploymentService service) 
	{
		service_employment = service;
	}
	
	public void setService_address(AddressService service) 
	{
		service_address = service;
	}
	
	public final void setPerson(NaturalPerson person) 
	{
		this.person = person;
	}

	public void setAddress_type_id(int address_type_id) 
	{
		this.address_type_id = address_type_id;
	}
}
