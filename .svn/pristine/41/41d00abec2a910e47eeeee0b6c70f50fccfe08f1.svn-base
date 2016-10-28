package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PurposePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
	int purpose_id;
	@Column
	int company_id;
	
	public PurposePK(){
		
	}
	public PurposePK(int purpose_id,int company_id){
		this.purpose_id=purpose_id;
		this.company_id=company_id;
	}

	public int getPurpose_id() {
		return purpose_id;
	}

	public void setPurpose_id(int purpose_id) {
		this.purpose_id = purpose_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}	
	
}
