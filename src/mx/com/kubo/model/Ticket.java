package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ln_ticket")
public class Ticket implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TicketPk ticketpk;
	@Column
	private String safi_credit_id;
	@Column
	private Integer payment_number;
	@Column
	private Date generation_date;
	@Column
	private String status;
	
	public TicketPk getTicketpk() {
		return ticketpk;
	}
	public void setTicketpk(TicketPk ticketpk) {
		this.ticketpk = ticketpk;
	}
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public Integer getPayment_number() {
		return payment_number;
	}
	public void setPayment_number(Integer payment_number) {
		this.payment_number = payment_number;
	}
	public Date getGeneration_date() {
		return generation_date;
	}
	public void setGeneration_date(Date generation_date) {
		this.generation_date = generation_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
