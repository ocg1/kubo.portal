package mx.com.kubo.listeners.mesa.solicitud;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlInputText;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.NaturalPerson;

import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FullNameService;
import mx.com.kubo.services.NaturalPersonService;

import mx.com.kubo.tools.Utilities;

import static mx.com.kubo.change_control.ChangeControlEMO.EDICION_DE_NOMBRE;
import static mx.com.kubo.change_control.ChangeControlEMO.EDICION_DE_SEGUNDO_NOMBRE;
import static mx.com.kubo.change_control.ChangeControlEMO.EDICION_DE_APELLIDO_PATERNO;
import static mx.com.kubo.change_control.ChangeControlEMO.EDICION_DE_APELLIDO_MATERNO;

public abstract class EditorNombreDMO 
implements EditorNombreIMO
{
	protected NaturalPersonService   service_natural_person;
	protected Change_controlService  service_change_control;
	protected FullNameService 		  full_name_service;
	
	protected ChangeBean change_control_bean;
	
	protected RequestContext request;
	
	protected HtmlInputText input_text;
	
	protected NaturalPerson person;
	
	protected List<ChangeBean> lista_cambios;
	protected List<Change_control>   bitacora_change_control;
	
	protected String first_name;
	protected String middle_name;
	protected String father_last_name;
	protected String mother_last_name;
	protected String full_name;
	
	protected String[] tables;
	protected String[] fields;
	
	protected int company_id;
	protected int prospectus_id;
	protected int emisor_prospectus_id;
	
	protected final int INDEX_NOMBRE           = 0;
	protected final int INDEX_SEGUNDO_NOMBRE   = 1;
	protected final int INDEX_APELLIDO_PATERNO = 2;
	protected final int INDEX_APELLIDO_MATERNO = 3;
	
	protected boolean change_control_OK;
	protected boolean edicion_ENABLED;
	protected boolean error_first_name_ENABLED;
	protected boolean error_father_last_name_ENABLED;	
	
	protected EditorNombreDMO()
	{
		service_natural_person = Utilities.findBean("naturalPersonServiceImp");
		service_change_control = Utilities.findBean("change_controlServiceImp");
		full_name_service	   = Utilities.findBean("fullNameServiceImp");
		
		tables = new String[]{"gn_natural_person"};
		fields = new String[]{"first_name", "middle_name", "father_last_name", "mother_last_name"};		
		
		init_lista_cambios();	
	}	
	
	protected void init_lista_cambios() 
	{
		lista_cambios = new ArrayList<ChangeBean>(4);		
		
		lista_cambios.add(INDEX_NOMBRE,           new ChangeBean(EDICION_DE_NOMBRE));
		lista_cambios.add(INDEX_SEGUNDO_NOMBRE,   new ChangeBean(EDICION_DE_SEGUNDO_NOMBRE));
		lista_cambios.add(INDEX_APELLIDO_PATERNO, new ChangeBean(EDICION_DE_APELLIDO_PATERNO));
		lista_cambios.add(INDEX_APELLIDO_MATERNO, new ChangeBean(EDICION_DE_APELLIDO_MATERNO));
	}

	public void setSesion(SessionBean sesion) 
	{
		emisor_prospectus_id = sesion.getProspectus_id();
	}
	
	public final void setPerson(NaturalPerson person)
	{
		this.person = person;
		
		init_person();
		init_bitacora_change_control();			
	}

	protected void init_person() 
	{
		prospectus_id    = person.getNatPerPK().getProspectus_id();		
		company_id       = person.getNatPerPK().getCompany_id();		
		first_name       = person.getFirst_name();
		middle_name      = person.getMiddle_name();
		father_last_name = person.getFather_last_name();
		mother_last_name = person.getMother_last_name();	
		full_name        = person.NombreCompletoNPM();
	}
	
	protected void init_bitacora_change_control() 
	{
		bitacora_change_control = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
		
		change_control_bean = new ChangeBean();
		change_control_bean.setHasChange (bitacora_change_control != null && bitacora_change_control.size() > 0 ? true : false);
		change_control_bean.setLstChanges(bitacora_change_control != null && bitacora_change_control.size() > 0 ? bitacora_change_control : null);
	}

	public String getFull_name() 
	{
		return full_name;
	}

	public String getFirst_name() 
	{
		return first_name;
	}

	public String getMiddle_name() 
	{
		return middle_name;
	}
	
	public String getFather_last_name() 
	{
		return father_last_name;
	}

	public String getMother_last_name() 
	{
		return mother_last_name;
	}

	public ChangeBean getChange_control_bean() 
	{
		return change_control_bean;
	}
}
