package mx.com.kubo.managedbeans.portal.ofertas;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.controller.threads.RespuestaConsultaMasiva;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.mesa.buro.ProspectRiskIMP;
import mx.com.kubo.mesa.buro.ProyectLoanCreatorIMP;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.portal.AccessIMP;
import mx.com.kubo.tools.Utilities;

@ViewScoped
@ManagedBean(name = "consultaRenovacion") 
public class ConsultaRenovacionIMP extends ConsultaRenovacionAMO
implements Serializable
{
	private static final long serialVersionUID = 2053681503076917602L;
	
	@PostConstruct
	public void init()
	{
		
		System.out.println( "" );
		System.out.println( "" );
		
		System.out.println( "*******************************" );
		System.out.println( "*******************************" );
		System.out.println( "***consultaRenovacion.init()***" );
		System.out.println( "*******************************" );
		System.out.println( "*******************************" );
		
		System.out.println( "" );
		System.out.println( "" );
		
		faces = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		company_id = null;
		prospectus_id = null;
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");		
		
		if(sesion != null && sesion.getProspectus_id() != null && sesion.getArea() != null && sesion.getArea().toString().equals("L") )
		{
			company_id    = sesion.getCompany_id();
			prospectus_id = sesion.getProspectus_id();
			
			sesion.setCoachProspectus_id(prospectus_id);
			
		}else if(sesion != null && sesion.getProspectus_id() != null && sesion.getArea() != null && sesion.getArea().toString().equals("M") )
		{
		
			sesion_search_request = (SearchSummaySession)  resolver.getValue(elContext, null, "searchSummaySession");
			
			String search = sesion_search_request.getSearchSummary();
			
			String[] splStr = search.split("::") ;
			
			if( splStr.length == 4 ){
				
				prospectus_id = Integer.parseInt( splStr[2] );
				company_id    = Integer.parseInt( splStr[3] );
			}
			
			sesion.setCoachProspectus_id(sesion.getProspectus_id());
			
			membership_PK = new MembershipPK();
			membership_PK.setCompany_id(company_id);
			membership_PK.setProspectus_id(sesion.getProspectus_id());
			
			membership = service_membership.getMembershipById(membership_PK);
			
			nombreCoach = membership.getPerson().NombreCompletoNPM();
			
			//sesion.setProspectus_id(prospectus_id);
			
			
		}
		
		if( company_id != null && prospectus_id != null ){
			
			membership_PK = new MembershipPK();
			membership_PK.setCompany_id(company_id);
			membership_PK.setProspectus_id(prospectus_id);
			
			membership = service_membership.getMembershipById(membership_PK);
			
			person = membership.getPerson();
			
			nombreCliente = membership.getPerson().NombreCompletoNPM();
			
			auditor = new AccessIMP();
			auditor.setService_access(service_access);

			if( sesion != null && sesion.getProspectus_id() != null && sesion.getArea() != null && sesion.getArea().toString().equals("L")  ){
				
				auditor.setSesion(sesion);
				auditor.setScreen_id( SCREEN_CONSULTING_AUTOMATIC_RENOVATION );
				auditor.setAccess_from(access_from);
				
			}
			
			sesion.setProspectus_id(prospectus_id);
			auditor.save_access(sesion, SCREEN_CONSULTING_AUTOMATIC_RENOVATION );
			sesion.setProspectus_id(sesion.getCoachProspectus_id());
			score = service_score.loadMaxScoringByProspectus(prospectus_id, company_id);						
			
			init_score();				
		}
	}
	
	public void init_password(AjaxBehaviorEvent event)
	{
		input_secret = (HtmlInputSecret) event.getComponent();
		
		String password = input_secret.getValue().toString();
		
		password = Utilities.encrypt(password);
		
		password_ENABLED = membership.getPassword().equals(password);
		
		request = RequestContext.getCurrentInstance();
		
		request.addCallbackParam("password_ENABLED", password_ENABLED);
	}
	
	public void init_prospect_BC_risk()
	{
		request = RequestContext.getCurrentInstance();
			
		if(password_ENABLED)
		{
			risk = new ProspectRiskIMP();
			risk.setPerson(person);
			risk.setConsulting_renovation_ENABLED(true);
			risk.init();
			
			prospect_risk_ENABLED = risk.isProspect_risk_ENABLED();
		}				
		
		if(prospect_risk_ENABLED)
		{
			creator = new ProyectLoanCreatorIMP();
			creator.setScore(risk.getScore());
			creator.init();
			
			bur_sol_num = risk.getScore().getMx_solicitud_buro();
			
			init_consulting_manual();
		}
		
		request.addCallbackParam("prospect_risk_ENABLED", prospect_risk_ENABLED);
	}
	
	public void consulta_Id_Provider(){
		
		
		
		faces = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		RespuestaConsultaMasiva res =  ejecutaConsulta( person.getNatPerPK().getProspectus_id() , person.getNatPerPK().getCompany_id() );
		
		request = RequestContext.getCurrentInstance();
		
		request.addCallbackParam("no_hit" , res.isNo_hit() );
		
		request.addCallbackParam("maxIntentos" , res.isMaxIntentos() );
		
		//request.addCallbackParam("msgErrBur" , res.getMsgErrBur() );
		
		request.addCallbackParam("prospect_risk_ENABLED" , (!res.isNo_hit() && !res.isMaxIntentos() ) );
		
	}
	
	public void check_is_risk_processed()
	{
		request = RequestContext.getCurrentInstance();
		
		faces = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		is_risk_processed = false;
		
		score = service_score.loadScoringByBurSolNum(bur_sol_num);
		
		if(score != null)
		{	
			is_risk_processed = score.getRisk_processed() > 0 ? true : false;	
			
			if(is_risk_processed)
			{
				init_score();
			}
		}
		
		request.addCallbackParam("is_risk_processed", is_risk_processed);
		request.addCallbackParam("redirect_to_ofert_ENABLED", redirect_to_ofert_ENABLED);
		request.addCallbackParam("redirect_to_registro_ENABLED", redirect_to_registro_ENABLED);
		request.addCallbackParam("is_controlTable", ( sesion.getArea() != null && sesion.getArea().toString().equals("M") ) );	
		
		if( redirect_to_registro_ENABLED ){
			
			if(sesion != null && sesion.getProspectus_id() != null && sesion.getArea() != null && sesion.getArea().toString().equals("L") )
			{
				company_id    = sesion.getCompany_id();
				prospectus_id = sesion.getProspectus_id();
				
				sesion.setCoachProspectus_id(prospectus_id);
				
				membership_PK = new MembershipPK();
				membership_PK.setCompany_id(company_id);
				membership_PK.setProspectus_id(prospectus_id);
				
				membership = service_membership.getMembershipById(membership_PK);
				
				person = membership.getPerson();
				
				auditor = new AccessIMP();
				auditor.setService_access(service_access);
				auditor.setSesion(sesion);
				auditor.setScreen_id(SCREEN_REGISTRO_AFTER_CONSULTA);
				auditor.setAccess_from(access_from);
				
				score = service_score.loadMaxScoringByProspectus(prospectus_id, company_id);
				
				init_score();				
				
			}else if(sesion != null && sesion.getProspectus_id() != null && sesion.getArea() != null && sesion.getArea().toString().equals("M") ) {
				
				actualProyect = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id) ;
				
			}
			
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void validaSession () 
	{		
			boolean flag = false;
			
			faces          = FacesContext.getCurrentInstance();
			ExternalContext external    = faces.getExternalContext();		
			HttpSession sessionUsed = (HttpSession) external.getSession(false);
			ServletContext servlet     = sessionUsed.getServletContext();
			
			Hashtable<String, Hashtable<String,HttpSession>>  ht = (Hashtable<String, Hashtable<String,HttpSession>>)servlet.getAttribute("usuariosFirmados");
			
			Enumeration<String> enumKey = ht.keys();
			
			if( actualProyect == null ){
				actualProyect = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id) ;
			}

			while(enumKey.hasMoreElements()) 
			{
				
			   String key = enumKey.nextElement();
			    
			   Hashtable<String,HttpSession> htVal = (Hashtable<String,HttpSession>)ht.get(key);
			    
			    if( htVal.get( actualProyect.getPerson().getProspectus().getTracking_id()) != null )
			    {
			    	flag = true;
			    	
			    	break;
			    }
			    
			}
			
			request   = RequestContext.getCurrentInstance();
			
			request.addCallbackParam("isLogged", flag);
			
	}
	
	
	public String changeSessionForEditForm(){
		
		faces = FacesContext.getCurrentInstance();
		ELContext context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		NavigationBeanIMP navi = (NavigationBeanIMP)resolver.getValue(context, null, "navigationBean");
		
		navi.setPaginaActual("registro/basicdata");
		
		SearchSummaySession search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
		
		if( actualProyect == null ){
			actualProyect = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id) ;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(actualProyect.getProyectloanPk().getProyect_loan_id()).append("::");
		sb.append(actualProyect.getProyectloanPk().getProyect_id()).append("::");
		sb.append(actualProyect.getProyectloanPk().getProspectus_id()).append("::");
		sb.append(actualProyect.getProyectloanPk().getCompany_id());
		
		String str = sb.toString();
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id( sesion.getCompany_id()  );
		mpk.setProspectus_id( sesion.getProspectus_id() );
		
		Membership mCoach = service_membership.getMembershipById(mpk);
		
		mpk = new MembershipPK();
		
		mpk.setCompany_id( sesion.getCompany_id() );
		mpk.setProspectus_id( actualProyect.getPerson().getNatPerPK().getProspectus_id() );
		
		Membership mProspectus = service_membership.getMembershipById(mpk);
		
		HeaderBean headerbean = (HeaderBean)resolver.getValue(context, null, "headerBean");
		
		int i = 0;
		
		do {
			
			try{
			
				Thread.sleep(500);
				
				headerbean.iniciaSesionCoach(mCoach, mProspectus, true);
				
				search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
				
				search.setSearchSummary( str );
				
				sesion = (SessionBean)resolver.getValue(context, null, "sessionBean");
				
				sesion.setCoachProspectus_id(mCoach.getMembershipPK().getProspectus_id());
				
				sesion.setAccess_from("portalKubo");
				
				i++;
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}while (sesion.getCompany_id() == null && i < 4 );
		
		return "registrocoach";
	
	}
	
	
	public String returnToControlTable(){
		
		faces = FacesContext.getCurrentInstance();
		ELContext context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		StringBuilder sb = new StringBuilder();
		
		if( actualProyect == null ){
			actualProyect = service_proyect_loan.getMaxProyectLoanByProspect(prospectus_id, company_id) ;
		}
		
		sb.append(actualProyect.getProyectloanPk().getProyect_loan_id()).append("::");
		sb.append(actualProyect.getProyectloanPk().getProyect_id()).append("::");
		sb.append(actualProyect.getProyectloanPk().getProspectus_id()).append("::");
		sb.append(actualProyect.getProyectloanPk().getCompany_id());
		
		String str = sb.toString();
		
		SearchSummaySession search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
		
		search.setSearchSummary( str );
		
		SummaryRequest summary_request = (SummaryRequest)  resolver.getValue(context, null, "summaryRequest");
		
		summary_request.init();		
		
		return "controlTable";
		
	}
	
	
}
