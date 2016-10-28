package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExpensesTypePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExpensesTypePK(){
		
	}
	
	public ExpensesTypePK(int expense_type_id, int company_id){
		this.expense_type_id = expense_type_id;
		this.company_id = company_id;
	}
	
	@Column
	int expense_type_id;
	@Column
	int company_id;

	public int getExpenses_type_id() {
		return expense_type_id;
	}
	public void setExpenses_type_id(int expenses_type_id) {
		this.expense_type_id = expenses_type_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}	
}