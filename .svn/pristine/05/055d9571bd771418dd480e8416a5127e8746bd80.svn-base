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
@Table(name="gn_prospector")
public class Prospector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prospector_id; //` INT NOT NULL AUTO_INCREMENT,
	@Column
	private int company_id; // TINYINT(3) NULL,
	@Column
	private int prospectus_id; //` INT(10) NULL,
	@Column
	private String mx_solicitud_buro; //` VARCHAR(10) NULL,
	@Column
	private String bc_score; //` VARCHAR(4) NULL,
	@Column
	private String is_valid; //` VARCHAR(1) NULL,
	@Column
	private String is_active; //` VARCHAR(1) NULL,
	@Column
	private String is_processed; //` VARCHAR(1) NULL,
	@Column
	private Date consulting_date; //` VARCHAR(1) NULL,
	
	public int getProspector_id() {
		return prospector_id;
	}
	public void setProspector_id(int prospector_id) {
		this.prospector_id = prospector_id;
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
	public String getMx_solicitud_buro() {
		return mx_solicitud_buro;
	}
	public void setMx_solicitud_buro(String mx_solicitud_buro) {
		this.mx_solicitud_buro = mx_solicitud_buro;
	}
	public String getBc_score() {
		return bc_score;
	}
	public void setBc_score(String bc_score) {
		this.bc_score = bc_score;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public String getIs_processed() {
		return is_processed;
	}
	public void setIs_processed(String is_processed) {
		this.is_processed = is_processed;
	}
	public Date getConsulting_date() {
		return consulting_date;
	}
	public void setConsulting_date(Date consulting_date) {
		this.consulting_date = consulting_date;
	}
	
}
