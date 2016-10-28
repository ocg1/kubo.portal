package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FromWhereCatPK implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column
	private int fromwhere_id;
	@Column
	private int company_id;
	
	public FromWhereCatPK(){
	}
	public FromWhereCatPK(int fromwhere_id,int company_id){
		this.fromwhere_id=fromwhere_id;
		this.company_id=company_id;
	}

	public int getFromwhere_id() {
		return fromwhere_id;
	}

	public void setFromwhere_id(int fromwhere_id) {
		this.fromwhere_id = fromwhere_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
