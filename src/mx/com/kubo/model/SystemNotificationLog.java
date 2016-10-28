package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name = "pr_system_notification_log")
public class SystemNotificationLog 
implements Serializable
{
	private static final long serialVersionUID = -4856518043901547367L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int notification_log_id;
	
	@Column private Integer company_id;
	@Column private Integer event_id;
	@Column private Integer prospectus_id;
	@Column private Integer prospectus_id_sender;	
	@Column private Integer prospectus_id_viewed;
		
	@Column @Temporal(TemporalType.TIMESTAMP)
	private Date notification_date;
	
	@Column private String comments;

	public int getNotification_log_id() {
		return notification_log_id;
	}

	public void setNotification_log_id(int notification_log_id) {
		this.notification_log_id = notification_log_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	public Integer getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public Integer getProspectus_id_sender() {
		return prospectus_id_sender;
	}

	public void setProspectus_id_sender(Integer prospectus_id_sender) {
		this.prospectus_id_sender = prospectus_id_sender;
	}

	public Integer getProspectus_id_viewed() {
		return prospectus_id_viewed;
	}

	public void setProspectus_id_viewed(Integer prospectus_id_viewed) {
		this.prospectus_id_viewed = prospectus_id_viewed;
	}

	public Date getNotification_date() {
		return notification_date;
	}

	public void setNotification_date(Date notification_date) {
		this.notification_date = notification_date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
