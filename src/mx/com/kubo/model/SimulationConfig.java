package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ln_simulation_config")
public class SimulationConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int simulation_config_id;
	@Column
	private String loan_type_id;
	@Column 
	private String partner_id;
	@Column
	private Integer company_id;
	@Column
	private float max_amount;
	@Column
	private float min_amount;
	@Column
	private Integer max_term;
	@Column
	private Integer min_term;
	@Column
	private String area;
	public int getSimulation_config_id() {
		return simulation_config_id;
	}
	public void setSimulation_config_id(int simulation_config_id) {
		this.simulation_config_id = simulation_config_id;
	}
	public String getLoan_type_id() {
		return loan_type_id;
	}
	public void setLoan_type_id(String loan_type_id) {
		this.loan_type_id = loan_type_id;
	}
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public float getMax_amount() {
		return max_amount;
	}
	public void setMax_amount(float max_amount) {
		this.max_amount = max_amount;
	}
	public float getMin_amount() {
		return min_amount;
	}
	public void setMin_amount(float min_amount) {
		this.min_amount = min_amount;
	}
	public Integer getMax_term() {
		return max_term;
	}
	public void setMax_term(Integer max_term) {
		this.max_term = max_term;
	}
	public Integer getMin_term() {
		return min_term;
	}
	public void setMin_term(Integer min_term) {
		this.min_term = min_term;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "SimulationConfig [simulation_config_id=" + simulation_config_id
				+ ", loan_type_id=" + loan_type_id + ", partner_id="
				+ partner_id + ", company_id=" + company_id + ", max_amount="
				+ max_amount + ", min_amount=" + min_amount + ", max_term="
				+ max_term + ", min_term=" + min_term + ", area=" + area + "]";
	}
	
	
	
		
}
