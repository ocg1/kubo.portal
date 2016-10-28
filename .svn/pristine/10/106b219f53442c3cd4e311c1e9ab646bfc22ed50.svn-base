package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contract_typeCatPK  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private int contract_type_id;
	@Column
	private int company_id;
	
	public Contract_typeCatPK(){
		
	}
	public Contract_typeCatPK(int contract_type_id,int company_id){
		this.contract_type_id=contract_type_id;
		this.company_id=company_id;
		
	}
	public int getContract_type_id() {
		return contract_type_id;
	}
	public void setContract_type_id(int contract_type_id) {
		this.contract_type_id = contract_type_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	

}
