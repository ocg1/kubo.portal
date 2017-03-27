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
@Table(name="pr_client_notification")
public class ClientNotification implements Serializable {

	private static final long serialVersionUID = 6229038881127317724L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int 		client_notification_id; //	int(10) unsigned
	@Column
	private Integer 	notification_type_id; //		int(11)
	@Column
	private Integer 	prospectus_id; //		int(11)
	@Column
	private Integer 	prospectus_id_coach; //		int(11)
	@Column
	private Integer 	assign_coach_status	; //	int(11)
	@Column
	private Integer 	status_mail_notification; //		int(11)
	@Column
	private Integer 	failed_mail_notification_attempt; //		int(11)
	@Column
	private Integer 	status_sms_notification	; //	int(11)
	@Column
	private Integer 	failed_sms_notification_attempt	; //	int(11)
	@Column
	private Date creation_date;
	@Column
	private Date date_assign_coach;
	@Column
	private Date mail_notification_date;
	@Column
	private Date sms_notification_date;
	
	public int getClient_notification_id() {
		return client_notification_id;
	}
	public void setClient_notification_id(int client_notification_id) {
		this.client_notification_id = client_notification_id;
	}
	public Integer getNotification_type_id() {
		return notification_type_id;
	}
	public void setNotification_type_id(Integer notification_type_id) {
		this.notification_type_id = notification_type_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getProspectus_id_coach() {
		return prospectus_id_coach;
	}
	public void setProspectus_id_coach(Integer prospectus_id_coach) {
		this.prospectus_id_coach = prospectus_id_coach;
	}
	public Integer getAssign_coach_status() {
		return assign_coach_status;
	}
	public void setAssign_coach_status(Integer assign_coach_status) {
		this.assign_coach_status = assign_coach_status;
	}
	public Integer getStatus_mail_notification() {
		return status_mail_notification;
	}
	public void setStatus_mail_notification(Integer status_mail_notification) {
		this.status_mail_notification = status_mail_notification;
	}
	public Integer getFailed_mail_notification_attempt() {
		return failed_mail_notification_attempt;
	}
	public void setFailed_mail_notification_attempt(Integer failed_mail_notification_attempt) {
		this.failed_mail_notification_attempt = failed_mail_notification_attempt;
	}
	public Integer getStatus_sms_notification() {
		return status_sms_notification;
	}
	public void setStatus_sms_notification(Integer status_sms_notification) {
		this.status_sms_notification = status_sms_notification;
	}
	public Integer getFailed_sms_notification_attempt() {
		return failed_sms_notification_attempt;
	}
	public void setFailed_sms_notification_attempt(Integer failed_sms_notification_attempt) {
		this.failed_sms_notification_attempt = failed_sms_notification_attempt;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Date getDate_assign_coach() {
		return date_assign_coach;
	}
	public void setDate_assign_coach(Date date_assign_coach) {
		this.date_assign_coach = date_assign_coach;
	}
	public Date getMail_notification_date() {
		return mail_notification_date;
	}
	public void setMail_notification_date(Date mail_notification_date) {
		this.mail_notification_date = mail_notification_date;
	}
	public Date getSms_notification_date() {
		return sms_notification_date;
	}
	public void setSms_notification_date(Date sms_notification_date) {
		this.sms_notification_date = sms_notification_date;
	}

	

	
}
