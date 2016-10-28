package mx.com.kubo.managedbeans;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.model.Membership;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.SystemParamService;

public class ChangePassDMO 
{
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	protected SessionBean sesion;
	protected Membership member;
	
	protected NotificadorConfigRequest request_notificar_config;
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
	
	protected String name;
	protected String email;
	protected String newPass;
	protected String diasVal;
	
	protected String [] lista_receptores;
	
	protected final String CONFIRMACION_CAMBIO_PASSWORD = "34";
	
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}
	
	public void setSavingaccountservice(SavingAccountService service) 
	{
		savingaccountservice = service;
	}

	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}
	
	public void setSystemparamservice(SystemParamService service)
	{
		systemparamservice = service;
	}

	public void setPasswordhistoryservice(PasswordHistoryService service) 
	{
		passwordhistoryservice = service;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Membership getMember() {
		return member;
	}

	public void setMember(Membership member) {
		this.member = member;
	}

	public String getDiasVal() {
		return diasVal;
	}

	public void setDiasVal(String diasVal) {
		this.diasVal = diasVal;
	}
}
