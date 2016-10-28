package mx.com.kubo.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ShowChangeSession;
import mx.com.kubo.model.Change_control;

@ManagedBean
@RequestScoped
public class ShowChanges {
	private List<Change_control> lstChanges;
	
	@PostConstruct
	public void init(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		ShowChangeSession showchange= (ShowChangeSession) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "showChangeSession");			
		lstChanges=showchange.getLstChanges();
	}

	public List<Change_control> getLstChanges() {
		return lstChanges;
	}

	public void setLstChanges(List<Change_control> lstChanges) {
		this.lstChanges = lstChanges;
	}
	
	

}
