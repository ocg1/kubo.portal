package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ln_fraud_detection")
public class FraudeDetection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int 		fraude_detection_id; //` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	@Column 
	 private Integer	company_id;//` VARCHAR(45) NULL,
	@Column 
	 private Integer	prospectus_id;//` VARCHAR(45) NULL,
	@Column 
	 private Integer 	fraude_type_id;//` INT NULL,
	@Column 
	 private Date 		creation_date;//` DATETIME NULL,
	@Column 
	 private String 	description_note;//` TEXT NULL,
	@Column 
	 private String 	severity;//
	
	
	public int getFraude_detection_id() {
		return fraude_detection_id;
	}
	public void setFraude_detection_id(int fraude_detection_id) {
		this.fraude_detection_id = fraude_detection_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getFraude_type_id() {
		return fraude_type_id;
	}
	public void setFraude_type_id(Integer fraude_type_id) {
		this.fraude_type_id = fraude_type_id;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getDescription_note() {
		return description_note;
	}
	public void setDescription_note(String description_note) {
		this.description_note = description_note;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	
	
	
	
}
