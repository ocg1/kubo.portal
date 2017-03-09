package mx.com.kubo.managedbeans.mesa;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.busqueda.SearchRequestIMP;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;

@ManagedBean @SessionScoped
public class RegistroCoach implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	private String coach;		
	private String prospectus_id;
	private String company_id;
	private String from;
	private Membership mCoach;
	private Membership mProspectus;
	
	private boolean successInfo;
	
	private final boolean ENTRAR = true;
	
	private final boolean SALIR = !ENTRAR;

	@PostConstruct
	public void init(){
		
		FacesContext faces = FacesContext.getCurrentInstance();	
		ExternalContext external  = faces.getExternalContext();
		ELContext context   = faces.getELContext();
		ELResolver resolver  = faces.getApplication().getELResolver();
		
		coach        = (String) external.getRequestParameterMap().get("coach");		
		
		if(coach != null){
		
			System.out.println( "INICIA REGISTRO COACH DESDE URL" );
			
			prospectus_id = (String) external.getRequestParameterMap().get("prospectus_id");
			company_id = (String) external.getRequestParameterMap().get("company_id");
			from = (String) external.getRequestParameterMap().get("from");
			successInfo = false;
			initSession();
			
			//RegistroCoach  reg = (RegistroCoach)resolver.getValue(context, null, "registroCoach");
			
			setmCoach(mCoach);
			setmProspectus(mProspectus);
			
		}else{
			
			SearchSummaySession search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
			
			SessionBean session = (SessionBean)resolver.getValue(context, null, "sessionBean");
			
			String str = search.getSearchSummary();
			
			System.out.println( str );
			
			if( str != null ){
				coach        	= session.getCoachProspectus_id()+"";	
				
				String[] s = str.split("::");
				
				if( s.length > 2 ){
					prospectus_id = s[2];
				}else{
					prospectus_id = s[0];
				}
				
				company_id 		= session.getCompany_id()+"";
				from 			= "portalKubo";
				successInfo 	= true;
				
				MembershipPK mpk = new MembershipPK();
				
				mpk.setCompany_id( Integer.parseInt( company_id ) );
				mpk.setProspectus_id(Integer.parseInt(coach));
				
				mCoach = service_membership.getMembershipById(mpk);
				
				mpk = new MembershipPK();
				
				mpk.setCompany_id( Integer.parseInt( company_id ) );
				mpk.setProspectus_id(Integer.parseInt(prospectus_id));
				
				mProspectus = service_membership.getMembershipById(mpk);
				
				registrarAcceso( ENTRAR );
				
				System.out.println( " coach " + coach +"  prospectus_id: "+ prospectus_id );
				
			}else{
				
				if( session != null && session.getProspectus_id() != null && session.getCoachProspectus_id() != null  ){
					
					coach        	= session.getCoachProspectus_id()+"";	
					
					company_id 		= session.getCompany_id()+"";
					from 			= "portalKubo";
					successInfo 	= true;
					
					prospectus_id 	= session.getProspectus_id()+"";
					
					MembershipPK mpk = new MembershipPK();
					
					mpk.setCompany_id( Integer.parseInt( company_id ) );
					mpk.setProspectus_id(Integer.parseInt(coach));
					
					mCoach = service_membership.getMembershipById(mpk);
					
					mpk = new MembershipPK();
					
					mpk.setCompany_id( Integer.parseInt( company_id ) );
					mpk.setProspectus_id(Integer.parseInt(prospectus_id));
					
					mProspectus = service_membership.getMembershipById(mpk);
					
					registrarAcceso( ENTRAR );
					
					System.out.println( " coach " + coach +"  prospectus_id: "+ prospectus_id );
					
				}
				
			}
			
		}
		
	}
	
	private void registrarAcceso( boolean accion ){
		
		Access access = new Access();
		
		access.setCompany_id( Integer.parseInt(company_id ));
		access.setProspectus_id(mCoach.getMembershipPK().getProspectus_id());
		if(accion == ENTRAR){
		access.setScreen_id(46); //RegistroCoach
		}else{
			access.setScreen_id(47); //RegistroCoach [LogOut]
		}
		access.setPercentage(0);
		access.setWeb_browser(null);
		access.setWeb_browser_version(null);
		access.setOp_system(null);
		access.setHorizontal_size(null);
		access.setVertical_size(null);
		access.setIpaddress(null);
		
		access.setIpaddress(null);
		
		access.setProspectus_id_viewed( mProspectus.getMembershipPK().getProspectus_id() );
		
		access.setAccess_from(from);
		
		accessService.add(access, false);
		
	}
	
	public void initSession(){
		
		if( coach != null ){
		
			FacesContext faces = FacesContext.getCurrentInstance();	
			ELContext context   = faces.getELContext();
			ELResolver resolver  = faces.getApplication().getELResolver();
			
			MembershipPK mpk = new MembershipPK();
			
			mpk.setCompany_id( Integer.parseInt( company_id ) );
			mpk.setProspectus_id(Integer.parseInt(coach));
			
			mCoach = service_membership.getMembershipById(mpk);
			
			mpk = new MembershipPK();
			
			mpk.setCompany_id( Integer.parseInt( company_id ) );
			mpk.setProspectus_id(Integer.parseInt(prospectus_id));
			
			mProspectus = service_membership.getMembershipById(mpk);
			
			HeaderBean headerbean = (HeaderBean)resolver.getValue(context, null, "headerBean");
			
			headerbean.iniciaSesionCoach(mCoach, mProspectus, true);
			
			System.out.println( " coach " + coach +"  prospectus_id: "+ prospectus_id );
			
			NavigationBeanIMP navigation1 = (NavigationBeanIMP)resolver.getValue(context, null, "navigationBean");
			
			System.out.println( navigation1.getPaginaActual() );
			
			successInfo = true;
			
		}
	}
	
	
	public String backSession(){
		
			FacesContext faces = FacesContext.getCurrentInstance();	
			ELContext context   = faces.getELContext();
			ELResolver resolver  = faces.getApplication().getELResolver();
			
			NavigationBeanIMP navigation1 = (NavigationBeanIMP)resolver.getValue(context, null, "navigationBean");
			
			System.out.println( navigation1.getPaginaActual() );
			
			SearchSummaySession search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
			
			String str = search.getSearchSummary();
			
			if( str != null ){
			
				navigation1.setPaginaActual( "controlTable/searchRequest" ) ;
				
				HeaderBean headerbean = (HeaderBean)resolver.getValue(context, null, "headerBean");
				
				headerbean.iniciaSesionCoach(mCoach, mProspectus, false);
				
				NavigationBeanIMP navigation = (NavigationBeanIMP)resolver.getValue(context, null, "navigationBean");
				
				search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
				
				search.setSearchSummary( str );
				
				if( str.split("::").length < 3 ){
					
					System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
					System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
					System.out.println("true: str="+str);
					System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
					System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
					search.setPerson(false);
					search.setActividad_ENABLED(true);
					search.setShowInvestPnl(true);
					
				}else{
					
					System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
					System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
					System.out.println("false: str="+str);
					System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
					System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
					search.setPerson(false);
					search.setActividad_ENABLED(true);
					search.setShowInvestPnl(true);
					search.setPerson(false);
					search.setActividad_ENABLED(true);
					search.setShowInvestPnl(true);
					
				}
				
				SearchRequestIMP searchRequest = (SearchRequestIMP)resolver.getValue(context, null, "searchRequest");
				
				searchRequest.setSearch(mProspectus.getMembershipPK().getProspectus_id()+"");
				
				searchRequest.listener_buscar_solicitud( null );
				
				registrarAcceso( SALIR );
				
				System.out.println( navigation.getPaginaActual() );
				
				successInfo = false;
				
				return "controlTable";
			
			}else{
				

				HeaderBean headerbean = (HeaderBean)resolver.getValue(context, null, "headerBean");
				
				headerbean.SignOut();
				
				
				return "kubofinanciero";
				
			}
			
	}
	

	public Membership getmCoach() {
		return mCoach;
	}

	public void setmCoach(Membership mCoach) {
		this.mCoach = mCoach;
	}

	public Membership getmProspectus() {
		return mProspectus;
	}

	public void setmProspectus(Membership mProspectus) {
		this.mProspectus = mProspectus;
	}

	public MembershipService getService_membership() {
		return service_membership;
	}

	public void setService_membership(MembershipService service_membership) {
		this.service_membership = service_membership;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
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

	public boolean isSuccessInfo() {
		return successInfo;
	}

	public void setSuccessInfo(boolean successInfo) {
		this.successInfo = successInfo;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	
}
