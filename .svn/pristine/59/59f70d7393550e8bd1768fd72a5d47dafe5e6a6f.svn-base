package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BmxEconActivityCatPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private String bmx_econ_activity_id;
	@Column
	private int company_id;
	
	public BmxEconActivityCatPK(){
		
	}
	public BmxEconActivityCatPK(String bmx_econ_activity_id,int company_id ){
		this.bmx_econ_activity_id=bmx_econ_activity_id;
		this.company_id=company_id;
		
	}
	public String getBmx_econ_activity_id() {
		return bmx_econ_activity_id;
	}
	public void setBmx_econ_activity_id(String bmx_econ_activity_id) {
		this.bmx_econ_activity_id = bmx_econ_activity_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
