package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProyectFundingPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private int proyect_loan_id;
	@Column
	private int proyect_id;
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	@Column 
	private int prospectus_investor_id;
	@Column 
	private int operation_number;
	
	public ProyectFundingPK(){
		
	}
	
	public ProyectFundingPK(int proyect_loan_id,int proyect_id,int prospectus_id,int company_id, int prospectus_investor_id){
		this.proyect_loan_id=proyect_loan_id;
		this.proyect_id=proyect_id;
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		this.prospectus_investor_id=prospectus_investor_id;
	}

	public int getProyect_loan_id() {
		return proyect_loan_id;
	}

	public void setProyect_loan_id(int proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}

	public int getProyect_id() {
		return proyect_id;
	}

	public void setProyect_id(int proyect_id) {
		this.proyect_id = proyect_id;
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

	public int getProspectus_investor_id() {
		return prospectus_investor_id;
	}

	public void setProspectus_investor_id(int prospectus_investor_id) {
		this.prospectus_investor_id = prospectus_investor_id;
	}
	public int getOperation_number() {
		return operation_number;
	}
	public void setOperation_number(int operation_number) {
		this.operation_number = operation_number;
	}
	
	
}