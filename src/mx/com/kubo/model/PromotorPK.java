package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PromotorPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column
	private int promotor_id;
	@Column
	private int company_id;
	
	public PromotorPK(int promotor_id,int company_id){
		
	}
	public PromotorPK(int company_id){
		
	}
	public PromotorPK(){
		
	}
	public int getPromotor_id() {
		return promotor_id;
	}
	public void setPromotor_id(int promotor_id) {
		this.promotor_id = promotor_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
