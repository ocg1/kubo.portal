package mx.com.kubo.change_control;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.services.Change_controlService;

public abstract class ChangeControlDMO 
implements ChangeControlIMO
{
	protected Change_controlService service_change_control;
	
	protected Change_controlPK change_control_PK;
	protected Change_control   change_control;	
	
	protected String field;
	protected String afected_table;
	protected String comments;
	protected String IP_address_client;
	
	protected boolean change_control_OK;
	protected boolean change_control_ENABLED;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer change_prospectus_id;	
	
	public void setField(String field) 
	{
		this.field = field;
	}

	public void setAfected_table(String afected_table) 
	{
		this.afected_table = afected_table;
	}
}
