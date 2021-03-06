package mx.com.kubo.bean;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.NeighborhoodCat;

public class AddressBean 
implements Serializable
{
	private static final long serialVersionUID = -6855187320648723751L;

	private Address address;
	
	private String zip_code;
	private String street;
	private String address_number;
	private String apt_number;
	private String mx_manzana;
	private String mx_lote;
	private String townName;
	private String stateName;
	private String neighborhood_text; 
	private String first_street_reference;
	private String second_street_reference;
	private String description;
	private String latitude;
	private String longitude;
	private String latLong;
	private String neighborhood_id_ENABLED   = "block";
	private String neighborhood_text_ENABLED = "none";
	private Integer beneficiario_id;
	
	private Integer town_id;
	private Integer state_id;
	private Integer neighborhood_id;
	
	private List<NeighborhoodCat> listNeighborhood;
	

	public List<NeighborhoodCat> getListNeighborhood() {
		return listNeighborhood;
	}

	public void setListNeighborhood(List<NeighborhoodCat> listNeighborhood) {
		this.listNeighborhood = listNeighborhood;
	}

	public String getNeighborhood_text() {
		return neighborhood_text;
	}

	public void setNeighborhood_text(String neighborhood_text) {
		this.neighborhood_text = neighborhood_text;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getNeighborhood_text_ENABLED() {
		return neighborhood_text_ENABLED;
	}

	public void setNeighborhood_text_ENABLED(String neighborhood_text_ENABLED) {
		this.neighborhood_text_ENABLED = neighborhood_text_ENABLED;
	}

	public String getNeighborhood_id_ENABLED() {
		return neighborhood_id_ENABLED;
	}

	public void setNeighborhood_id_ENABLED(String neighborhood_id_ENABLED) {
		this.neighborhood_id_ENABLED = neighborhood_id_ENABLED;
	}

	public String getTownName() {
		return townName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public Integer getTown_id() {
		return town_id;
	}

	public Integer getState_id() {
		return state_id;
	}

	public Integer getNeighborhood_id() {
		return neighborhood_id;
	}

	public void setTown_id(Integer town_id) {
		this.town_id = town_id;
	}

	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	public void setNeighborhood_id(Integer neighborhood_id) {
		this.neighborhood_id = neighborhood_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getAddress_number() {
		return address_number;
	}

	public String getFirst_street_reference() {
		return first_street_reference;
	}

	public void setFirst_street_reference(String first_street_reference) {
		this.first_street_reference = first_street_reference;
	}

	public String getSecond_street_reference() {
		return second_street_reference;
	}

	public void setSecond_street_reference(String second_street_reference) {
		this.second_street_reference = second_street_reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAddress_number(String address_number) {
		this.address_number = address_number;
	}

	public String getApt_number() {
		return apt_number;
	}

	public void setApt_number(String apt_number) {
		this.apt_number = apt_number;
	}

	public String getMx_manzana() {
		return mx_manzana;
	}

	public void setMx_manzana(String mx_manzana) {
		this.mx_manzana = mx_manzana;
	}

	public String getMx_lote() {
		return mx_lote;
	}

	public void setMx_lote(String mx_lote) {
		this.mx_lote = mx_lote;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

	public Integer getBeneficiario_id() {
		return beneficiario_id;
	}

	public void setBeneficiario_id(Integer beneficiario_id) {
		this.beneficiario_id = beneficiario_id;
	}
}
