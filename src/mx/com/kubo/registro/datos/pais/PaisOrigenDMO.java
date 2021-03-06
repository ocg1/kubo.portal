package mx.com.kubo.registro.datos.pais;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Country;
import mx.com.kubo.model.CountryPK;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.CountryService;
import mx.com.kubo.tools.Utilities;

public abstract class PaisOrigenDMO 
implements PaisOrigenIMO
{
	@Autowired @Qualifier("addressServiceImp")
	protected AddressService service_address;
	
	@Autowired @Qualifier("countryServiceImp")
	protected CountryService service_country;
	
	protected HtmlInputText     input_text;
	protected HtmlInputTextarea input_text_area;
	protected HtmlSelectOneMenu input_select_menu;
	
	protected Address   address;
	protected AddressPK address_PK;
	
	protected Country   country;
	protected CountryPK country_PK;
	
	protected String pais;
	protected String codigo_postal;
	protected String ciudad;
	protected String address_text;
	
	protected int company_id;
	protected int prospectus_id;
	protected int country_id;
	protected int address_id;
	
	protected final int PAIS_DE_ORIGEN = 7;
	
	protected boolean address_ENABLED;
	protected boolean save_address_OK;

	protected PaisOrigenDMO(){
		
		
		service_address = Utilities.findBean("addressServiceImp") ;
		
		service_country = Utilities.findBean("countryServiceImp");
		
	}
	
	public final void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}

	public final void setProspectus_id(int prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}

	public Integer getCountry_id() 
	{
		return this.country_id;
		
	}
	
	public final void setCountry_id(int country_id) 
	{
		this.country_id = country_id;
		
		country_PK = new CountryPK(country_id, company_id);
		
		country = service_country.getCountryById(country_PK);
		
		pais = country.getName();
		
		System.out.println("PaisOrigenDMO.setCountry_id(): " + pais);
	}
	
	public final void setCodigo_postal(String codigo_postal) 
	{
		this.codigo_postal = codigo_postal;
	}
	
	public final void setCiudad(String ciudad) 
	{
		this.ciudad = ciudad;
	}
	
	public final void setAddress_text(String address_text) 
	{
		this.address_text = address_text;
	}
	
	public final String getPais() 
	{		
		return pais;
	}

	public final String getCodigo_postal() 
	{
		return codigo_postal;
	}
	
	public final String getCiudad() 
	{
		return ciudad;
	}

	public final String getAddress_text() 
	{
		return address_text;
	}
}
