package mx.com.kubo.mesa.solicitud.perfil.curp;

import java.util.List;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.tools.Utilities;

public abstract class EditorCurpDMO 
implements EditorCurpIMO
{
	protected NaturalPersonService  service_natural_person;
	protected Change_controlService service_change_control;
	
	protected ChangeBean change_control_bean;
	protected List<Change_control>  bitacora_change_control;
	
	protected NaturalPerson person;
	
	protected String curp;
	protected String ipAddress;
	
	protected String[] tables;
	protected String[] fields;
	
	protected int company_id;
	protected int prospectus_id;
	protected int emisor_prospectus_id;
	
	protected EditorCurpDMO()
	{
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");
		
		tables = new String[]{"gn_natural_person"};
		fields = new String[]{"mx_curp"};
	}
	
	public void setSesion(SessionBean sesion) 
	{
		emisor_prospectus_id = sesion.getProspectus_id();
	}
	
	public final void setPerson(NaturalPerson person)
	{
		this.person = person;	
		
		prospectus_id = person.getNatPerPK().getProspectus_id();		
		company_id    = person.getNatPerPK().getCompany_id();
		
		curp = person.getMx_curp();	
		
		init_change_control();
	}
	
	protected void init_change_control() 
	{		
		change_control_bean = new ChangeBean();
		change_control_bean.setOrigValue(curp);
		change_control_bean.setNewValue(curp);
		change_control_bean.setNameTable("gn_natural_person");
		change_control_bean.setNameField("mx_curp");
			
		bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
			
		change_control_bean.setHasChange (bitacora_change_control != null && bitacora_change_control.size() > 0 ? true : false);
		change_control_bean.setLstChanges(bitacora_change_control != null && bitacora_change_control.size() > 0 ? bitacora_change_control : null);
	}
	
	public ChangeBean getChange_control_bean() 
	{
		return change_control_bean;
	}
}
