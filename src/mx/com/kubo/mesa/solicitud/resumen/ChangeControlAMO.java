package mx.com.kubo.mesa.solicitud.resumen;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public abstract class ChangeControlAMO extends ChangeControlDMO
{
	public void init_change_control() 
	{		
		change_control_bean = new ChangeBean();
		change_control_bean.setOrigValue(original_value);
		change_control_bean.setNameTable(afected_table);
		change_control_bean.setNameField(field);
		change_control_bean.setWhyChangeData("");
			
		bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
			
		change_control_bean.setHasChange (bitacora_change_control != null && bitacora_change_control.size() > 0 ? true : false);
		change_control_bean.setLstChanges(bitacora_change_control != null && bitacora_change_control.size() > 0 ? bitacora_change_control : null);
	}
	
	public void init_change_control_bean() 
	{
		if(change_control_OK)
		{
			change_control_bean.setWhyChangeData("");
			change_control_bean.setHasChange(true);
			
			bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
			
			if(bitacora_change_control != null && bitacora_change_control.size() > 0)
			{						
				change_control_bean.setLstChanges(bitacora_change_control);
				
			} else {
				
				change_control_bean.setLstChanges(null);	
			}	
		}
	}
	
	public void save_change_control()
	{
		change_control_OK = update_OK = false;
		
		save_ENABLED = change_control_bean.getWhyChangeData() != null;
		
		change_control_bean.setNewValue(new_value);
		
		if(save_ENABLED)
		{
			change_control_PK = new Change_controlPK();		
			change_control_PK.setProspectus_id(prospectus_id);
			change_control_PK.setCompany_id(company_id);
			
			change_control = new Change_control();
			
			change_control.setChange_controlPK(change_control_PK);
			change_control.setChange_prospectus_id(emisor_prospectus_id);
			change_control.setAfected_table(afected_table);
			change_control.setAfected_table_type(afected_table_type);
			change_control.setField(field);
			change_control.setField_type_id(field_type_id);
			change_control.setIp(ip_address);			
			change_control.setOriginal_value(change_control_bean.getOrigValue());
			change_control.setNew_value     (change_control_bean.getNewValue());		
			change_control.setComments      (change_control_bean.getWhyChangeData());
			
			change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
		}
	}
}
