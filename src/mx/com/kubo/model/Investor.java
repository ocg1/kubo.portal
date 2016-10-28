package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="in_investor")
public class Investor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InvestorPK pk;
	
	@Column
	private Date date_published; //', 'datetime', 'YES', '', NULL, ''
	
	@Column
	private int status_id;
	
	@Column
	private Date date_approved; //', 'datetime', 'YES', '', NULL, ''
	
	@Column
	private String mx_ife_domicilio;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
	    })	
	private Membership member;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "status_id", referencedColumnName = "status_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private StatusInv statusinv;

	public InvestorPK getPk() {
		return pk;
	}

	public void setPk(InvestorPK pk) {
		this.pk = pk;
	}

	public Date getDate_published() {
		return date_published;
	}

	public void setDate_published(Date date_published) {
		this.date_published = date_published;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public Date getDate_approved() {
		return date_approved;
	}

	public void setDate_approved(Date date_approved) {
		this.date_approved = date_approved;
	}

	public Membership getMember() {
		return member;
	}

	public void setMember(Membership member) {
		this.member = member;
	}

	public StatusInv getStatusinv() {
		return statusinv;
	}

	public void setStatusinv(StatusInv statusinv) {
		this.statusinv = statusinv;
	}

	public String getMx_ife_domicilio() {
		return mx_ife_domicilio;
	}

	public void setMx_ife_domicilio(String mx_ife_domicilio) {
		this.mx_ife_domicilio = mx_ife_domicilio;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	
	
	
	
}
