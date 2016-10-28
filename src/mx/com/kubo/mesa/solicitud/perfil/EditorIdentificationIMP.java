package mx.com.kubo.mesa.solicitud.perfil;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.model.Change_control;

public final class EditorIdentificationIMP extends EditorIdentificationAMO 
implements EditorIdentificationIMO 
{
	public void init()
	{
		if(person != null)
		{			
			identification_type_id = person.getIdentification_type_id();					
			
			if(identification_type_id != null)
			{
				init_label();				
			} 								
		}
	}
	
	public void init_type_id(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		identification_type_id = Integer.parseInt(select_menu.getValue().toString());
				
		init_label();
		
		request.addCallbackParam("type_id", identification_type_id);
		request.addCallbackParam("label_ENABLED", label_ENABLED);		
	}
	
	public void edit(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		input_text_id    = input_text.getId();		
		input_text_value = input_text.getValue().toString();
		
		if(input_text_id.equals("mx-ine-cic"))
		{
			person.setMx_ine_cic(input_text_value);
			
		} else if(input_text_id.equals("mx-ife-cveelector")) {
			
			person.setMx_ife_cveelector(input_text_value);
		}
		
		request.addCallbackParam("input_text_value", input_text_value);
	}
	
	public void update()
	{
		request = RequestContext.getCurrentInstance();
		
		person.setIdentification_type_id(identification_type_id);
		
		person = service_natural_person.update(person);
		
		request.addCallbackParam("update_OK", true);
	}
	
	public void saveChangeIFE(ChangeBean changeife){
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		  String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	        if(ipAddress == null)  
	        {  
	          ipAddress = httpServletRequest.getRemoteAddr();  
	        }
	        
		if(person!=null && changeife.getWhyChangeData()!=null)
		{
			String originalVal="";
			String newValue="";
			String field="";
			
			if(changeife.getNameField().equals("mx_ife_cveelector"))
			{
				originalVal=person.getMx_ife_cveelector()!=null?person.getMx_ife_cveelector():"";
				newValue=changeife.getNewValue();
				field="mx_ife_cveelector";
				person.setMx_ife_cveelector(changeife.getNewValue());
				
			}else if(changeife.getNameField().equals("mx_ife_numemision")){
				originalVal=person.getMx_ife_numemision()!=null?person.getMx_ife_numemision().toString():"";
				newValue=changeife.getNewValue();
				field="mx_ife_numemision";	
				person.setMx_ife_numemision(Integer.parseInt(changeife.getNewValue()));
			}else if(changeife.getNameField().equals("mx_ife_seccion")){
				originalVal=person.getMx_ife_seccion()!=null?person.getMx_ife_seccion():"";
				newValue=changeife.getNewValue();
				field="mx_ife_seccion";				
				person.setMx_ife_seccion(changeife.getNewValue());
			}else if(changeife.getNameField().equals("mx_ife_numvertical")){
				originalVal=person.getMx_ife_numvertical()!=null?person.getMx_ife_numvertical():"";
				newValue=changeife.getNewValue();
				field="mx_ife_numvertical";			
				person.setMx_ife_numvertical(changeife.getNewValue());
			}
			
			service_natural_person.update(person);	
			
			if(saveChangeData("gn_natural_person", field, originalVal, newValue, changeife.getWhyChangeData()))
			{
				whyChangeData = (null);
				changeife.setWhyChangeData(null);
				changeDataIFE=new ChangeBean();
				List<Change_control> lstChange_ife=service_change_control.getListByProspectByAfectedTablesFields(person.getNatPerPK().getProspectus_id(),
						person.getNatPerPK().getCompany_id(), new String[]{"gn_natural_person"}, new String[]{"mx_ife_cveelector","mx_ife_numemision","mx_ife_seccion","mx_ife_numvertical"});
				changeDataIFE.setHasChange(lstChange_ife!=null && lstChange_ife.size()>0?true:false);
				changeDataIFE.setLstChanges(lstChange_ife!=null && lstChange_ife.size()>0?lstChange_ife:null);
			}
				
			
		}
	}
}
