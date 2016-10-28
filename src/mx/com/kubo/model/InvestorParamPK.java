package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InvestorParamPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id; //` TINYINT(3) NOT NULL,
	@Column
	private int prospectus_id; // ` INT(11) NOT NULL,
	@Column
	private int investment_param_id; //
	
	
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
	public int getInvestment_param_id() {
		return investment_param_id;
	}
	public void setInvestment_param_id(int investment_param_id) {
		this.investment_param_id = investment_param_id;
	}
	
}
