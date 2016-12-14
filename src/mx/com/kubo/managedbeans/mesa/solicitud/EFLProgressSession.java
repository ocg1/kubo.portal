package mx.com.kubo.managedbeans.mesa.solicitud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.com.kubo.bean.ProgressEFL;

@ManagedBean
@SessionScoped
public class EFLProgressSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProgressEFL> eflprogresslist;
	
	@PostConstruct
	public void init(){
		
	}

	public List<ProgressEFL> getEflprogresslist() {
		return eflprogresslist;
	}

	public void setEflprogresslist(List<ProgressEFL> eflprogresslist) {
		this.eflprogresslist = eflprogresslist;
	}
	
}
