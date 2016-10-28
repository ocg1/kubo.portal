package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ln_ticket_title")
public class TicketTitle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TicketTitlePK pk;
	@Column
	private String name;
	@Column
	private Integer order_title;
	
	public TicketTitlePK getPk() {
		return pk;
	}
	public void setPk(TicketTitlePK pk) {
		this.pk = pk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder_title() {
		return order_title;
	}
	public void setOrder_title(Integer order_title) {
		this.order_title = order_title;
	}
	
	
	

}
