package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name="ln_approval_credit" )
public class ApprovalCredit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ApprovalCreditPK pk;
	@Column
	private Date approval_date; //` DATETIME NULL,
	@Column
	private String approval_role_user; //` VARCHAR(100) NULL,
	
	public ApprovalCreditPK getPk() {
		return pk;
	}
	public void setPk(ApprovalCreditPK pk) {
		this.pk = pk;
	}
	public Date getApproval_date() {
		return approval_date;
	}
	public void setApproval_date(Date approval_date) {
		this.approval_date = approval_date;
	}
	public String getApproval_role_user() {
		return approval_role_user;
	}
	public void setApproval_role_user(String approval_role_user) {
		this.approval_role_user = approval_role_user;
	}
	
}
