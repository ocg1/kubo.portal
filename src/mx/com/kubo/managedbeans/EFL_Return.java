package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.controller.efl_connect.EflConnect;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.tools.Utilities;


@ManagedBean
@ViewScoped
public class EFL_Return implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int COMPANY_ID = 1;

	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	private Membership mCoach;
	
	private SessionBean sesion ;
	
	private HeaderBean headerbean;
	
	
	private String name;
	
	@PostConstruct
	public void init(){
		
		try
		{	
			FacesContext faces    = FacesContext.getCurrentInstance();
			ELContext context  = faces.getELContext();
			ExternalContext external = faces.getExternalContext();
			ELResolver resolver = faces.getApplication().getELResolver();
			
			/*
			 * URL EXAMPLE 
			 * http://localhost:8080/Kubo/Portal/efl2.xhtml?utm_source=EFL_PSYCHOMETRIC&utm_medium=PSYCHOMETRIC&prospectus_id=MTQwNDQ=
			 * 
			 */
			
			String prospectus_id        = (String) external.getRequestParameterMap().get("prospectus_id");
			
			if(prospectus_id != null ){
				
				prospectus_id = Utilities.encodeBase64( prospectus_id ) ;
				
				if( Utilities.isNumeric(prospectus_id) ){
				
					MembershipPK mpk = new MembershipPK();
					
					mpk.setCompany_id( COMPANY_ID );
					mpk.setProspectus_id( Integer.parseInt(prospectus_id) );
					
					mCoach = service_membership.getMembershipById(mpk);
					
					name =  mCoach.getPerson().getFirst_name();
					
					Scoring score = scoringService.loadMaxScoringByProspectus(Integer.parseInt(prospectus_id),COMPANY_ID);
					
					EflConnect eflResult = new EflConnect();
					
					eflResult.EFLResult(prospectus_id, score.getMx_solicitud_buro());
					
					if( eflResult.getEstatus() != null && eflResult.getEstatus().equals("1") ){
					
						score.setEfl_test("1");
					
					}else{
						score.setEfl_test("3");
					}
					
					scoringService.updateScoring(score);
				
					
				
				}
				
				sesion          = (SessionBean)         resolver.getValue(context, null, "sessionBean");
				
				headerbean = (HeaderBean)resolver.getValue(context, null, "headerBean");
				
			}
				
			}catch(Exception e){
				
				e.printStackTrace();
				
			}

			
		}
				
		public void iniciSessionReturn(){
			
			try{
		
				if(
						
					(
							sesion != null && 
							sesion.getProspectus_id() != null && 
							sesion.getProspectus_id().intValue() != mCoach.getMembershipPK().getProspectus_id()  
					) 
					
					||
					 
					sesion == null || sesion.getProspectus_id() == null
							
				  ){
				
					headerbean.iniciaSesionCoach(mCoach, null, false);
					
				}
				
				registraAccess( sesion , mCoach.getMembershipPK().getProspectus_id() );
						
				
				
		}catch(Exception e){
			
			e.printStackTrace();
			
		}

		
	}
	
	public String getAction(){
		
		FacesContext faces    = FacesContext.getCurrentInstance();
		ELContext context  = faces.getELContext();
		ELResolver resolver = faces.getApplication().getELResolver();
		
		NavigationBeanIMP nav          = (NavigationBeanIMP)         resolver.getValue(context, null, "navigationBean");
		
		nav.init();
		
		return "registrar";
		
	}
	
	private void registraAccess( SessionBean sesion , Integer prospectus_id){
		
		Access access = new Access();
		
		access.setCompany_id(COMPANY_ID);
		access.setProspectus_id(prospectus_id);
		access.setScreen_id(77);
		access.setPercentage(0);
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		access.setUrl_access( sesion.getUrl_access() );
		access.setProspectus_id_coach(sesion.getCoachProspectus_id());
		
		access.setIpaddress(sesion.getIP_address_client());
		
		access.setAccess_datetime(new Date());
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access, true);
		
	}

	public MembershipService getService_membership() {
		return service_membership;
	}

	public void setService_membership(MembershipService service_membership) {
		this.service_membership = service_membership;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScoringService getScoringService() {
		return scoringService;
	}

	public void setScoringService(ScoringService scoringService) {
		this.scoringService = scoringService;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	
}
