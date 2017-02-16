package mx.com.kubo.registro;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;

import org.primefaces.context.RequestContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.services.Change_controlService;

public abstract class ChangeControlDMO 
implements ChangeControlIMO
{
	protected Change_controlService service_change_control;
	
	protected HtmlInputText     input_text;
	protected HtmlInputTextarea input_textarea;
	protected HtmlSelectOneMenu  select_menu;
	protected HtmlSelectOneRadio select_radio;
	
	protected RequestContext request;
	
	protected SessionBean sesion;	
	
	protected Change_controlPK change_control_PK;
	protected Change_control   change_control;	
	
	protected Date focus_date;
	
	protected SimpleDateFormat date_format;
	
	protected String area;
	protected String field;
	protected String afected_table_type;
	protected String original_value;
	protected String new_value;
	protected String comments;
	protected String IP_address_client;
		
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer prospectus_id_coach;
	protected Integer change_prospectus_id;	
	protected Integer field_type_id;
	
	protected boolean change_control_OK;
	protected boolean change_control_ENABLED;
	
	protected ChangeControlDMO() 
	{
		date_format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
	}
	
	public final void setService_change_control(Change_controlService service)
	{
		service_change_control = service;
	}
	
	public void setSesion(SessionBean  sesion)
	{
		this.sesion = sesion;
		
				 company_id = sesion.getCompany_id();
					   area = sesion.getArea() + "";
		prospectus_id 		= sesion.getProspectus_id();		
		prospectus_id_coach = sesion.getCoachProspectus_id();
		IP_address_client   = sesion.getIP_address_client();
		
		change_prospectus_id = prospectus_id_coach != null ? prospectus_id_coach : prospectus_id;
	}
}
