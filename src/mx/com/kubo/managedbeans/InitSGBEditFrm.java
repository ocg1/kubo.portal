package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.services.MembershipService;

@ManagedBean
@ViewScoped
public class InitSGBEditFrm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	private String action;
	
	private String prospectus_id;
	private String company_id;
	private String from;
	private String coach;

	@PostConstruct
	public void init(){
		
		
		System.out.println( "Iniciando InitSGBEditFrm" );
		
		

	}
	
	public String iniciaSession(){
		
		FacesContext faces = FacesContext.getCurrentInstance();
		ELContext context   = faces.getELContext();
		ELResolver resolver  = faces.getApplication().getELResolver();
		
		System.out.println(
				
				" prospectus_id = " + prospectus_id +
				" company_id = " + company_id +
				" from = " + from +
				" coach = " + coach
				
				);
		
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id( Integer.parseInt( company_id )  );
		mpk.setProspectus_id( Integer.parseInt( coach ) );
		
		Membership mCoach = service_membership.getMembershipById(mpk);
		
		mpk = new MembershipPK();
		
		mpk.setCompany_id( Integer.parseInt( company_id ) );
		mpk.setProspectus_id( Integer.parseInt( prospectus_id ) );
		
		Membership mProspectus = service_membership.getMembershipById(mpk);
		
		HeaderBean headerbean = (HeaderBean)resolver.getValue(context, null, "headerBean");
		
		headerbean.iniciaSesionCoach(mCoach, mProspectus, true);
		
		SessionBean sesion = (SessionBean)resolver.getValue(context, null, "sessionBean");
		
		sesion.setCoachProspectus_id(mCoach.getMembershipPK().getProspectus_id());
		
		sesion.setAccess_from("portalKubo");
		
		SearchSummaySession search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
		
		
		String str = mProspectus.getMembershipPK().getProspectus_id()+"::";
		
		search.setSearchSummary( str );
		
		
		return "registrocoach";
		
	}

	public MembershipService getService_membership() {
		return service_membership;
	}

	public void setService_membership(MembershipService service_membership) {
		this.service_membership = service_membership;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(String prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}
	
}
