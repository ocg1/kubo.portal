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
@Table(name="ln_ticket_config")
public class TicketConfig implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TicketConfigPK pk;
	@Column
	private String safi_description;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "ticket_title_id", referencedColumnName = "ticket_title_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private TicketTitle tickettitle;
	
	public TicketConfigPK getPk() {
		return pk;
	}
	public void setPk(TicketConfigPK pk) {
		this.pk = pk;
	}
	public String getSafi_description() {
		return safi_description;
	}
	public void setSafi_description(String safi_description) {
		this.safi_description = safi_description;
	}
	public TicketTitle getTickettitle() {
		return tickettitle;
	}
	public void setTickettitle(TicketTitle tickettitle) {
		this.tickettitle = tickettitle;
	}
	
}
