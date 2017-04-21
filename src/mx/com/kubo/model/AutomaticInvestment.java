package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table( name = "in_automatic_investment" )
public class AutomaticInvestment implements Serializable 
{
	private static final long serialVersionUID = 2836618487083272042L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int automatic_investment_id; // ` int(11) NOT NULL AUTO_INCREMENT,
	@Column
	private int company_id; //` tinyint(3) DEFAULT NULL,
	@Column
	private int prospectus_id; //` int(10) DEFAULT NULL,
	@Column
	private String safi_client_id; //` varchar(10) DEFAULT NULL,
	@Column
	private String safi_account_id; //` varchar(12) DEFAULT NULL,
	@Column
	private String filter_label; //` varchar(45) DEFAULT NULL,
	@Column
	private String inscription_date; //` varchar(45) DEFAULT NULL,
	@Column
	private String filter; //` text,
	@Column
	private String frequency; //` varchar(1) DEFAULT NULL,
	@Column
	private Date next_investment; //` date DEFAULT NULL,
	@Column
	private Date next_investment_apply; //` date DEFAULT NULL,
	@Column
	private Date last_investment; //` datetime DEFAULT NULL,
	@Column
	private String is_active; //` varchar(1) DEFAULT NULL,
	@Column
	private Date finished_date; //` datetime DEFAULT NULL,
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id",    insertable = false, updatable = false)
	}) private Membership membership;
	
	public int getAutomatic_investment_id() {
		return automatic_investment_id;
	}
	public void setAutomatic_investment_id(int automatic_investment_id) {
		this.automatic_investment_id = automatic_investment_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getSafi_client_id() {
		return safi_client_id;
	}
	public void setSafi_client_id(String safi_client_id) {
		this.safi_client_id = safi_client_id;
	}
	public String getSafi_account_id() {
		return safi_account_id;
	}
	public void setSafi_account_id(String safi_account_id) {
		this.safi_account_id = safi_account_id;
	}
	public String getFilter_label() {
		return filter_label;
	}
	public void setFilter_label(String filter_label) {
		this.filter_label = filter_label;
	}
	public String getInscription_date() {
		return inscription_date;
	}
	public void setInscription_date(String inscription_date) {
		this.inscription_date = inscription_date;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Date getNext_investment() {
		return next_investment;
	}
	public void setNext_investment(Date next_investment) {
		this.next_investment = next_investment;
	}
	public Date getNext_investment_apply() {
		return next_investment_apply;
	}
	public void setNext_investment_apply(Date next_investment_apply) {
		this.next_investment_apply = next_investment_apply;
	}
	public Date getLast_investment() {
		return last_investment;
	}
	public void setLast_investment(Date last_investment) {
		this.last_investment = last_investment;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public Membership getMembership() {
		return membership;
	}
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	public Date getFinished_date() {
		return finished_date;
	}
	public void setFinished_date(Date finished_date) {
		this.finished_date = finished_date;
	}
	
}
