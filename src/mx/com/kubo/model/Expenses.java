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

@NamedQuery(name="queryAddExpenses",
query="select MAX(e.expensesPk.expense_id) from Expenses e where e.expensesPk.prospectus_id=? and e.expensesPk.company_id=?"
)
@Entity
@Table(name="gn_expense")
public class Expenses implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "expense_type_id", referencedColumnName = "expense_type_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	    })
	private ExpensesType expensesType;
	
	@EmbeddedId
	private ExpensesPK expensesPk;
	@Column
	private Integer expense_type_id;
	@Column
	private Double ammount;
	@Column
	private String description;
	@Column
	private Double ammount_modified;
	@Column
	private Integer prospectus_id_modified;
	@Column
	private Date datetime_modified;
	@Column
	private Double ammount_minus;
	
	public Expenses(){
		
	}

	public ExpensesPK getExpensesPk() {
		return expensesPk;
	}

	public void setExpensesPk(ExpensesPK expensesPk) {
		this.expensesPk = expensesPk;
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

	public ExpensesType getExpensesType() {
		return expensesType;
	}

	public void setExpensesType(ExpensesType expensesType) {
		this.expensesType = expensesType;
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

	
	
	
}
