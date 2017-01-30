package mx.com.kubo.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.managedbeans.registro.publicacion.DealIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.SystemParamService;

@ManagedBean
@ViewScoped

public class Rechazado implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	protected boolean retomar = false;
	
	private String action;
	
	private ProyectLoan pyln;
	
	protected SessionBean sesion;
	
	FacesContext	faces     ; 
	ELContext 		elContext ;
	ELResolver 		resolver  ;	
	ExternalContext external  ;
	
	@PostConstruct
	public void init(){
		
		faces     = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();	
		external  = faces.getExternalContext();
						
		sesion  = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if( sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
		
			SystemParamPK spk = new SystemParamPK(54, 1);
			
			SystemParam sys = systemparamservice.loadSelectedSystemParam( spk );
			
			pyln = proyectloanservice.getMaxProyectLoanByProspect( sesion.getProspectus_id() , sesion.getCompany_id() );
			
			initAccess();
			
			if( pyln != null && pyln.getStatus_id() != null && pyln.getStatus_id().intValue() != 0 && pyln.getStatus_id().intValue() != 1 && pyln.getStatus_id().intValue() != 2 && pyln.getStatus_id().intValue() != 3 ){
				
				if( pyln.getConsulting_date() != null ){
					
					
					Calendar cNow = Calendar.getInstance();
					cNow.setTime(  new Date() );
					
					Calendar cCon = Calendar.getInstance();
					
					cCon.setTime( pyln.getConsulting_date() );
					
					cCon.add(Calendar.DATE, Integer.parseInt( sys.getValue() ));
					
					if( cNow.after(cCon) ){
						
						retomar = true;
						
					}
					
					
				}else{
					
					redirectRegistro();
					return;
				}
				
			}else{
				
				redirectRegistro();
				return;
			}
		
		}else{
			
			isSesion_DISABLED();
			
		}
		
	}
	
	private void initAccess(){
		
		Access access = new Access();
		
		access.setCompany_id( sesion.getCompany_id() );
		access.setProspectus_id( sesion.getProspectus_id() );
		access.setScreen_id(74); //Rechazo Automático
		access.setPercentage(0);
		access.setWeb_browser        (sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system          (sesion.getOsbrawser());
		access.setHorizontal_size    (sesion.getBrowser_width());
		access.setVertical_size      (sesion.getBrowser_height());
		access.setIpaddress          (sesion.getIP_address_client());
		access.setUser_agent         (sesion.getUser_agent());
		access.setDevice_info        (sesion.getDevice_info());				
		access.setProspectus_id_coach(sesion.getCoachProspectus_id());
		access.setUrl_access		  (sesion.getUrl_access());
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access, true);
		
		Scoring score = scoringService.loadMaxScoringByProspectus(sesion.getProspectus_id(), sesion.getCompany_id());
		
		score.setScreen_viewed(1);
		
		scoringService.updateScoring(score);
		
	}
	
	public void redirectRegistroAction(){
		
		System.out.println( "Redirige a Registro" );
		faces     = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();	
		external  = faces.getExternalContext();
		
		redirectRegistro();
	}
	
	private boolean redirectRegistro()
	{
		boolean bandera = false;
		
																												
			String url = (getPath() + "/jsf/registro.xhtml");
							
			try 
			{
				System.out.println( "Redirigiendo desde NavigationBean: " + url);
				external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			bandera = true;
		
		
		return bandera;
	}
	
	private boolean isSesion_DISABLED()
	{
		boolean bandera = false;
		
																												
			String url = (getPath() + "/Portal/sesion-expirada.xhtml?redirecFrom=NavigationBean");
							
			try 
			{
				System.out.println( "Redirigiendo desde NavigationBean: " + url);
				external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			bandera = true;
		
		
		return bandera;
	}
	
	public void retomaSolicitud( AjaxBehaviorEvent evento ) {
		
		try{
			
			System.out.println("retomaSolicitud .... ");
			
			Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
			DealIMP deal = (DealIMP) viewMap.get("deal");
		
			//DealIMP deal  = (DealIMP) resolver.getValue(elContext, null, "deal");
			
			deal.init();
			
			deal.setProyect_loan_reasignable( pyln );
			
			deal.listener_reanudar_solicitud_consulta( evento );
			
			action = "registrar";
		
		}catch(Exception e){
			
			e.printStackTrace();
			
			action = "";
		}
		
	}
	
	private String getPath()
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}

	public ProyectLoanService getProyectloanservice() {
		return proyectloanservice;
	}

	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
		this.proyectloanservice = proyectloanservice;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}

	public ScoringService getScoringService() {
		return scoringService;
	}

	public void setScoringService(ScoringService scoringService) {
		this.scoringService = scoringService;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}

	public boolean isRetomar() {
		return retomar;
	}

	public void setRetomar(boolean retomar) {
		this.retomar = retomar;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public FacesContext getFaces() {
		return faces;
	}

	public void setFaces(FacesContext faces) {
		this.faces = faces;
	}

	public ELContext getElContext() {
		return elContext;
	}

	public void setElContext(ELContext elContext) {
		this.elContext = elContext;
	}

	public ELResolver getResolver() {
		return resolver;
	}

	public void setResolver(ELResolver resolver) {
		this.resolver = resolver;
	}

	public ExternalContext getExternal() {
		return external;
	}

	public void setExternal(ExternalContext external) {
		this.external = external;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}
