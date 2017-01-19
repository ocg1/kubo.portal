package mx.com.kubo.bean;

import java.util.List;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.BlacklistIp;
import mx.com.kubo.model.BlacklistPassword;
import mx.com.kubo.model.BlacklistPhone;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.References;

public class BehaviorBean {

	private List<BlacklistPassword> lstBlkPass;
	private List<BlacklistIp> lstBlkIp;
	private List<BlacklistPhone> lstBlkphone;
	
	private List<PasswordHistory> lstUserPass;
	private List<Access> lstUserAccess;
	private List<Phone> lstUserPhones;
	private List<References> lstreferences;
	
	public List<BlacklistPassword> getLstBlkPass() {
		return lstBlkPass;
	}
	public void setLstBlkPass(List<BlacklistPassword> lstBlkPass) {
		this.lstBlkPass = lstBlkPass;
	}
	public List<BlacklistIp> getLstBlkIp() {
		return lstBlkIp;
	}
	public void setLstBlkIp(List<BlacklistIp> lstBlkIp) {
		this.lstBlkIp = lstBlkIp;
	}
	public List<BlacklistPhone> getLstBlkphone() {
		return lstBlkphone;
	}
	public void setLstBlkphone(List<BlacklistPhone> lstBlkphone) {
		this.lstBlkphone = lstBlkphone;
	}
	public List<PasswordHistory> getLstUserPass() {
		return lstUserPass;
	}
	public void setLstUserPass(List<PasswordHistory> lstUserPass) {
		this.lstUserPass = lstUserPass;
	}
	public List<Access> getLstUserAccess() {
		return lstUserAccess;
	}
	public void setLstUserAccess(List<Access> lstUserAccess) {
		this.lstUserAccess = lstUserAccess;
	}
	public List<Phone> getLstUserPhones() {
		return lstUserPhones;
	}
	public void setLstUserPhones(List<Phone> lstUserPhones) {
		this.lstUserPhones = lstUserPhones;
	}
	public List<References> getLstreferences() {
		return lstreferences;
	}
	public void setLstreferences(List<References> lstreferences) {
		this.lstreferences = lstreferences;
	}
	
}
