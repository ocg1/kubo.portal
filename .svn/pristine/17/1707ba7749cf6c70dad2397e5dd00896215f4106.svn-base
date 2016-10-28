package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ComparisonModPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int comparison_id;
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	
	
	
	
	public ComparisonModPK(int comparison_id, int prospectus_id, int company_id) {
		super();
		this.comparison_id = comparison_id;
		this.prospectus_id = prospectus_id;
		this.company_id = company_id;
	}
	
	
	public ComparisonModPK() {
		super();
	}


	public int getComparison_id() {
		return comparison_id;
	}
	public void setComparison_id(int comparison_id) {
		this.comparison_id = comparison_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
	
}
