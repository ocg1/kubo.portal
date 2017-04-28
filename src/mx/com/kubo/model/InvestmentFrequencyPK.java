package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class InvestmentFrequencyPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int company_id;
	private int investment_frequency_id;
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getInvestment_frequency_id() {
		return investment_frequency_id;
	}
	public void setInvestment_frequency_id(int investment_frequency_id) {
		this.investment_frequency_id = investment_frequency_id;
	}
	
}
