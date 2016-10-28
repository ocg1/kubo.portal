package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ConfigManaged implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String location;

	@PostConstruct
	public void init(){
		location = "https://www.kubofinanciero.com/Kubo/Portal/";
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
