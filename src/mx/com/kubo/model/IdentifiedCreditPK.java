package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdentifiedCreditPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int  identified_credit_id;
	@Column
	private int company_id;
	@Column
	private int prospectus_id;
	@Column
	private String original_entity; 
	@Column
	private String frequency; 
	@Column
	private String total_payments;
	@Column
	private Date start_date;
	
	
	public int getIdentified_credit_id() {
		return identified_credit_id;
	}
	public void setIdentified_credit_id(int identified_credit_id) {
		this.identified_credit_id = identified_credit_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getOriginal_entity() {
		return original_entity;
	}
	public void setOriginal_entity(String original_entity) {
		this.original_entity = original_entity;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getTotal_payments() {
		return total_payments;
	}
	public void setTotal_payments(String total_payments) {
		this.total_payments = total_payments;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
