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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="gn_selling_detail")
public class SellingDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "selling_type_id", referencedColumnName = "selling_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })	
	private SellingType sellingType;

	@EmbeddedId
	private SellingDetailPK sellingDetailPK;
	
	@Column
	private Double ammount;
	@Column
	private Double ammount_modified;
	@Column
	private Integer prospectus_id_modified;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date datetime_modified;

	public SellingDetail(){
		
	}
	
	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public Double getAmmount_modified() {
		return ammount_modified;
	}

	public void setAmmount_modified(Double ammount_modified) {
		this.ammount_modified = ammount_modified;
	}

	public Integer getProspectus_id_modified() {
		return prospectus_id_modified;
	}

	public void setProspectus_id_modified(Integer prospectus_id_modified) {
		this.prospectus_id_modified = prospectus_id_modified;
	}

	public Date getDatetime_modified() {
		return datetime_modified;
	}

	public void setDatetime_modified(Date datetime_modified) {
		this.datetime_modified = datetime_modified;
	}

	public SellingType getSellingType() {
		return sellingType;
	}

	public void setSellingType(SellingType sellingType) {
		this.sellingType = sellingType;
	}

	public SellingDetailPK getSellingDetailPK() {
		return sellingDetailPK;
	}

	public void setSellingDetailPK(SellingDetailPK sellingDetailPK) {
		this.sellingDetailPK = sellingDetailPK;
	}
	
	
	

}
