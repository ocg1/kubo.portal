package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ln_related_person_loan")
public class RelatedPersonLoan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//`company_id` TINYINT(3) UNSIGNED NOT NULL,
	//`related_person_loan_id` INT UNSIGNED NOT NULL,
	
	@EmbeddedId
	private RelatedPersonLoanPK pk;
	@Column
	private Integer 	prospectus_id;//` INT(10) UNSIGNED NULL,
	@Column
	private Integer 	proyect_loan_id;//` INT(10) UNSIGNED NULL,
	@Column
	private String 		authorization_status;//` VARCHAR(45) NULL,
	@Column
	private Date 		authorization_date;//` DATETIME NULL;//,
	@Column
	private String 		authorization_file_location;//` VARCHAR(50) NULL,
	@Column
	private Date 		notification_date;//` DATETIME NULL,
	@Column
	private String 		authorization_type;//` VARCHAR(50) NULL,
	
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	public String getAuthorization_status() {
		return authorization_status;
	}
	public void setAuthorization_status(String authorization_status) {
		this.authorization_status = authorization_status;
	}
	public Date getAuthorization_date() {
		return authorization_date;
	}
	public void setAuthorization_date(Date authorization_date) {
		this.authorization_date = authorization_date;
	}
	public String getAuthorization_file_location() {
		return authorization_file_location;
	}
	public void setAuthorization_file_location(String authorization_file_location) {
		this.authorization_file_location = authorization_file_location;
	}
	public Date getNotification_date() {
		return notification_date;
	}
	public void setNotification_date(Date notification_date) {
		this.notification_date = notification_date;
	}
	public RelatedPersonLoanPK getPk() {
		return pk;
	}
	public void setPk(RelatedPersonLoanPK pk) {
		this.pk = pk;
	}
	public String getAuthorization_type() {
		return authorization_type;
	}
	public void setAuthorization_type(String authorization_type) {
		this.authorization_type = authorization_type;
	}
	
	
	
}
