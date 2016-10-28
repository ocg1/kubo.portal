package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApprovalCreditPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private String safi_credit_id; // ` VARCHAR(25) NOT NULL,
	@Column
	private String approval_user; // ` VARCHAR(300) NOT NULL,
	
	
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public String getApproval_user() {
		return approval_user;
	}
	public void setApproval_user(String approval_user) {
		this.approval_user = approval_user;
	}
	
}
