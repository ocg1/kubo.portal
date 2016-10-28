package mx.com.kubo.model.mesa.solicitud.busqueda;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "view_prospect_full_name_for_coach")
public class UserViewFullNameForCoach 
implements Serializable
{
	private static final long serialVersionUID = 1183548412534433368L;

	@Id 
	@Column private Integer prospectus_id;
	@Column private Integer promotor_id;
	
	@Column private String email;
	@Column private String full_name;
	@Column private String tracking_id;
	@Column private String area;	

	public Integer getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public Integer getPromotor_id() {
		return promotor_id;
	}

	public void setPromotor_id(Integer promotor_id) {
		this.promotor_id = promotor_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getTracking_id() {
		return tracking_id;
	}

	public void setTracking_id(String tracking_id) {
		this.tracking_id = tracking_id;
	}
	
	public final String getArea() {
		return area;
	}

	public final void setArea(String area) {
		this.area = area;
	}
}
