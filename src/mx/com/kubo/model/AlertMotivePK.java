package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AlertMotivePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int alert_id;
	@Column
	private int company_id;
	@Column
	private int alert_motive_id;
	
	public int getAlert_id() {
		return alert_id;
	}
	public void setAlert_id(int alert_id) {
		this.alert_id = alert_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getAlert_motive_id() {
		return alert_motive_id;
	}
	public void setAlert_motive_id(int alert_motive_id) {
		this.alert_motive_id = alert_motive_id;
	}
	
}
