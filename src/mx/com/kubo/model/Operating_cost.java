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
@Table(name="gn_operating_cost")
public class Operating_cost implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "operating_cost_type_id", referencedColumnName = "operating_cost_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })	
	private Operating_cost_type operaCostType;
	
	@EmbeddedId
	private Operating_costPK operatingCostPK;
	
	@Column
	private Double	ammount;
	@Column
	private Double  ammount_modified;
	@Column
	private Integer prospectus_id_modified;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date 	datetime_modified;
	
	
	public Operating_cost(){
		
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

	public Operating_cost_type getOperaCostType() {
		return operaCostType;
	}

	public void setOperaCostType(Operating_cost_type operaCostType) {
		this.operaCostType = operaCostType;
	}


	public Operating_costPK getOperatingCostPK() {
		return operatingCostPK;
	}


	public void setOperatingCostPK(Operating_costPK operatingCostPK) {
		this.operatingCostPK = operatingCostPK;
	}
	
	
	

}
