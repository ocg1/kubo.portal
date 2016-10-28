package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.kubo.model.catalogos.Channel;


@Entity
@Table(name = "gn_registration_reason")
public class RegistrationReason 
implements Serializable 
{
	private static final long serialVersionUID = -4917977884955917334L;

	@EmbeddedId private RegistrationReasonPK regPK;
	
	@Column private String name;        //	varchar(60)
	@Column private String partner_id;  //varchar (3)
	@Column private String channel_id;  //varchar (3)
	
	@Column private Character is_active;  //VarCHAR (1)
	
	private int order_list;
		
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "partner_id", referencedColumnName = "partner_id", insertable = false, updatable = false),
			@JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	}) private Partner partner;
	
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "channel_id", referencedColumnName = "channel_id", insertable = false, updatable = false),
			@JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	}) private Channel channel;
	
	
	public RegistrationReasonPK getRegPK() 
	{
		return regPK;
	}
	
	public void setRegPK(RegistrationReasonPK regPK)
	{
		this.regPK = regPK;
	}
	
	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPartner_id() {
		return partner_id;
	}
	
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	
	public Partner getPartner() {
		return partner;
	}
	
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	
	public final Character getIs_active() {
		return is_active;
	}
	
	public final int getOrder_list() {
		return order_list;
	}
	
	public final void setIs_active(Character is_active) {
		this.is_active = is_active;
	}
	
	public final void setOrder_list(int order) {
		this.order_list = order;
	}
}
