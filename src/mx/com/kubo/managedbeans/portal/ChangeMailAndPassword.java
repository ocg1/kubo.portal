package mx.com.kubo.managedbeans.portal;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ChangeMailAndPassword implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String email;

	@PostConstruct
	public void init(){
		System.out.println("");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
