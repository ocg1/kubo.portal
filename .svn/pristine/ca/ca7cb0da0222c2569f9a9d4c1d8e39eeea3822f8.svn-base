package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="queryAddIncome",
query="select MAX(i.incomePk.income_id) from Income i where i.incomePk.prospectus_id=? and i.incomePk.company_id=?"
)
@Entity
@Table(name="gn_income")
public class Income implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "income_type_id", referencedColumnName = "income_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	    })
	private IncomeType incomeType; 
	
	@EmbeddedId
	private IncomePK incomePk;
	@Column
	private Integer income_type_id;
	@Column
	private Double ammount;
	@Column
	private String description;
	@Column
	private Double ammount_modified;//	"decimal(12	2)"	YES		NULL
	@Column
	private Integer prospectus_id_modified;//	"int(10) unsigned"	YES	MUL	NULL	
	@Column
	private Date datetime_modified;//	datetime	YES		NULL	

	
	public Income(){
		
	}

	public IncomePK getIncomePk() {
		return incomePk;
	}

	public void setIncomePk(IncomePK incomePk) {
		this.incomePk = incomePk;
	}

	public Integer getIncome_type_id() {
		return income_type_id;
	}

	public void setIncome_type_id(Integer income_type_id) {
		this.income_type_id = income_type_id;
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

	public IncomeType getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(IncomeType incomeType) {
		this.incomeType = incomeType;
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

	
	

}
