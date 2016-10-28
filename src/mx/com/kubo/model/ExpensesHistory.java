package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_expense_history")
public class ExpensesHistory implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	  private ExpensesHistoryPK pk;
	@Column
	  private Integer expense_type_id;
	@Column
	  private Double ammount;
	@Column
	  private String description;
	@Column
	  private Double ammount_modified; //'Monto modificado por mesa de control',
	@Column
	  private Integer prospectus_id_modified;
	@Column
	  private Date datetime_modified;
	@Column
	  private Double ammount_minus; //'Cantidad que se debe restar del rubro correspondiente',
	@Column
	  private Date creation_date;
	
	public ExpensesHistoryPK getPk() {
		return pk;
	}
	public void setPk(ExpensesHistoryPK pk) {
		this.pk = pk;
	}
	public Integer getExpense_type_id() {
		return expense_type_id;
	}
	public void setExpense_type_id(Integer expense_type_id) {
		this.expense_type_id = expense_type_id;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Double getAmmount_minus() {
		return ammount_minus;
	}
	public void setAmmount_minus(Double ammount_minus) {
		this.ammount_minus = ammount_minus;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
}
