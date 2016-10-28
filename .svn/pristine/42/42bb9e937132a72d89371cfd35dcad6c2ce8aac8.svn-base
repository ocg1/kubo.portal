package mx.com.kubo.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;

@FacesConverter(value = "viewFullNameConverter")
public class ViewFullNameConverter 
implements Converter
{
	 public Object getAsObject(FacesContext fc, UIComponent uic, String value) 
	 {
	        if(value != null && value.trim().length() > 0) 
	        {
	            try 
	            {
//	            	ClientViewFullName service = (ClientViewFullName) fc.getExternalContext().getApplicationMap().get("ClientViewFullName");
//	                return service.getThemes().get(Integer.parseInt(value));
	            	
	            	// System.out.println("ViewFullNameConverter.getAsObject(): " + value);
	            	
	            	return value.trim();
	            	
	            } catch(Exception e) {
	            	
	                throw new ConverterException  ( new FacesMessage (FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
	                
	            }
	            
	        } else {
	        	
	            return null;
	        }
	    }
	 
	 @Override
	 public String getAsString(FacesContext fc, UIComponent uic, Object object) 
	 {
		 try
		 {
			 if(object != null) 
	        {
				 String prospectus_id = String.valueOf(((ClientViewFullName) object).getProspectus_id());;
				 
				 // System.out.println("ViewFullNameConverter.getAsString(): " + prospectus_id);
				
	            return prospectus_id;
	            
	        } else {
	        	
	            return null;
	        }
	        
		 } catch(Exception e) {
			 return null;
		 }
	 } 
	
}
