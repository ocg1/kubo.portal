package mx.com.kubo.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.MembershipService;

public abstract class ConfirmationMailDMO 
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService changeControlService;
	
	protected FacesContext faces;
	protected ExternalContext external;
	
	protected HttpServletRequest servlet;
	
	protected Change_control changeMail;
	protected Change_control changeCtrl;
	protected Change_controlPK changeCtrlPK;
	
	protected List<Change_control> lisChangMail;
	
	protected Membership membership;
	
	protected String counter;
	protected String newemail;
	protected String ipAddress;
	
	protected int prospectus_id;
	protected int company_id;
	
	protected boolean success;
	protected boolean change_OK; 
	
	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public Change_controlService getChangeControlService() {
		return changeControlService;
	}

	public void setChangeControlService(Change_controlService changeControlService) {
		this.changeControlService = changeControlService;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
