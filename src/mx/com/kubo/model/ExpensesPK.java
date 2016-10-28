package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExpensesPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private int expense_id;
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	
	public ExpensesPK(){
		
	}
	public ExpensesPK(int expense_id,int prospectus_id,int company_id){
		this.expense_id=expense_id;
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		
	}
	public ExpensesPK(int prospectus_id,int company_id){
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		
	}
	public int getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(int expense_id) {
		this.expense_id = expense_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

}
