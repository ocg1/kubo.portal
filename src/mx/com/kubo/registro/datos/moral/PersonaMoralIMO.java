package mx.com.kubo.registro.datos.moral;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.SocietyType;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProspectusService;

public interface PersonaMoralIMO 
{
	void setService_prospectus(ProspectusService         service);
	void setService_natural_person(NaturalPersonService  service);
	void setService_change_control(Change_controlService service);
	
	void setSesion(SessionBean sesion);
	void setPerson(NaturalPerson person);
	
	void init();
	void init_person_type_id (AjaxBehaviorEvent evento);
	void init_legal_name     (AjaxBehaviorEvent evento);
	void init_society_type_id(AjaxBehaviorEvent evento);
	void init_company_rfc    (AjaxBehaviorEvent evento);
	
	boolean isPersona_moral_ENABLED();
	
	List<SocietyType> getLista_society_type();	
}
