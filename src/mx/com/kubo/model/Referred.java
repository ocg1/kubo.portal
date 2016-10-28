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
@Table( name = "gn_referred" )
public class Referred implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReferredPK pk;
	
	@Column
	private Date date_referred;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "prospectus_id_origin", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private Membership membership;

	public ReferredPK getPk() {
		return pk;
	}

	public void setPk(ReferredPK pk) {
		this.pk = pk;
	}

	public Date getDate_referred() {
		return date_referred;
	}

	public void setDate_referred(Date date_referred) {
		this.date_referred = date_referred;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
}