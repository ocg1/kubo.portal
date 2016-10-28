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
@Table( name = "ln_efl_score" )
public class EflScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int efl_score_id; //` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	@Column
	private int company_id; //` TINYINT(3) NULL AFTER `efl_score_id`,
	@Column
	private int prospectus_id;
	@Column
	private String mx_solicitud_buro; //` VARCHAR(10) NULL,
	@Column
	private String consulting_status; //` VARCHAR(10) NULL,
	@Column
	private String efl_return; //` VARCHAR(700) NULL,
	@Column
	private Date consulting_date; //
	
	
	public int getEfl_score_id() {
		return efl_score_id;
	}
	public void setEfl_score_id(int efl_score_id) {
		this.efl_score_id = efl_score_id;
	}
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public String getConsulting_status() {
		return consulting_status;
	}
	public void setConsulting_status(String consulting_status) {
		this.consulting_status = consulting_status;
	}
	public String getEfl_return() {
		return efl_return;
	}
	public void setEfl_return(String efl_return) {
		this.efl_return = efl_return;
	}
	public Date getConsulting_date() {
		return consulting_date;
	}
	public void setConsulting_date(Date consulting_date) {
		this.consulting_date = consulting_date;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	
}
