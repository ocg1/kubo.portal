package mx.com.kubo.converters;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mx.com.kubo.model.ViewEconomicInfo;
import mx.com.kubo.services.ViewClientInfoService;

@ManagedBean(name="econConverter")
@RequestScoped
@FacesConverter(value="econConverter")
public class EconConverter implements Converter{
	
	@ManagedProperty("#{viewClientInfoServiceImp}")
	private ViewClientInfoService viewclientinfoservice;
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		 if (submittedValue==null || submittedValue.trim().equals("")) {  
	            return null;  
	        } else {
					ViewEconomicInfo economic=viewclientinfoservice.getEconomicById(submittedValue.trim());
					return economic;
	        }
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		 if (value == null || value.equals("")) {  
	            return "";  
	        } else {  
	            return ((ViewEconomicInfo) value).getBmx_econ_activity_id();  
	        } 
	}

	public ViewClientInfoService getViewclientinfoservice() {
		return viewclientinfoservice;
	}

	public void setViewclientinfoservice(ViewClientInfoService viewclientinfoservice) {
		this.viewclientinfoservice = viewclientinfoservice;
	}


	
	
	
}
