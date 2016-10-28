package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ln_alert_event")
public class AlertEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer alert_event_id;
	@Column
	private int company_id;
	@Column
	private Integer proyect_loan_id;
	@Column
	private Integer proyect_id;
	@Column
	private Integer prospectus_id_sender;
	@Column
	private Integer prospectus_id_viewed;
	@Column
	private Date creation_date;
	@Column
	private String note;
	@Column
	private Integer alert_id;
	@Column
	private Integer alert_motive_id;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id_sender", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
	})	
	
	private NaturalPerson person_sender;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false),
	        @JoinColumn(name = "alert_id", referencedColumnName = "alert_id", insertable = false, updatable = false)
	})	
	
	private Alert alert;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=AlertMotive.class )
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false),
	        @JoinColumn(name = "alert_id", referencedColumnName = "alert_id", insertable = false, updatable = false),
	        @JoinColumn(name = "alert_motive_id", referencedColumnName = "alert_motive_id", insertable = false, updatable = false)
	})	
	private AlertMotive alertmotive;
	
	
	public int getAlert_event_id() {
		return alert_event_id;
	}
	public void setAlert_event_id(Integer alert_event_id) {
		this.alert_event_id = alert_event_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	public Integer getProyect_id() {
		return proyect_id;
	}
	public void setProyect_id(Integer proyect_id) {
		this.proyect_id = proyect_id;
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
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getAlert_id() {
		return alert_id;
	}
	public void setAlert_id(Integer alert_id) {
		this.alert_id = alert_id;
	}
	public Integer getAlert_motive_id() {
		return alert_motive_id;
	}
	public void setAlert_motive_id(Integer alert_motive_id) {
		this.alert_motive_id = alert_motive_id;
	}
	public NaturalPerson getPerson_sender() {
		return person_sender;
	}
	public void setPerson_sender(NaturalPerson person_sender) {
		this.person_sender = person_sender;
	}
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	public AlertMotive getAlertmotive() {
		return alertmotive;
	}
	public void setAlertmotive(AlertMotive alertmotive) {
		this.alertmotive = alertmotive;
	}


}
