package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_operating_cost_history")
public class OperationCostHistory implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	  private OperationCostHistoryPK pk;
	@Column
	  private Double ammount;
	@Column
	  private Double ammount_modified;
	@Column
	  private Integer prospectus_id_modified;
	@Column
	  private Date datetime_modified;
	@Column
	  private Date creation_date;
	
	public OperationCostHistoryPK getPk() {
		return pk;
	}
	public void setPk(OperationCostHistoryPK pk) {
		this.pk = pk;
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
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
}
