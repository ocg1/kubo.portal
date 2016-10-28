package mx.com.kubo.model.mesa.solicitud;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.com.kubo.model.NaturalPerson;

@Entity @Table(name = "pr_pld_notification")
public class PldNotification 
implements Serializable
{
	private static final long serialVersionUID = -2341955546552686657L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pld_notification_id;
	
	@Column @Temporal(TemporalType.TIMESTAMP)
	private Date notification_date;
	
	@OneToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false)
	        
	}) private NaturalPerson person;
	
	@Column private int company_id;
	
	@Column private Integer prospectus_id;
	@Column private Integer prospectus_id_viewed;
	@Column private Integer unusual_behavior_id;
		
	@Column private String comments;

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public NaturalPerson getPerson() {
		return person;
	}

	public void setPerson(NaturalPerson person) {
		this.person = person;
	}

	public int getPld_notification_id() {
		return pld_notification_id;
	}

	public void setPld_notification_id(int pld_notification_id) {
		this.pld_notification_id = pld_notification_id;
	}

	public Date getNotification_date() {
		return notification_date;
	}

	public void setNotification_date(Date notification_date) {
		this.notification_date = notification_date;
	}

	public Integer getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public Integer getProspectus_id_viewed() {
		return prospectus_id_viewed;
	}

	public void setProspectus_id_viewed(Integer prospectus_id_viewed) {
		this.prospectus_id_viewed = prospectus_id_viewed;
	}

	public Integer getUnusual_behavior_id() {
		return unusual_behavior_id;
	}

	public void setUnusual_behavior_id(Integer unusual_behavior_id) {
		this.unusual_behavior_id = unusual_behavior_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
