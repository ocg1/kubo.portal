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
@Table(name="in_investment_progress_det")
public class InvestmentProgressDet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int investment_progress_det_id;
	@Column
	private int prospectus_investor_id;
	@Column
	private int 	automatic_investment_id; // ` INT NOT NULL,
	@Column
	private Date 	investment_date; // ` DATE NOT NULL,
	@Column
	private String fondeo_kubo_id; //` VARCHAR(45) NOT NULL,
	@Column
	private Integer 	proyect_loan_id; //` INT(10) NULL,
	@Column
	private Integer 	safi_mx_solicitud_id; //` INT(10) NULL,
	@Column
	private String  	safi_credit_id; //` VARCHAR(25) NULL,
	@Column
	private Double 		investment_bite; //` DOUBLE NULL,
	@Column
	private String 		kubo_score; //` VARCHAR(4) NULL,
	@Column
	private Integer 	status_progress_det;//` INT NULL,
	@Column
	private Date 		investment_execute_date; //` DATETIME NULL,
	@Column
	private String		message;
	@Column
	private String		investment_type;
	
	
	public int getAutomatic_investment_id() {
		return automatic_investment_id;
	}
	public void setAutomatic_investment_id(int automatic_investment_id) {
		this.automatic_investment_id = automatic_investment_id;
	}
	public Date getInvestment_date() {
		return investment_date;
	}
	public void setInvestment_date(Date investment_date) {
		this.investment_date = investment_date;
	}
	public String getFondeo_kubo_id() {
		return fondeo_kubo_id;
	}
	public void setFondeo_kubo_id(String fondeo_kubo_id) {
		this.fondeo_kubo_id = fondeo_kubo_id;
	}
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	public Integer getSafi_mx_solicitud_id() {
		return safi_mx_solicitud_id;
	}
	public void setSafi_mx_solicitud_id(Integer safi_mx_solicitud_id) {
		this.safi_mx_solicitud_id = safi_mx_solicitud_id;
	}
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public Double getInvestment_bite() {
		return investment_bite;
	}
	public void setInvestment_bite(Double investment_bite) {
		this.investment_bite = investment_bite;
	}
	public String getKubo_score() {
		return kubo_score;
	}
	public void setKubo_score(String kubo_score) {
		this.kubo_score = kubo_score;
	}
	public Integer getStatus_progress_det() {
		return status_progress_det;
	}
	public void setStatus_progress_det(Integer status_progress_det) {
		this.status_progress_det = status_progress_det;
	}
	public Date getInvestment_execute_date() {
		return investment_execute_date;
	}
	public void setInvestment_execute_date(Date investment_execute_date) {
		this.investment_execute_date = investment_execute_date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getInvestment_type() {
		return investment_type;
	}
	public void setInvestment_type(String investment_type) {
		this.investment_type = investment_type;
	}
	public int getInvestment_progress_det_id() {
		return investment_progress_det_id;
	}
	public void setInvestment_progress_det_id(int investment_progress_det_id) {
		this.investment_progress_det_id = investment_progress_det_id;
	}
	public int getProspectus_investor_id() {
		return prospectus_investor_id;
	}
	public void setProspectus_investor_id(int prospectus_investor_id) {
		this.prospectus_investor_id = prospectus_investor_id;
	}
	
	
}
