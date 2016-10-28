package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleAssignmentPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int role_assignment_id;
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	@Column
	private int role_id;
	
	public int getRole_assignment_id() {
		return role_assignment_id;
	}
	public void setRole_assignment_id(int role_assignment_id) {
		this.role_assignment_id = role_assignment_id;
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
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}


}
