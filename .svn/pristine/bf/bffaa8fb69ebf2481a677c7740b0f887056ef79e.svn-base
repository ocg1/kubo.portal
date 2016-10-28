package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.token.Token;
import mx.com.kubo.token.TokenVerification;

@ManagedBean
@ViewScoped
public class GeneraToken implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService phoneService;
	
	protected SessionBean  sesion;
	
	private String token;
	private boolean envioToken;
	private boolean validToken;
	private boolean blockedToken = false;
	private String msg;
	private int status=0;
	private String cell_num;
	
	@PostConstruct
	public void init(){
		status=0;
		envioToken=false;
		validToken=false;
		msg = "";
		
		FacesContext faces  = FacesContext.getCurrentInstance();
		ELResolver resolver	= faces.getApplication().getELResolver();
		ELContext context	= faces.getELContext();
		
		sesion = (SessionBean)resolver.getValue(context, null, "sessionBean");
		
		initPhone();
		
	}
	
	public void generaToken(){
		System.out.println("Generando TOKEN");
		
		Token t = new Token();
		
		boolean b = t.creaToken( sesion.getProspectus_id() , 1, 1);
		
		if( b ){
			envioToken=true;
		}else{
			envioToken=false;
		}
		
		status=1;
		validToken=false;
		blockedToken = false;
		msg = "";
		
	}
	
	public void validaToken(){
		
		//System.out.println("Validando TOKEN: "+token);
		
		Token t = new Token();
		
		TokenVerification tv = t.verificaToken( sesion.getProspectus_id() , 1, 1,token);
		
		System.out.println( "Valido: " + tv.isValid()  );
		
		System.out.println( "Mensaje: " + tv.getMessage() );
		
		if( tv.isValid() ){
			validToken=true;
		}else{
			validToken=false;
		}
		
		if( !tv.isBloqued() ){
			
			blockedToken = false;
			
		}else{
			
			blockedToken = true;
			
		}
		
		envioToken=false;
		status=2;
		msg = tv.getMessage();
		
		
		
	}
	
	public void muestraGeneraToken(){
		System.out.println("Muestra Genera Token");
		status=0;
		envioToken=false;
		validToken=false;
		blockedToken = false;
		msg = "";
	}
	
	public void muestraIntroduceToken(){
		System.out.println("Muestra Introduce Token");
		status=1;
		validToken=false;
		envioToken=true;
		blockedToken = false;
		msg = "";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isEnvioToken() {
		return envioToken;
	}

	public void setEnvioToken(boolean envioToken) {
		this.envioToken = envioToken;
	}

	public boolean isValidToken() {
		return validToken;
	}

	public void setValidToken(boolean validToken) {
		this.validToken = validToken;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	private void initPhone(){
		
		if( phoneService != null && sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
			
			Phone  phone = phoneService.getPhoneByTypeByArea(sesion.getProspectus_id(), sesion.getCompany_id(), 6, sesion.getArea());
			
			if( phone != null && phone.getPhone_number() != null ){
				cell_num = "**** " + phone.getPhone_number().substring( (phone.getPhone_number().length() - 4) );
			}else{
				if ( phone == null ){
					
					cell_num = "no existe el telefono";
					
				}else if ( phone.getPhone_number() == null ){
					cell_num = "no existe el numero telefono";
				}
			}
		}
	}

	public String getCell_num() {
		return cell_num;
	}

	public void setCell_num(String cell_num) {
		this.cell_num = cell_num;
	}

	public PhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public boolean isBlockedToken() {
		return blockedToken;
	}

	public void setBlockedToken(boolean blockedToken) {
		this.blockedToken = blockedToken;
	}
	
}
