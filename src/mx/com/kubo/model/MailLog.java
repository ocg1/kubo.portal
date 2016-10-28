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

@Entity @Table(name="pr_mail_log")
public class MailLog 
implements Serializable
{
	private static final long serialVersionUID = 39624514374265471L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mail_log_id;
	
	@Column private Integer prospectus_id_sender;
	@Column private Integer prospectus_id;
	@Column private Integer prospectus_id_viewed;
	@Column private Integer event_id;
	@Column private Integer status;
	@Column private Integer company_id;
	@Column private Integer account_type_id;
	
	@Column private String email_to;
	@Column private String send_type;
	@Column private String exception;
		
	@Column @Temporal(TemporalType.TIMESTAMP)
	private Date email_date;

	public Integer getMail_log_id() {
		return mail_log_id;
	}

	public void setMail_log_id(Integer mail_log_id) {
		this.mail_log_id = mail_log_id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getAccount_type_id() {
		return account_type_id;
	}

	public void setAccount_type_id(Integer account_type_id) {
		this.account_type_id = account_type_id;
	}

	public String getEmail_to() {
		return email_to;
	}

	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Date getEmail_date() {
		return email_date;
	}

	public void setEmail_date(Date email_date) {
		this.email_date = email_date;
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

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
}
