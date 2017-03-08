package mx.com.kubo.mesa.solicitud.perfil.curp;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public class EditorCurpIMP extends EditorCurpDMO
implements EditorCurpIMO
{	
	public void listener_guardar_cambios() 
	{
		FacesContext faces = FacesContext.getCurrentInstance();
		ExternalContext external = faces.getExternalContext();
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) external.getRequest();
		
		ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR"); 
		
        if(ipAddress == null)  
        {  
          ipAddress = httpServletRequest.getRemoteAddr();  
        }
        
        boolean change_ENABLED = person!=null && person.getMx_curp()!=null && change_control_bean.getOrigValue()!=null && change_control_bean.getWhyChangeData()!=null;
        
		if(change_ENABLED)
		{			
			person.setMx_curp(change_control_bean.getNewValue());
			
			person = service_natural_person.update(person);
			
			boolean change_control_OK = saveChangeData("gn_natural_person", "mx_curp", change_control_bean.getOrigValue() != null ? change_control_bean.getOrigValue():"", person.getMx_curp(), change_control_bean.getWhyChangeData());
			
			if(change_control_OK)
			{
				change_control_bean.setWhyChangeData(null);
				change_control_bean.setHasChange(true);
				
				int prospectus_id = person.getNatPerPK().getProspectus_id();
				int company_id    = person.getNatPerPK().getCompany_id();
				
				bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
				
				if(bitacora_change_control != null && bitacora_change_control.size() > 0)
				{						
					change_control_bean.setLstChanges(bitacora_change_control);
					
				} else {
					
					change_control_bean.setLstChanges(null);	
				}								
			}							
		}
	}
	
	private boolean saveChangeData(String table, String field, String origValue, String newValue, String comment)
	{
		Change_controlPK changeCtrlPK = new Change_controlPK();
		
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		Change_control changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(emisor_prospectus_id);
		changeCtrl.setAfected_table(table);
		changeCtrl.setIp(ipAddress);			
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);
		
		if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
		{
			return true;	
		} else {
			return false;
		}
	}
}
