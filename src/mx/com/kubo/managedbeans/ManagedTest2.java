package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ManagedTest2 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mail;
	private String pwd;
	
	@PostConstruct
	public void init(){
		
		System.out.println("Iniciando ManagedTest --");
		
	}
	
	public void recuperaDatos(){
		
		System.out.println( "mail: " + mail);
		System.out.println( "password:  " + pwd);
		
		RequestContext request = RequestContext.getCurrentInstance();
		
		request.addCallbackParam("mensaje", "este es un mensaje de regreso");
		
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		System.out.println("valor que se asigna a mail: "+mail);
		this.mail = mail;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		System.out.println("valor que se asigna a pwd: "+pwd);
		this.pwd = pwd;
	}
	

}
