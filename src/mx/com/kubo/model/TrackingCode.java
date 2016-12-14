package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pr_tracking_code")
public class TrackingCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String tracking_code; //` VARCHAR(10) NOT NULL,
	@Column
	private String description; //` VARCHAR(500) NULL,
	@Column
	private String enabled; //` VARCHAR(1) NULL,
	@Column
	private String status;
	@Column
	private Date creation_date;
	public String getTracking_code() {
		return tracking_code;
	}
	public void setTracking_code(String tracking_code) {
		this.tracking_code = tracking_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	} 
}
