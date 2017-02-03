package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;



@ManagedBean
@ViewScoped
public class EFL_Redirec implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	private String url_str_1;
	private String url_redirec;
	private String url_str_2;
	
	@PostConstruct
	public void init(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ELContext elContext = facesContext.getELContext();
		
		SessionBean sesion = (SessionBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "sessionBean");
		
		generaURL( sesion.getProspectus_id() +"", sesion.getCompany_id()+"" );
		
		actualizaViewScreen( sesion );
		
	}
	
	private void actualizaViewScreen( SessionBean sesion ){
		
		Scoring score = scoringservice.loadMaxScoringByProspectus(sesion.getProspectus_id(), sesion.getCompany_id());
		
		score.setScreen_viewed(1);
		
		scoringservice.updateScoring(score);
		
		Access access = new Access();
		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		access.setScreen_id(76);
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
		
		//Registra en PRACCESS
	}
	
	public void registraRedirect(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		ELContext elContext = facesContext.getELContext();
		
		SessionBean sesion = (SessionBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "sessionBean");
		
		Access access = new Access();
		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		access.setScreen_id(78);
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
	
	private void generaURL( String prospectus_id, String company_id ){
		
		// https://kubofinanciero.eflglobal.com/hostedPlayer.html?externalKey=MTIzNDU=&redirectURL=aHR0cHM6Ly9rdWJvZmluYW5jaWVyby5sZWFkcGFnZXMuY28vZWZsLWdyYWNpYXMv
		
		ProyectLoan proyect = proyectLoanService.getMaxProyectLoanByProspect( Integer.parseInt( prospectus_id ), Integer.parseInt( company_id ) );
		
		String url = "https://kubofinanciero.eflglobal.com/hostedPlayer.html?externalKey="  ;
		
		String encode = Utilities.decodeBase64( prospectus_id );
		
		System.out.println( "decodeBase64 " + prospectus_id +" : " + encode );
		
		url += encode;
		
		//System.out.println( "encodeBase64 " + encode +" : " +  Utilities.encodeBase64( encode ) );
		
		//url += Utilities.encodeBase64( prospectus_id );
		
		url_str_2 = "https://www.kubofinanciero.com/Kubo/Portal/efl2.xhtml?utm_source=EFL_PSYCHOMETRIC&utm_medium=PSYCHOMETRIC&prospectus_id=" + encode;
		
		String url_return = Utilities.decodeBase64( url_str_2 );
		
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		
		url += "&redirectURL="+url_return;
		
		if( proyect != null && proyect.getPerson() != null && proyect.getPerson().getDate_of_birth() != null  ){
		
			url += "&birthday=" 	+  Utilities.decodeBase64(  format.format(proyect.getPerson().getDate_of_birth())  ) ; // -  fecha de nacimiento en formato YYYY-MM-DD
		
		}
		
		url += "&loanAmount=" 	+  Utilities.decodeBase64( proyect.getAmmount()+"" ) ; // - monto del préstamo en pesos, es un valor entero >= 1
		url += "&currency=" 	+  Utilities.decodeBase64("MXN");// - es la cadena 'MXN'
		url += "&analyticsId="  +  Utilities.decodeBase64( getUUID() );
		url += "&language=ZXMtTVgteC1pbmZvcm1hbA==";
		
		url_str_1 = url;
		
		System.out.println( "url:  " + url_str_1 );
		
		SystemParamPK system_param_PK_I = new SystemParamPK();
		
		system_param_PK_I.setCompany_id( 1 );
		system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
		
		SystemParam system_param_I = systemparamservice.loadSelectedSystemParam(system_param_PK_I);
		
		if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
		
			if( proyect.getPerson().getProspectus().getHs_vid() != null ){
				try{
					actualizaHS_VID( proyect.getPerson().getProspectus().getHs_vid() );
				}catch(Exception e){
					System.out.println( "No encontrado en HUBSPOT:" + e.getMessage() );
				}
				
			}
			
		}
		
	}

	public String getUrl_str_1() {
		return url_str_1;
	}

	public void setUrl_str_1(String url_str_1) {
		this.url_str_1 = url_str_1;
	}

	public String getUrl_redirec() {
		return url_redirec;
	}

	public void setUrl_redirec(String url_redirec) {
		this.url_redirec = url_redirec;
	}

	public String getUrl_str_2() {
		return url_str_2;
	}

	public void setUrl_str_2(String url_str_2) {
		this.url_str_2 = url_str_2;
	}
	
	private String getUUID( ){
		
		String res= "";
		
		UUID id = UUID.randomUUID();
	    
		res =  String.valueOf(id);
		System.out.println( res );
		
		return res;
		
	}
	
	/* private String decoderBase64( String word ){
		try{
			 BASE64Decoder decoder = new BASE64Decoder();
			String res = new String(decoder.decodeBuffer(word), "UTF-8");
		
		System.out.println("decoderBase64  '"+ word + "':" + res);
		
		return res;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}
	
	
	private String getEncryptString(String str) {
		String res = "";
	    BASE64Encoder base64en = new BASE64Encoder();
	    try {
			
			res = base64en.encodeBuffer( str.getBytes() );
			
			System.out.println("encoderBase64  '"+ str + "':" + res);
			
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    
	    return res;
	    
	} */

	public ScoringService getScoringservice() {
		return scoringservice;
	}

	public void setScoringservice(ScoringService scoringservice) {
		this.scoringservice = scoringservice;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}

	public ProyectLoanService getProyectLoanService() {
		return proyectLoanService;
	}

	public void setProyectLoanService(ProyectLoanService proyectLoanService) {
		this.proyectLoanService = proyectLoanService;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}
	 
	
	
	protected void actualizaHS_VID( Integer hs_vid ){
		
				//TODO TRAE utm_source utm_medium  registration_reason
				
				HubSpotController hs  = new HubSpotController();
				
				StringBuilder properties = new StringBuilder();
					
				properties.append("{ \"property\" : \"contacto_efl\" ,\"value\":\"si\" }");
					
				hs.updateProspectus(hs_vid, properties);
				
		}
	
}
