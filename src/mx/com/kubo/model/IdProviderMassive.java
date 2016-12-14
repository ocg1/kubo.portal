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
@Table(name="pr_id_provider_massive")
public class IdProviderMassive implements Serializable {
	
	private static final long serialVersionUID = 6229038881127317724L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_massive; //` INT NOT NULL AUTO_INCREMENT,
	@Column
	private Integer company_id; //` TINYINT(3) NULL,
	@Column
	private String tracking_code; //` VARCHAR(45) NULL,
	@Column
	private Integer prospectus_id; //` INT(11) NULL,
	@Column
	private Date load_date; //` DATETIME NULL,
	@Column
	private String status; //` VARCHAR(45) NULL,
	@Column
	private Date consulting_date; //` DATETIME NULL,
	@Column
	private String mx_solicitud_buro; //` VARCHAR(10) NULL,
	@Column
	private String bc_score; //` VARCHAR(4) NULL,
	@Column
	private Date send_risk_date; //` DATETIME NULL
	public Integer getId_massive() {
		return id_massive;
	}
	public void setId_massive(Integer id_massive) {
		this.id_massive = id_massive;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public String getTracking_code() {
		return tracking_code;
	}
	public void setTracking_code(String tracking_code) {
		this.tracking_code = tracking_code;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Date getLoad_date() {
		return load_date;
	}
	public void setLoad_date(Date load_date) {
		this.load_date = load_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getConsulting_date() {
		return consulting_date;
	}
	public void setConsulting_date(Date consulting_date) {
		this.consulting_date = consulting_date;
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
	public Date getSend_risk_date() {
		return send_risk_date;
	}
	public void setSend_risk_date(Date send_risk_date) {
		this.send_risk_date = send_risk_date;
	}
	
}
