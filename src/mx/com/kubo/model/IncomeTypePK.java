package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IncomeTypePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IncomeTypePK(){
		
	}
	
	public IncomeTypePK(int income_type_id, int company_id){
		this.income_type_id = income_type_id;
		this.company_id = company_id;
	}
	
	@Column
	int income_type_id;
	@Column
	int company_id;

	public int getIncome_type_id() {
		return income_type_id;
	}

	public void setIncome_type_id(int income_type_id) {
		this.income_type_id = income_type_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
