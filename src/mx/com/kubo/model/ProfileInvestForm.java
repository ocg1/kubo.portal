package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="in_profile_form")
public class ProfileInvestForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProfileInvestFormPK pk;
	@Column
	private String description;
	@Column
	private String field_number;
	@Column
	private Integer points;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getField_number() {
		return field_number;
	}
	public void setField_number(String field_number) {
		this.field_number = field_number;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public ProfileInvestFormPK getPk() {
		return pk;
	}
	public void setPk(ProfileInvestFormPK pk) {
		this.pk = pk;
	}

	
}
