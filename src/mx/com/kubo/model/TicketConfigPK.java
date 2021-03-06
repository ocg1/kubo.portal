package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TicketConfigPK implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int ticket_title_id;
	@Column
	private int company_id;
	@Column
	private int safi_code;
	
	public int getTicket_title_id() {
		return ticket_title_id;
	}
	
	public void setTicket_title_id(int ticket_title_id) {
		this.ticket_title_id = ticket_title_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getSafi_code() {
		return safi_code;
	}
	public void setSafi_code(int safi_code) {
		this.safi_code = safi_code;
	}

}
