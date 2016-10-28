package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SellingDetailPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private int income_id;
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	@Column
	private int income_detail_id;
	@Column
	private int selling_type_id;
	
	public SellingDetailPK(int income_id,int prospectus_id,int company_id,int income_detail_id,int selling_type_id){
		this.income_id=income_id;
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		this.income_detail_id=income_detail_id;
		this.selling_type_id=selling_type_id;
	}
	public SellingDetailPK(int income_id,int prospectus_id,int company_id,int income_detail_id){
		this.income_id=income_id;
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		this.income_detail_id=income_detail_id;
	}
	public SellingDetailPK(int income_id,int prospectus_id,int company_id){
		this.income_id=income_id;
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
	}
	
	public SellingDetailPK(){
		
	}

	public int getIncome_id() {
		return income_id;
	}

	public void setIncome_id(int income_id) {
		this.income_id = income_id;
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

	public int getIncome_detail_id() {
		return income_detail_id;
	}

	public void setIncome_detail_id(int income_detail_id) {
		this.income_detail_id = income_detail_id;
	}

	public int getSelling_type_id() {
		return selling_type_id;
	}

	public void setSelling_type_id(int selling_type_id) {
		this.selling_type_id = selling_type_id;
	}
	
	
	
}
