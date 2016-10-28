package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gn_form_analytics")
public class FormAnalytics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(unique=true,updatable=false,insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long analytics_id;
	 
	@Column
	private String  google_id; //` VARCHAR(50) NOT NULL,
	@Column
	private Integer prospectus_id; //` INT(10) UNSIGNED NULL,
	@Column
	private String  analytics_str; //` TEXT NULL,
	
	public Long getAnalytics_id() {
		return analytics_id;
	}
	public void setAnalytics_id(Long analytics_id) {
		this.analytics_id = analytics_id;
	}
	public String getGoogle_id() {
		return google_id;
	}
	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getAnalytics_str() {
		return analytics_str;
	}
	public void setAnalytics_str(String analytics_str) {
		this.analytics_str = analytics_str;
	}
	
	
	
}
