package mx.com.kubo.portal.telefonos;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public abstract class ChangePhoneAMO extends ChangePhoneDMO
{	
	protected void init_change_control(String origValue, String newValue, String comment)
	{
		change_control_PK = new Change_controlPK();
		
		change_control_PK.setProspectus_id(prospectus_id);
		change_control_PK.setCompany_id   (company_id);
		
		change_control = new Change_control();
		
		change_control.setChange_controlPK(change_control_PK);
		change_control.setChange_prospectus_id(change_prospectus_id);
		change_control.setAfected_table("gn_phone");
		change_control.setField(field);
		change_control.setAfected_table_type(afected_table_type);
		change_control.setField_type_id(field_type_id);					
		change_control.setOriginal_value(origValue);
		change_control.setNew_value(newValue);		
		change_control.setComments(comment);		
		change_control.setIp(IP_address_client);
		
		change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);				
	}
}
