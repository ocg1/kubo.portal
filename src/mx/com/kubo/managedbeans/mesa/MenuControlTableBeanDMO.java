package mx.com.kubo.managedbeans.mesa;

import java.util.List;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.RoleAccess;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.RoleAccessService;

public abstract class MenuControlTableBeanDMO 
{
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService  proyectloanservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{roleAccessServiceImp}")
	protected RoleAccessService roleaccessService;	
	
	protected FacesContext  faces;
	protected ELResolver    resolver;
	protected ELContext     elContext;
	
	
	protected ExternalContext    external;
	protected HttpServletRequest request;	
	protected ResourceBundle     recurso;
	
	protected SessionBean   sesion;
	
	protected String path;
	protected String url;
	
	protected List<AccessCollector> menuAccess;
	protected List<RoleAccess>      roleAccess;
	
	private List<MenuRegBean>  menus;
	
	private String canceledReason = "";	
	private String menuSel        = "menu1";
	private String paginaActual   = "controlTable/searchRequest";	
	
	private boolean prospectus_is_canceled= false;
	
	public List<MenuRegBean> getMenus() {
		return menus;
	}
	
	public String getMenuSel() {
		return menuSel;
	}

	public String getPaginaActual() {
		return paginaActual;
	}

	public void setMenus(List<MenuRegBean> menus) {
		this.menus = menus;
	}

	public void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public void setRoleaccessService(RoleAccessService service) 
	{
		roleaccessService = service;
	}
	
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}

	public void setMenuSel(String menuSel) {
		this.menuSel = menuSel;
	}

	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}

	public boolean isProspectus_is_canceled() 
	{
		return prospectus_is_canceled;
	}
	
	public void setProspectus_is_canceled(boolean prospectus_is_canceled) 
	{
		this.prospectus_is_canceled = prospectus_is_canceled;
	}

	public String getCanceledReason() {
		return canceledReason;
	}

	public void setCanceledReason(String canceledReason) {
		this.canceledReason = canceledReason;
	}

	public ProyectLoanService getProyectloanservice() {
		return proyectloanservice;
	}

	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
		this.proyectloanservice = proyectloanservice;
	}
}
