package mx.com.kubo.managedbeans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import java.net.HttpCookie;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.json.JSONObject;

import mx.com.kubo.bean.HS_OBJ;
import mx.com.kubo.constantes.NavigationRule;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.managedbeans.portal.IniciaSession;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TimeLog;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TimeLogService;
import mx.com.kubo.tools.GeneradorCodigos;
import mx.com.kubo.tools.Utilities;

@ManagedBean
@ViewScoped
public class Hs_Init implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService prospectusService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{timeLogServiceImp}")
	protected TimeLogService timelogservice;
	
	
	private final int PEDIR_CONTRASENA_SEGURA = 101;
	private String nombre;
	private String email;
	private String pass;
	
	private String message = "";
	private String url_str = ""; 
	private int status_hs = 0;
	
	private boolean pide_contrasena_segura;
	
	private String ipAddressClient;
	
	protected Membership member;
	
	@PostConstruct
	public void init(){
		
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		
		HttpServletRequest http_request = (HttpServletRequest) external.getRequest();
		
		ipAddressClient  = http_request.getHeader("X-FORWARDED-FOR");
		
		SystemParamPK system_param_PK = new SystemParamPK();
		
		system_param_PK.setCompany_id( 1 );
		system_param_PK.setSystem_param_id(PEDIR_CONTRASENA_SEGURA);
		
		SystemParam system_param = systemparamservice.loadSelectedSystemParam(system_param_PK);
						
		if(system_param != null && system_param.getValue() != null )
		{		
			setPide_contrasena_segura(system_param.getValue().equals("S"));	
		}
		
		if(ipAddressClient == null)
		{
	    	ipAddressClient = http_request.getRemoteAddr();
		}
		
		Map<String, String> request_map = external.getRequestParameterMap();
		
		message = "";
		url_str = "";
		
		email = request_map.get("hs_email");
		
		if( email != null ){
			
			member = membershipService.getMembershipByEmail(email);
			
			if( member != null ){
				
				if( member.getIs_active() != null && member.getIs_active().intValue() == 0 ){
				
					nombre = "";
					
					if( member.getPerson().getFirst_name() != null && member.getPerson().getFirst_name().trim().length() > 0)
					{
						nombre = member.getPerson().getFirst_name();
					}
					
					if(member.getPerson().getMiddle_name() != null && member.getPerson().getMiddle_name().trim().length() > 0)
					{
						nombre += " "+member.getPerson().getMiddle_name();
					}
					
					if(nombre.trim().length() == 0 ){
						nombre = null;
					}
				
				}else{
					//TODO  redirigir a Home con iniciarsesion=true
					url_str = "iniciarSesion=true&email_access="+email;
					status_hs = 1;
				}
				
			}else{
				//TODO  no encuentra el correo en la base
				url_str = "sm_email="+email+"&utm_source=HSP";
				status_hs = 2;
			}
			
		}else{
			//TODO  no llega el hs_mail en la url
			
			SystemParamPK sppk =  new SystemParamPK(96, 1);  // Bandera que indica si esta habilitada la conección con HUBSPOT
			
			SystemParam sp = systemparamservice.loadSelectedSystemParam(sppk);
			
			if( sp != null && sp.getValue() != null && sp.getValue().equals("S") ){
			
				HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
				
				initHS( request );
			
			}else{
				url_str = "utm_source=HSP";
				status_hs = 2;
			}
			
		}
		
	}
	
	public void login(){
		
		FacesContext faces   = FacesContext.getCurrentInstance();
		ELContext context  = faces.getELContext();		
		ELResolver resolver = faces.getApplication().getELResolver();
		
		
		
		IniciaSession inicio = (IniciaSession) resolver.getValue(context, null, "iniciaSession");
		inicio.setEmail(member.getEmail());
		inicio.setPassword(pass);
		inicio.setCheckLogin(false);
		inicio.iniciaSesion();
		
	}
	
	public String savePass(){
		
		try{
			
			member.setPassword(Utilities.encrypt(pass));
			member.setIs_active(1);
			
			if( isPide_contrasena_segura() ){
				member.setIs_client_pass("S");
			}else{
				member.setIs_client_pass("N");
			}
			
			membershipService.update(member);
			
			PasswordHistoryPK pssPk = new PasswordHistoryPK();
			PasswordHistory passHis = new PasswordHistory(); 
			
			pssPk.setCompany_id( member.getMembershipPK().getCompany_id() );			
			pssPk.setProspectus_id( member.getMembershipPK().getProspectus_id() );
								
			passHis.setDate_changed(new Date());
			passHis.setPassword( member.getPassword() );
			passHis.setPwdhpk( pssPk );
			
			passwordhistoryservice.savePasswordHistory(passHis);
			
			ProspectusPK ppk = new ProspectusPK(member.getMembershipPK().getProspectus_id(), member.getMembershipPK().getCompany_id());
			
			Prospectus prospectus =  prospectusService.getProspectusById(ppk);
			
			prospectus.setTracking_id( generaTracking_id ( member.getMembershipPK().getCompany_id(),member.getMembershipPK().getProspectus_id() ) );
			
			prospectusService.update(prospectus);
			
			FacesContext faces   = FacesContext.getCurrentInstance();
			ELContext context  = faces.getELContext();		
			ELResolver resolver = faces.getApplication().getELResolver();
			
			IniciaSession inicio = (IniciaSession) resolver.getValue(context, null, "iniciaSession");
			inicio.setEmail(member.getEmail());
			inicio.setPassword(pass);
			inicio.setCheckLogin(false);
			inicio.iniciaSesion();
			
			Simulator simulator = (Simulator) resolver.getValue(context, null, "simulator");
			
			simulator.setAmmount(0D);
			simulator.simulaCred(false);
			
			return NavigationRule.REGISTRAR.toString();
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}
	
	private void initHS( HttpServletRequest request ){
		
		String cookieVal = getCookieRequest( request ); 
		
		Integer hs_vid  = null;
		
		//cookieVal = "baa0d64325060715408289c8e07bf573";
		
		//cookieVal = "2c87c37948cd7ad94762cec4cb09fbf1";
		
		if( cookieVal != null ){
		
			String hs_email = null;
			
			int w = 0;
			
			while( hs_email == null && w < 5 ){
				
				w++;
				
				hs_vid =  getHSVID( cookieVal);
			
				if( hs_vid != null ){
				
					hs_email =  getEmailHS( hs_vid);
			
					if( hs_email == null ){
						
						try{
							
							saveTimelog( "coockie:"+cookieVal, " Intento ERROR: " + w + " vid: " + hs_vid , new Date() , null  );
							
							Thread.sleep(3000);
							
						}catch( InterruptedException i ){
							
							System.out.println( i.getMessage() );
							
						}catch(Exception e){
							
							System.out.println( e.getMessage() );
							
						}
					
					}
					
				}else{
					try{
						
						saveTimelog( "coockie:"+cookieVal, " Intento ERROR: " + w + " vid: " + hs_vid , new Date() , null  );
						
						Thread.sleep(3000);
						
					}catch( InterruptedException i ){
						
						System.out.println( i.getMessage() );
						
					}catch(Exception e){
						
						System.out.println( e.getMessage() );
						
					}
				}
				
			}
			
			
			
			if( hs_email != null ){
			
				saveTimelog( "coockie:"+cookieVal, " Intento EXITO " + w + " vid: " + hs_vid , new Date() , null  );
				
				member = null;
				
				int y = 0;
				
				while( member == null && y < 1 ){
				
					y++;
					
					member = membershipService.getMembershipByEmail(hs_email);
				
					if( member == null ){
						try{
							
							Thread.sleep(1000);
							
						}catch(Exception e){
							
						}
					
					}
					
				}
				
				if( member != null ){
					
					email = member.getEmail();
					
					if( member.getIs_active() != null && member.getIs_active().intValue() == 0 ){
					
						nombre = "";
						
						if( member.getPerson().getFirst_name() != null && member.getPerson().getFirst_name().trim().length() > 0)
						{
							nombre = member.getPerson().getFirst_name();
						}
						
						if(member.getPerson().getMiddle_name() != null && member.getPerson().getMiddle_name().trim().length() > 0)
						{
							nombre += " "+member.getPerson().getMiddle_name();
						}
						
						if(nombre.trim().length() == 0 ){
							nombre = null;
						}
					
					}else{
						//TODO  redirigir a Home con iniciarsesion=true
						url_str = "iniciarSesion=true&email_access="+email;
						status_hs = 1;
					}
					
				}else{
					
					HubSpotController hsc  = new HubSpotController();
					
					String json_str =hsc.getJSONProperties( hs_vid );
					
					HS_OBJ hs_obj =hsc.createHSOBJ( json_str );
					
					altaUsuario( hs_obj );
					
				}
				
			}else{
				
				saveTimelog( "coockie:"+cookieVal, " Intento ERROR " + w + " vid: " + hs_vid , new Date() , null  );
				
				//TODO  no encuentra el correo en la base
				url_str = "utm_source=HSP";
				status_hs = 2;
				
			}
		
		}else{
			message = "";
			status_hs = 3;
		}
		
	}
	
	
	private boolean altaUsuario( HS_OBJ hs_obj ){
		
		if( hs_obj != null ){
			
			String tipo = validaTipoCliente( hs_obj.getV_id().intValue() );
			
			if( tipo != null && tipo.equals("inversionista") ){
			
				hs_obj.setArea("I");
				
			}else{
				
				hs_obj.setArea("L");
			
			}
			
			Prospectus exist_VID = prospectusService.getProspectusByHSId( Integer.parseInt( hs_obj.getV_id() + "" ) );
			
			Membership  exist_EMAIL = membershipService.getMembershipByEmail( hs_obj.getEmail_value() );
			
			if( exist_VID == null  &&  exist_EMAIL == null   ){
				
				if ( prospectusService.altaProspectoHS( hs_obj ) ) {
				
					member = membershipService.getMembershipByEmail( hs_obj.getEmail_value() );
					
					email = member.getEmail();
					
					HubSpotController hs = new HubSpotController();
					
					String properties = "{ \"property\" : \"alta_kubo\" ,\"value\":\"S\" }";
					
					hs.updateProspectus( hs_obj.getV_id().intValue() , new StringBuilder( properties ) );
					
				}
				
				return true;
			
			}else{
				
				Integer pros = 0;
				
				if( exist_VID != null ){
					
					pros = exist_VID.getProspectusPK().getProspectus_id();
					
				}else if( exist_EMAIL != null ){
					
					pros = exist_EMAIL.getMembershipPK().getProspectus_id();
					
				}
				
				hs_obj.setProspectus_id(pros);
				
				if (prospectusService.updateProspectoHS( hs_obj ) ){
				
					HubSpotController hs = new HubSpotController();
					
					String properties = "{ \"property\" : \"alta_kubo\" ,\"value\":\"S\" }";
					
					hs.updateProspectus(  hs_obj.getV_id().intValue() , new StringBuilder( properties ) );
				
				}
				
				member = membershipService.getMembershipByEmail( hs_obj.getEmail_value() );
				
				if( member != null ){
				
					email = member.getEmail();
					
					url_str = "iniciarSesion=true&email_access="+member.getEmail();
					status_hs = 1;
				
				}else{
					
					url_str = "utm_source=HSP";
					status_hs = 2;
					
				}
				
				return true;
			}
			
		
		}else{
			
			return false;
			
		}
	
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Membership getMember() {
		return member;
	}

	public void setMember(Membership member) {
		this.member = member;
	}

	public PasswordHistoryService getPasswordhistoryservice() {
		return passwordhistoryservice;
	}

	public void setPasswordhistoryservice(PasswordHistoryService passwordhistoryservice) {
		this.passwordhistoryservice = passwordhistoryservice;
	}
	
	private String generaTracking_id( int company_id, int prospectus_id ){
		
		String tracking = "";
		
		tracking = GeneradorCodigos.get_tracking_id(company_id, prospectus_id);
		
		return tracking;
		
	}

	public ProspectusService getProspectusService() {
		return prospectusService;
	}

	public void setProspectusService(ProspectusService prospectusService) {
		this.prospectusService = prospectusService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl_str() {
		return url_str;
	}

	public void setUrl_str(String url_str) {
		this.url_str = url_str;
	}

	public int getStatus_hs() {
		return status_hs;
	}

	public void setStatus_hs(int status_hs) {
		this.status_hs = status_hs;
	}
	
	// HubSpotController
	
	private String validaTipoCliente( Integer vid_ ){
		
		HttpURLConnection con = null;
		String 	hs_email = null;
		
		
		try{
		
			HubSpotController hsc  = new HubSpotController();
			
			String json =hsc.getJSONProperties(vid_);
			
		    hs_email = getClientTypeFromHS( json );
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
		  
		  if(con != null)
			  con.disconnect();
	  }
	
		if( hs_email != null && hs_email.trim().length() == 0 ){
			hs_email = null;
		}
	 
	return hs_email;
		
	}
	
	private String getEmailHS( Integer vid_){
		
		HttpURLConnection con = null;
		String 	hs_email = null;
		
		
		try{
		
			HubSpotController hsc  = new HubSpotController();
			
			String json =hsc.getJSONProperties(vid_);
			
			
			
		    hs_email = getMailFromHS( json );
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
		  
		  if(con != null)
			  con.disconnect();
	  }
	
		if( hs_email != null && hs_email.trim().length() == 0 ){
			hs_email = null;
		}
	 
	return hs_email;
		
	}
	
	private Integer getHSVID( String cookie ){
		
		HttpURLConnection con = null;
		Integer 	vid = null;
		
		
		try{
		
		String url="http://api.hubapi.com/contacts/v1/contact/utk/"+cookie+"/profile/?hapikey=ab5f1f2f-bc79-4cbb-a280-e53c182b7f8d";
		URL object=new URL(url);
		
		String charset = "UTF-8";

		con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		
		con.setRequestProperty("Accept-Charset", charset);
		con.setRequestProperty("Content-Type", "application/json;charset="+charset);
		
		con.setRequestMethod("GET");
		
		//String  s  = "";
		
		//System.out.println(s);
		
		/*OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		
		wr.write( s );
		wr.close(); */
		
		//display what returns the POST request

		StringBuilder sb = new StringBuilder();  
		int HttpResult = 0;
				
		try{
			
			HttpResult = con.getResponseCode();
			
			
		}catch( ConnectException ne ){
			
			System.out.println( "ConnectException: " + ne );
			
			ne.printStackTrace();
			
			vid = null;
			
			SystemParamPK sppk =  new SystemParamPK(96, 1);  // Bandera que indica si esta habilitada la conección con HUBSPOT
			
			SystemParam sp = systemparamservice.loadSelectedSystemParam(sppk);
			
			sp.setValue("N");
			
			systemparamservice.updateSelectedSystemParam(sp);
			
			try{
				
				NotificadorConfigRequest request_notificar_config = new NotificadorConfigRequest();
				request_notificar_config.setCompany_id("1");
				request_notificar_config.setProspectus_id("0");					
				request_notificar_config.setCalled_FROM("PortalKubo.HS_init()");
				request_notificar_config.setEvento_id("2");
				request_notificar_config.setFecha_deposito("El servicio de HUBSPOT no está disponible");
				request_notificar_config.setMonto_deposito("ERROR COMUNICACIÓN CON HUBSPOT");
				
				PublicProyectServiceLocator locator = new PublicProyectServiceLocator();
				
				PublicProyect kubo_services = locator.getPublicProyect();
				
				WsResponse response = kubo_services.notificar(request_notificar_config);
				
			}catch( Exception e ){
				
				e.printStackTrace();
				
			}
			
			
			
		}
		
		System.out.println(HttpResult);
		
		if (HttpResult == HttpURLConnection.HTTP_OK ) {
			
		    BufferedReader br = new BufferedReader(
									            	new InputStreamReader(con.getInputStream(), charset)
									            );
		    
		    String line = null;  
		    
		    while ((line = br.readLine()) != null) {  
		        
		    	sb.append(line + "\n");
		    	
		    }
		    
		    br.close();
		    System.out.println("" + sb.toString());
		    
		    //JSONObject jsonObj = new JSONObject( sb.toString() );
		    
		    JSONObject json = new JSONObject( sb.toString() );
			
		    vid = (Integer) json.get("vid");
			
			//hs_email =getMailFromHS( sb.toString() );
		    
		} else {
			
		    if( con != null ){
		    	
		    	System.out.println(con.getResponseMessage());
		    	
		    }
		    
		}  
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
		  
		  if(con != null)
			  con.disconnect();
	  }
	
	 
	return vid;
			
		
	}

	private String getCookieRequest( HttpServletRequest request ){
		
		System.out.println( "");
		System.out.println( "********************getCookieRequest*********************************");
		
		Cookie[] cookies = request.getCookies();
		
		// java.net.HttpCookie[] kookies = request.getHeaders(arg0);
		
		Enumeration<String> e = request.getHeaderNames();
		
		while (e.hasMoreElements()) {
			
			System.out.println( e.nextElement() ); 
			
		}
		
		String hsutk = null;
		
		  if(cookies != null) {
		      for (int i = 0; i < cookies.length; i++) {
		         
		    	  Cookie  cookie=cookies[i];
		          
		    	  String cookieName = cookie.getName();
		          String cookieValue = cookie.getValue();
		          String domain = cookie.getDomain();
		          
		          
		          if(  cookieName != null && cookieValue != null ){
		          
		        	 
		        	  
			          if( cookieName.equals("hubspotutk")  ){
			        	  
			        	 
				          /*
				          System.out.println( "*********************************************************");
			        	  System.out.println( "*********************************************************");
			        	  System.out.println( "*********************************************************");
			        	  System.out.println( "cookieName: " +  cookieName);
			        	  System.out.println( "cookieValue: " + cookieValue );
			        	  System.out.println( "domain: " +  domain );
			        	  System.out.println( "getComment(): " + cookie.getComment() );
			        	  System.out.println( "getDomain(): " + cookie.getDomain() );
			        	  System.out.println( "getMaxAge(): " + cookie.getMaxAge() );
			        	  System.out.println( "getName(): " + cookie.getName() );
			        	  System.out.println( "getPath(): " + cookie.getPath() );
			        	  System.out.println( "getValue(): " + cookie.getValue() );
			        	  System.out.println( "getVersion(): " + cookie.getVersion() );
			        	  System.out.println( "getSecure(): " + cookie.getSecure() );
			        	  
			        	  
			        	  System.out.println( "*********************************************************");
			        	  System.out.println( "*********************************************************");
			        	  System.out.println( "*********************************************************");
			        	  */
			        	  hsutk = cookieValue;
			        	  break;
			        	  
			          }
		          
		          }
		          
		       }
		   }
		
		
		  return hsutk;
		  
	}
	
	private String getMailFromHS( String json_str ){
		
		try{
			
			String email = null;
			
			JSONObject json = new JSONObject( json_str );
			
			JSONObject prop =  (JSONObject)json.get("properties");
			
			
			email = getValueElementForJSON( prop, "email" ) ;
			
			return email;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
private String getClientTypeFromHS( String json_str ){
		
		try{
			
			String tipo_cliente = null;
			
			JSONObject json = new JSONObject( json_str );
			
			JSONObject prop =  (JSONObject)json.get("properties");
			
			
			tipo_cliente = getValueElementForJSON( prop, "tipo_cliente" ) ;
			
			return tipo_cliente;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
		
		protected String getValueElementForJSON( JSONObject json_obj, String element ){
			try{
				
				JSONObject prop =  json_obj.getJSONObject(element);
						//get(element);
				
				String res= null;
				
				if( prop != null ){
				
					res = (String)prop.get("value");
					
				}
	
				return res;
				
			}catch(Exception e){
				
				e.printStackTrace();
				return null;
			}
			
		}

		public TimeLogService getTimelogservice() {
			return timelogservice;
		}

		public void setTimelogservice(TimeLogService timelogservice) {
			this.timelogservice = timelogservice;
		}
		
		public boolean saveTimelog( String identify_str, String description , Date init_time , Date final_time  ){
			
			TimeLog timelog = new TimeLog();
			
			timelog.setDescription(description);
			
			timelog.setInit_time(init_time);
			timelog.setFinal_time(final_time);
			
			if( init_time != null && final_time != null ){
				
				//Date d = new Date( final_time.getTime() - init_time.getTime() );
			
				long l = final_time.getTime() - init_time.getTime();
				
				Long seg = l/1000;
				
				long m = l%1000;
				
				Long min =  Long.valueOf((seg.intValue()))/60;
				
				seg = Long.valueOf((seg.intValue()))%60;
				
				String str =  min.intValue()+"m " + seg +"s " + m+"ms";
				
				timelog.setTotal_lapse(str);
			
			}
			
			timelog.setIdentify_str(identify_str);
			
			if( member != null && member.getMembershipPK() != null && member.getMembershipPK().getProspectus_id() != 0 ){
			
				timelog.setProspectus_id(member.getMembershipPK().getProspectus_id());
				
			}
			
			timelog.setIp_address(ipAddressClient);
			
			return timelogservice.saveTimeLog(timelog);
			
		}

		public SystemParamService getSystemparamservice() {
			return systemparamservice;
		}

		public void setSystemparamservice(SystemParamService systemparamservice) {
			this.systemparamservice = systemparamservice;
		}

		public boolean isPide_contrasena_segura() {
			return pide_contrasena_segura;
		}

		public void setPide_contrasena_segura(boolean pide_contrasena_segura) {
			this.pide_contrasena_segura = pide_contrasena_segura;
		}
	
}

