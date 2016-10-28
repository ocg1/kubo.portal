package mx.com.kubo.registro.datos.curp;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.StateService;

public interface CURPGeneratorIMO 
{
	void setService_estado                 (StateService service); 
	void setService_change_control(Change_controlService service);	
	void setService_natural_person (NaturalPersonService service);
	
	void setPerson(NaturalPerson person);
	void setSesion(SessionBean  sesion);
	
	void setChange_prospectus_id(Integer prospectus_id);
	
	void init_RFC();
	void init_CURP();
	
	NaturalPerson getPerson();
}
