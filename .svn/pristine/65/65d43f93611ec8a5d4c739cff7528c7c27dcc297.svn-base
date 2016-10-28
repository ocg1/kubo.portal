package mx.com.kubo.managedbeans.home;

import java.text.SimpleDateFormat;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.preregistro.PreregistroIMO;
import mx.com.kubo.model.QrAccess;
import mx.com.kubo.model.ViewHomeStatistics;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.QrAccessService;

public abstract class InicioValuesDMO 
{
	@ManagedProperty("#{qrAccessServiceImp}")
	protected QrAccessService service_access;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	protected PreregistroIMO preregistro;
	protected ViewHomeStatistics statistics;
		
	protected FacesContext    faces;
	protected ELContext       context;
	protected ExternalContext external;
	protected ELResolver      resolver;
	protected SessionBean     sesion;
	protected HttpSession sessionUsed;
	
	protected QrAccess qr;
	
	protected SimpleDateFormat formatter;
	
	protected String area;
	protected String inicio;
	protected String partner; 
	protected String qrParam;	
	protected String utm_origen;
	protected String utm_campaign;
	protected String utm_medium;
	
	protected String url_value;
	
	protected String partner_id;
	protected String utm_partner_id;
	protected String sm_first_name;
	protected String sm_email;
	protected String promotorID;
	protected String referred;
	
	protected String user_agent;
	
	protected boolean renderLogoPartner;
	
	protected InicioValuesDMO()
	{
		formatter = new SimpleDateFormat("dd 'de' MMMMM 'del' yyyy hh:mm:ss aaa");	
	}
	
	public final void setService_access(QrAccessService service) 
	{
		service_access = service;
	}
	
	public String getPartner() {
		return partner;
	}

	public String getInicio() {
		return inicio;
	}
	
	public boolean isRenderLogoPartner() {
		return renderLogoPartner;
	}

	public String getArea() {
		return area;
	}
	
	public String getQrParam() {
		return qrParam;
	}

	public final ViewHomeStatistics getStatistics() 
	{
		return statistics;
	}

	public String getUrl_value() {
		return url_value;
	}

	public void setUrl_value(String url_value) {
		
		//System.out.println("asignando url: "+url_value);
		
		this.url_value = url_value;
		
		if(sesion!=null){
			if(this.url_value != null && !this.url_value.equals("")){
				sesion.setUrl_value(this.url_value);
			}
		}
	
	}

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public String getPromotorID() {
		return promotorID;
	}

	public void setPromotorID(String promotorID) {
		this.promotorID = promotorID;
	}

	public String getReferred() {
		return referred;
	}

	public void setReferred(String referred) {
		this.referred = referred;
	}
}
