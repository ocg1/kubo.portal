package mx.com.kubo.registro.datos.moral;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public abstract class PersonaMoralAMO extends PersonaMoralDMO
{
	protected void init_persona_moral_ENABLED() 
	{
		switch(person_type_id.charAt(0))
		{
			case 'M':
				persona_moral_ENABLED = true;
			break;
		
			default:
				persona_moral_ENABLED = false;
			break;			
		}
	}
	
	protected void init_change_control(String afected_table, String field, String original_value, String new_value) 
	{
		change_control_PK = new Change_controlPK();
		change_control    = new Change_control();
		
		change_control_PK.setProspectus_id(prospectus_id);
		change_control_PK.setCompany_id(company_id);
		
		change_control.setChange_controlPK(change_control_PK);		
		change_control.setChange_prospectus_id(prospectus_id);
		
		change_control.setAfected_table(afected_table);	
		change_control.setField(field);
		
		change_control.setOriginal_value(original_value);
		change_control.setNew_value(new_value);
		
		change_control.setComments(comments);
		change_control.setIp(IP_address_client);
		
		change_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
	}
}
