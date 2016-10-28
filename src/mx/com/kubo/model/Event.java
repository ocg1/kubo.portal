package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "gn_event")
public class Event implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	EventPK eventPK;
	@Column
	String description;
	@Column
	char is_send_to_promotor;
	
	
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id", insertable = false, updatable = false),
			@JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	})
	private AccountType accountType;
	
	public Event(){
		
	}

	public Event(EventPK eventPK, String descrition, char is_send_to_promotor) {
		super();
		this.eventPK = eventPK;
		this.description = descrition;
		this.is_send_to_promotor = is_send_to_promotor;
	}

	public EventPK getEventPK() {
		return eventPK;
	}

	public void setEventPK(EventPK eventPK) {
		this.eventPK = eventPK;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getIs_send_to_promotor() {
		return is_send_to_promotor;
	}

	public void setIs_send_to_promotor(char is_send_to_promotor) {
		this.is_send_to_promotor = is_send_to_promotor;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
}
