package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="in_profile_value")
public class ProfileFormValue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProfileFormValuePK pk;
	@Column
	private Date last_update;
	@Column
	private String value;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "profile_form_id", referencedColumnName = "profile_form_id", insertable = false, updatable = false)
	    })	
	private ProfileInvestForm profile_form;
	
	public Date getLast_update() {
		return last_update;
	}
	
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public ProfileFormValuePK getPk() {
		return pk;
	}

	public void setPk(ProfileFormValuePK pk) {
		this.pk = pk;
	}

	public ProfileInvestForm getProfile_form() {
		return profile_form;
	}

	public void setProfile_form(ProfileInvestForm profile_form) {
		this.profile_form = profile_form;
	}

}
