package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gn_promotor")
public class Promotor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PromotorPK promotorPk;
	
	private Integer prospectus_id;
	
	@Column private String name;
	@Column private String code_id;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "prospectus_id", referencedColumnName = "prospectus_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })
	private Membership membership;
	

	public PromotorPK getPromotorPk() {
		return promotorPk;
	}

	public void setPromotorPk(PromotorPK promotorPk) {
		this.promotorPk = promotorPk;
	}

	public Integer getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode_id() {
		return code_id;
	}

	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	

}
