package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Role_Searching_PK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Column
	private int company_id ;
	@Column
	private int role_id;
	@Column
	private String area_used;
	
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getArea_used() {
		return area_used;
	}
	public void setArea_used(String area_used) {
		this.area_used = area_used;
	}
	
}
