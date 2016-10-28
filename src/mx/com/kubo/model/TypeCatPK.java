package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TypeCatPK implements Serializable {		

	private static final long serialVersionUID = 1L;
	
	@Column
	private int	type_id;
	@Column
	private int	company_id;
	
	public TypeCatPK(){
		
	}
	public TypeCatPK(int type_id,int company_id){
		this.type_id=type_id;
		this.company_id=company_id;		
	}
	
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}	

}
