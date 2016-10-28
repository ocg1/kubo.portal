package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "gn_phone")
public class Phone 
implements Serializable 
{
	private static final long serialVersionUID = 2159788310305886457L;
	
	@EmbeddedId private PhonePK phonePk;
	
	@Column private String phone_number;
	@Column private String extension;
	@Column private String owned;
	
	@Column private Integer phone_type_id;
	@Column private Integer employment_id;
	@Column private Integer business_id;
	
	@Column private Character	area;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "phone_type_id", referencedColumnName = "phone_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false)
	}) private PhoneType phoneType;


	public PhonePK getPhonePk() {
		return phonePk;
	}

	public void setPhonePk(PhonePK phonePk) {
		this.phonePk = phonePk;
	}

	public Integer getPhone_type_id() {
		return phone_type_id;
	}

	public void setPhone_type_id(Integer phone_type_id) {
		this.phone_type_id = phone_type_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getOwned() {
		return owned;
	}

	public void setOwned(String owned) {
		this.owned = owned;
	}

	public Integer getEmployment_id() {
		return employment_id;
	}

	public void setEmployment_id(Integer employment_id) {
		this.employment_id = employment_id;
	}

	public Integer getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(Integer business_id) {
		this.business_id = business_id;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public Character getArea() {
		return area;
	}

	public void setArea(Character area) {
		this.area = area;
	}	

}
