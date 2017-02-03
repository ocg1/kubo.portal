package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GroupMemberPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column
	private int company_id; // ` INT UNSIGNED NOT NULL,
	@Column
	private int investor_group_id; // INT UNSIGNED NOT NULL,
	@Column
	private int group_member_id; // ` INT UNSIGNED NOT NULL,
	@Column
	private int prospectus_id; // ` INT(11) UNSIGNED NOT NULL,
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getInvestor_group_id() {
		return investor_group_id;
	}
	public void setInvestor_group_id(int investor_group_id) {
		this.investor_group_id = investor_group_id;
	}
	public int getGroup_member_id() {
		return group_member_id;
	}
	public void setGroup_member_id(int group_member_id) {
		this.group_member_id = group_member_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	
	
}
