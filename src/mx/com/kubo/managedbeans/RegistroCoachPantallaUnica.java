package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.services.MembershipService;

@ManagedBean @ViewScoped
public class RegistroCoachPantallaUnica implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	String prospectus_id;
	String saludo;
	String coach_id;
	String company_id ;

	@PostConstruct
	public void init(){
		
		saludo = "cargando...";
		
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "--- RegistroCoachPantallaUnica.init() ---" );
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "" );
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext context   = facesContext.getELContext();
		ELResolver resolver  = facesContext.getApplication().getELResolver();
		ExternalContext external = facesContext.getExternalContext();
		
		prospectus_id = (String) external.getRequestParameterMap().get("prospectus_id_viewed") ;
		coach_id = (String) external.getRequestParameterMap().get("prospectus_id") ;//Analista o coach
		company_id = (String) external.getRequestParameterMap().get("company_id") ;
		
		SessionBean sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		if( sesion.getProspectus_id() != null && sesion.getProspectus_id().intValue() != 0 ){
		
			MembershipPK pk = new MembershipPK();
			
			pk.setCompany_id(1);
			pk.setProspectus_id(sesion.getProspectus_id());
			
			Membership member1 = service_membership.getMembershipById(pk);
			
			HttpSession sessionUsed = (HttpSession) facesContext.getExternalContext().getSession(false);
			
			HttpSession otherSession= null;
			
			/*
			 * sessionUsed.invalidate();
			 * sessionUsed = (HttpSession) facesContext.getExternalContext().getSession(true);
			*/
			
			Hashtable<String, Hashtable<String,HttpSession>> ht = (Hashtable<String, Hashtable<String,HttpSession>>)sessionUsed.getServletContext().getAttribute("usuariosFirmados");
			
			if(ht == null)
			{
				sessionUsed.getServletContext().setAttribute("usuariosFirmados",new Hashtable());
				ht = (Hashtable<String, Hashtable<String,HttpSession>>)sessionUsed.getServletContext().getAttribute("usuariosFirmados");
			}
			
			Enumeration<String> enumKey = ht.keys();
			boolean flag= false;
			while(enumKey.hasMoreElements()) {
				
			    String key = enumKey.nextElement();
			    Hashtable<String,HttpSession> htVal = (Hashtable<String,HttpSession>)ht.get(key);
			    
			    if( htVal.get(member1.getPerson().getProspectus().getTracking_id()) != null ){
			    	flag=true;
			    	otherSession = htVal.get(member1.getPerson().getProspectus().getTracking_id());
			    	break;
			    }
			    
			}
			
			if( flag ){
				
		    	ht.remove(otherSession.getId());
		    	otherSession.invalidate();
				    
			}
		
		}
		
	}
	
	public void initSession(){
		
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "--- RegistroCoachPantallaUnica.initSession() --- coach: " + coach_id + "  prospectus_id_viewed : " + prospectus_id );
		System.out.println( "" );
		System.out.println( "" );
		System.out.println( "" );
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext context   = facesContext.getELContext();
		ELResolver resolver  = facesContext.getApplication().getELResolver();
		
		SessionBean sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id( Integer.parseInt( company_id )  );
		mpk.setProspectus_id( Integer.parseInt(coach_id) );
		
		Membership mCoach = service_membership.getMembershipById(mpk);
		
		mpk = new MembershipPK();
		
		mpk.setCompany_id( Integer.parseInt( company_id ) );
		mpk.setProspectus_id( Integer.parseInt(prospectus_id) );
		
		Membership mProspectus = service_membership.getMembershipById(mpk);
		
		HeaderBean headerbean = (HeaderBean)resolver.getValue(context, null, "headerBean");
		
		int i = 0;
		
		do {
			
			try{
			
				Thread.sleep(500);
				
				headerbean.iniciaSesionCoach(mCoach, mProspectus, true);
				
				sesion = (SessionBean)resolver.getValue(context, null, "sessionBean");
				
				sesion.setCoachProspectus_id(mCoach.getMembershipPK().getProspectus_id());
				
				sesion.setAccess_from("portalKubo");
				
				NavigationBeanIMP navi = (NavigationBeanIMP)resolver.getValue(context, null, "navigationBean");
				
				navi.setPaginaActual("registro/basicdata");
				
				i++;
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}while (sesion.getCompany_id() == null && i < 4 );
		
	
		
		
	}
	
	public String getSaludo(){
		return "iniciando";
	}

	public MembershipService getService_membership() {
		return service_membership;
	}

	public void setService_membership(MembershipService service_membership) {
		this.service_membership = service_membership;
	}
	
}
