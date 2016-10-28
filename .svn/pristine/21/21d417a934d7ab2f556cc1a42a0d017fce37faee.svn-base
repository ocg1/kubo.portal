package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "gn_event_notification")
public class EventNotification 
{
	@EmbeddedId
	private EventNotificationPK pk;
	
	@Column
	private String comments;//	varchar(45)
	
	@Column
	private String mail_to;//	varchar(5)
	
	@ManyToOne
	@JoinColumns
	(value = {
		@JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	    @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false)
	})
	
	private Membership membership;
	
	@ManyToOne
	@JoinColumns
	(value = {
		@JoinColumn (name = "event_id", referencedColumnName = "event_id", insertable = false, updatable = false),
		@JoinColumn (name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	})
	
	private Event event;
	
	public EventNotificationPK getPk() {
		return pk;
	}
	
	public void setPk(EventNotificationPK pk) {
		this.pk = pk;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getMail_to() {
		return mail_to;
	}
	
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	
	public Membership getMembership() {
		return membership;
	}
	
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
}
