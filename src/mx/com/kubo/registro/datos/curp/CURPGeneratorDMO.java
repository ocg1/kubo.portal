package mx.com.kubo.registro.datos.curp;

import java.text.SimpleDateFormat;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.StateService;

public abstract class CURPGeneratorDMO 
implements CURPGeneratorIMO
{
	protected NaturalPersonService  service_natural_person;
	protected StateService          service_estado;
	protected Change_controlService service_change_control;
	
	protected SessionBean sesion;	
	protected NaturalPerson person;
	
	protected Change_controlPK change_control_PK;
	protected Change_control   change_control;
	
	protected StateCatPK state_cat_PK;
	
	protected SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	protected String name = "";  		
	protected String birthday;
	protected String father_last; 
	protected String mother_last;
	protected String estado;
	protected String RFC;
	protected String CURP;
	protected String value_ORIGINAL;
	protected String comments;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer change_prospectus_id;
	
	protected boolean rfc_ENABLED;
	protected boolean curp_ENABLED;
	protected boolean change_control_OK;
	protected boolean change_control_ENABLED;
	protected boolean citizenship_ENABLED;
	protected boolean state_ENABLED;
	
	public final void setService_estado(StateService service) 
	{
		service_estado = service;
	}
	
	public final void setService_natural_person(NaturalPersonService service)
	{
		service_natural_person = service;
	}
	
	public final void setService_change_control(Change_controlService service)
	{
		service_change_control = service;
	}
	
	public final void setSesion(SessionBean  sesion)
	{
		this.sesion = sesion;
	}
	
	public final void setPerson(NaturalPerson person)
	{
		this.person = person;
		
		prospectus_id  = person.getNatPerPK().getProspectus_id();
		company_id     = person.getNatPerPK().getCompany_id();		
		
		if( person.getFirst_name() != null && person.getFather_last_name() != null && person.getMother_last_name() != null ){
		
			rfc_ENABLED = !person.getFirst_name().isEmpty()
					   && !person.getFather_last_name().isEmpty()
					   && !person.getMother_last_name().isEmpty()
					   &&  person.getDate_of_birth() != null;
		
		}
		
		citizenship_ENABLED = person.getCitizenship() != null && person.getCitizenship() == 1;
		
		state_ENABLED = person.getState_id() != null && !person.getState_id().equals("") && !person.getState_id().equals("0");
	
	}
	
	public final void setChange_prospectus_id(Integer prospectus_id)
	{
		change_prospectus_id = prospectus_id;
	}
	
	public final NaturalPerson getPerson()
	{
		return person;
	}
}
