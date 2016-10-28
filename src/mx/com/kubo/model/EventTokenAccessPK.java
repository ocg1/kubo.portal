package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventTokenAccessPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id;
	@Column
	private int event_token_id;
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getEvent_token_id() {
		return event_token_id;
	}
	public void setEvent_token_id(int event_token_id) {
		this.event_token_id = event_token_id;
	}
	
}
