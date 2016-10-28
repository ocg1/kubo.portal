package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ManagedTest implements Serializable{
	
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
		
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
		System.out.println( "1mail: " + mail);
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
		System.out.println( "1pass: " + pwd);
	}
	

}
