package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gn_sms_pending_notification")
public class PendingNotification implements Serializable {

	private static final long serialVersionUID = 6229038881127317724L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pending_notification_id; //` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	@Column
	private Integer company_id; //`` TINYINT(3) NULL,
	@Column
	private Integer prospectus_id; //`` INT(11) UNSIGNED NULL,
	@Column
	private Integer event_id; //`` VARCHAR(45) NULL,
	@Column
	private Date pending_date; //`` DATETIME NULL,
	@Column
	private Date notification_date; //`` DATETIME NULL,
	@Column
	private Integer status_id; //`
	
	
	public Integer getPending_notification_id() {
		return pending_notification_id;
	}
	public void setPending_notification_id(Integer pending_notification_id) {
		this.pending_notification_id = pending_notification_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}
	public Date getPending_date() {
		return pending_date;
	}
	public void setPending_date(Date pending_date) {
		this.pending_date = pending_date;
	}
	public Date getNotification_date() {
		return notification_date;
	}
	public void setNotification_date(Date notification_date) {
		this.notification_date = notification_date;
	}
	public Integer getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}
	
}
