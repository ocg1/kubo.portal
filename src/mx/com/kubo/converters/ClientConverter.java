package mx.com.kubo.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mx.com.kubo.model.ClientView;

@FacesConverter(value="clientconvert")
public class ClientConverter implements Converter{
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {	  
	        return submittedValue.trim();  
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		 if (value == null || value.equals("")) {  
	            return "";  
	        } else {  
	            //return ((ClientView) value).getTracking_id();  
	        	return ((ClientView) value).getProspectus_id().toString();
	        }  
	}

}
