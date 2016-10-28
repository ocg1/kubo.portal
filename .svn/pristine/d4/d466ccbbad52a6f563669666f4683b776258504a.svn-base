package mx.com.kubo.managedbeans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.json.JSONObject;

import mx.com.kubo.constantes.NavigationRule;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.managedbeans.portal.IniciaSession;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.ProspectusService;
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
	
	private String nombre;
	private String email;
	private String pass;
	
	private String message = "";
	private String url_str = ""; 
	private int status_hs = 0;
	
	protected Membership member;
	
	@PostConstruct
	public void init(){
		
		ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
		
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
			
			HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
			
			initHS( request );
			
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
			
			return NavigationRule.REGISTRAR.toString();
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}
	
	private void initHS( HttpServletRequest request ){
		
		String cookieVal = getCookieRequest( request ); 
		
		if( cookieVal != null ){
		
			String hs_email = null;
			
			int w = 0;
			
			while( hs_email == null && w < 5 ){
				
				w++;
				
				Integer hs_vid =  getHSVID( cookieVal);
			
				hs_email =  getEmailHS( hs_vid);
			
				if( hs_email == null ){
					try{
						
						Thread.sleep(1000);
						
					}catch(Exception e){
						
					}
				
				}
				
			}
			
			if( hs_email != null ){
				
				member = null;
				
				int y = 0;
				
				while( member == null && y < 5 ){
				
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
					
				}
				
			}
		
		}else{
			message = "";
			status_hs = 3;
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
	
	private String getEmailHS( Integer vid_){
		
		HttpURLConnection con = null;
		String 	hs_email = null;
		
		
		try{
		
		
			HubSpotController hsc  = new HubSpotController();
			
			String json =hsc.getJSONProperties(vid_);
			
		   hs_email =getMailFromHS( json );
		    
		  
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
		  
		  if(con != null)
			  con.disconnect();
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
		
		String  s  = "";
		
		System.out.println(s);
		
		/*OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		
		wr.write( s );
		wr.close(); */
		
		//display what returns the POST request

		StringBuilder sb = new StringBuilder();  
		int HttpResult = con.getResponseCode();
		
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
		    
			System.out.println(con.getResponseMessage());
		    
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
		
		Cookie[] cookies = request.getCookies();
		
		String hsutk = null;
		
		  if(cookies != null) {
		      for (int i = 0; i < cookies.length; i++) {
		         
		    	  Cookie  cookie=cookies[i];
		          
		    	  String cookieName = cookie.getName();
		          String cookieValue = cookie.getValue();
		          
		          if( cookieName.equals("hubspotutk") ){
		        	  hsutk = cookieValue;
		        	  break;
		          }
		          
		       }
		   }
		
		
		  return hsutk;
		  
	}
	
	private String getMailFromHS( String json_str ){
		
		try{
			
			String email = "";
			
			JSONObject json = new JSONObject( json_str );
			
			JSONObject prop =  (JSONObject)json.get("properties");
			
			
			email = getValueElementForJSON( prop, "email" ) ;
			
			return email;
			
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
	
}
