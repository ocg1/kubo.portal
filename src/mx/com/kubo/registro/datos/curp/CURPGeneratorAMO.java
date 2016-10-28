package mx.com.kubo.registro.datos.curp;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public abstract class CURPGeneratorAMO extends CURPGeneratorDMO
{
	protected void init_change_control(String field, String original_value, String new_value) 
	{			
		change_control_ENABLED = ! new_value.equalsIgnoreCase(original_value);
		
		if(change_control_ENABLED)
		{	
			change_control_PK = new Change_controlPK();
			change_control    = new Change_control();		
			
			change_control_PK.setProspectus_id(prospectus_id);
			change_control_PK.setCompany_id(company_id);
			
			change_control.setChange_controlPK(change_control_PK);		
			change_control.setChange_prospectus_id(change_prospectus_id);
			
			change_control.setAfected_table("gn_natural_person");	
			change_control.setField(field);
			
			change_control.setOriginal_value(original_value);
			change_control.setNew_value(new_value);
			
			change_control.setComments(comments);
			change_control.setIp(sesion.getIP_address_client());

			change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
		}
	}
}
