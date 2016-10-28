package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ln_loan_info")
public class ProyectInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	@Id
	private Integer proyect_loan_id; //` INT(10) UNSIGNED NOT NULL,
	@Column
	private Integer prospectus_id; //` INT(10) UNSIGNED NULL,
	@Column
	private String safi_credit_id; //` VARCHAR(25) NULL,
	@Column
	private Double min_investment_bite;
	
	
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public Double getMin_investment_bite() {
		return min_investment_bite;
	}
	public void setMin_investment_bite(Double min_investment_bite) {
		this.min_investment_bite = min_investment_bite;
	}
	
}
