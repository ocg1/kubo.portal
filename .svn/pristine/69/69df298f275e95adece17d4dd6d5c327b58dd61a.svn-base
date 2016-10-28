package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity @Table(name = "gn_address")
public class Address 
implements Serializable 
{
	@OneToOne
	@JoinColumns
	(value = {
			@JoinColumn(name = "employment_id", referencedColumnName = "employment_id", insertable = false, updatable = false),	       
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
	})private Employment employment;
	
	@OneToOne
	@JoinColumns
	(value = {
			@JoinColumn(name = "business_id",    referencedColumnName = "business_id",   insertable = false,  updatable = false),	   
			@JoinColumn(name = "prospectus_id",  referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",     referencedColumnName = "company_id",    insertable = false, updatable = false)	       
	})private Business business;
	
	@OneToOne
	@JoinColumns
	(value = {
			@JoinColumn(name = "town_id",    referencedColumnName = "town_id",    insertable = false, updatable = false),	   			 
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	})private TownCat townCat;
	
	@OneToOne
	@JoinColumns
	(value = {
			@JoinColumn(name = "state_id",   referencedColumnName = "state_id",     insertable = false, updatable = false),	   			 
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	})private StateCat stateCat;
	
	@OneToOne
	@JoinColumns
	(value = {
			@JoinColumn(name = "neighborhood_id", referencedColumnName = "neighborhood_id", insertable = false, updatable = false),	   			 
	        @JoinColumn(name = "company_id",      referencedColumnName = "company_id",      insertable = false, updatable = false)
	}) private NeighborhoodCat neighborhood;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "address_type_id", referencedColumnName = "address_type_id",   insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",      referencedColumnName = "company_id",        insertable = false, updatable = false)
	}) private AddressType address_type;
	
	@OneToOne
	@JoinColumns(value = {
			@JoinColumn(name = "country_id", referencedColumnName = "country_id", insertable = false, updatable = false),
			@JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	}) private Country country;
	
	@EmbeddedId private AddressPK addressPK;
	
	@Column private String street;                  //	varchar(60)
	@Column private String address_number;          //	varchar(10)
	@Column private String address_text;            //	varchar(1000)
	@Column private String apt_number;              //	varchar(10)
	@Column private String floor;                   //	varchar(5)
	@Column private String mx_manzana;              //	varchar(5)
	@Column private String mx_lote;                 //	varchar(5)
	@Column private String neighborhood_text;       //	varchar(45)
	@Column private String first_street_reference;  //	varchar(45)
	@Column private String second_street_reference; //	varchar(45)
	@Column private String zip_code;//	varchar(10);//
	@Column private String description;//	varchar(100)
	@Column private String latitude;//	"decimal(10)
	@Column private String longitude;//	"decimal(10)
	@Column private String city_text;//	"varchar(100)
	
	@Column private Integer address_type_id;
	@Column private Integer country_id;             //	tinyint(3)  unsigned
	@Column private Integer state_id;               //	smallint(5) unsigned
	@Column private Integer town_id;                //	int(10)     unsigned
	@Column private Integer neighborhood_id;        //	varchar(45)	
	@Column private Integer employment_id; 	
	@Column private Integer business_id;	
	@Column private Integer beneficiarie_id;
	
	public void setAddressPK(AddressPK addressPK) 
	{
		this.addressPK = addressPK;
	}
	
	public void setTownCat(TownCat townCat) 
	{
		this.townCat = townCat;
	}
	
	public void setStateCat(StateCat stateCat) 
	{
		this.stateCat = stateCat;
	}
	
	public void setNeighborhood(NeighborhoodCat neighborhood) 
	{
		this.neighborhood = neighborhood;
	}
	
	public void setEmployment(Employment employment) 
	{
		this.employment = employment;
	}
	
	public void setBusiness(Business business) 
	{
		this.business = business;
	}
	
	public void setAddress_type_id(Integer address_type_id) 
	{
		this.address_type_id = address_type_id;
	}
	
	public void setCountry_id(Integer country_id) 
	{
		this.country_id = country_id;
	}
	
	public void setState_id(Integer state_id) 
	{
		this.state_id = state_id;
	}
	
	public void setTown_id(Integer town_id) 
	{
		this.town_id = town_id;
	}
	
	public final void setBeneficiarie_id(Integer beneficiarie_id) 
	{
		this.beneficiarie_id = beneficiarie_id;
	}
	
	public void setNeighborhood_id(Integer neighborhood_id) 
	{
		if( neighborhood_id == null || neighborhood_id == 0){
			this.neighborhood_id = null;
		}else{
			this.neighborhood_id = neighborhood_id;
		}
		
	}
	
	public void setEmployment_id(Integer employment_id) 
	{
		this.employment_id = employment_id;
	}
	
	public void setBusiness_id(Integer business_id) 
	{
		this.business_id = business_id;
	}
	
	public void setStreet(String street) 
	{
		this.street = street;
	}
	
	public void setAddress_number(String address_number) 
	{
		this.address_number = address_number;
	}
	
	public void setApt_number(String apt_number) 
	{
		this.apt_number = apt_number;
	}
	
	public void setFloor(String floor) 
	{
		this.floor = floor;
	}
	
	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}
	
	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}
	
	public final void setCity_text(String city_text) 
	{
		this.city_text = city_text;
	}
	
	public final void setAddress_text(String address_text) 
	{
		this.address_text = address_text;
	}
		
	public void setNeighborhood_text(String neighborhood_text) 
	{
		this.neighborhood_text = neighborhood_text;
	}
	
	public void setMx_manzana(String mx_manzana) 
	{
		this.mx_manzana = mx_manzana;
	}
	
	public void setMx_lote(String mx_lote) 
	{
		this.mx_lote = mx_lote;
	}
	
	public void setFirst_street_reference(String first_street_reference) 
	{
		this.first_street_reference = first_street_reference;
	}
	
	public void setSecond_street_reference(String second_street_reference) 
	{
		this.second_street_reference = second_street_reference;
	}
	
	public void setZip_code(String zip_code) 
	{
		this.zip_code = zip_code;
	}
	
	public AddressType getAddress_type() 
	{
		return address_type;
	}

	public void setAddress_type(AddressType address_type) 
	{
		this.address_type = address_type;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public AddressPK getAddressPK() 
	{
		return addressPK;
	}
		
	public Integer getAddress_type_id() 
	{
		return address_type_id;
	}
	
	public Integer getCountry_id() 
	{
		return country_id;
	}
	
	public Integer getState_id() 
	{
		return state_id;
	}
	
	public Integer getTown_id() 
	{
		return town_id;
	}
		
	public String getStreet() 
	{
		return street;
	}
	
	public String getAddress_number() 
	{
		return address_number;
	}
	
	public String getApt_number() 
	{
		return apt_number;
	}
	
	public String getFloor() 
	{
		return floor;
	}
		
	public String getMx_manzana() 
	{
		return mx_manzana;
	}
	
	public String getMx_lote() 
	{
		return mx_lote;
	}
	
	public String getFirst_street_reference() 
	{
		return first_street_reference;
	}
	
	public String getSecond_street_reference() 
	{
		return second_street_reference;
	}
	
	public String getZip_code() 
	{
		return zip_code;
	}

	public Integer getNeighborhood_id() 
	{
		return neighborhood_id;
	}
	
	public Integer getEmployment_id() 
	{
		return employment_id;
	}

	public Integer getBusiness_id() 
	{
		return business_id;
	}
		
	public String getLatitude() 
	{
		return latitude;
	}
	
	public String getLongitude() 
	{
		return longitude;
	}
	
	public Employment getEmployment() 
	{
		return employment;
	}
	
	public Business getBusiness() 
	{
		return business;
	}
	
	public TownCat getTownCat() 
	{
		return townCat;
	}
	
	public StateCat getStateCat() 
	{
		return stateCat;
	}
	
	public NeighborhoodCat getNeighborhood() 
	{
		return neighborhood;
	}
	
	public Country getCountry()
	{
		return country;
	}
	
	public String getNeighborhood_text() 
	{
		return neighborhood_text;
	}
	
	public final Integer getBeneficiarie_id() 
	{
		return beneficiarie_id;
	}
	
	public final String getAddress_text() 
	{
		return address_text;
	}
	
	public final String getCity_text() 
	{
		return city_text;
	}
}
