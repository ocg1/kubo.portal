package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EventPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	int event_id;
	@Column
	int company_id;
	
	public EventPK(){
		
	}

	public EventPK(int event_id, int company_id) {
		super();
		this.event_id = event_id;
		this.company_id = company_id;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
}
