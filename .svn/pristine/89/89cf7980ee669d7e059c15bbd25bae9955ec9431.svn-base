package mx.com.kubo.registro.sobreti.actividad;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.ActividadEconomicaDMO;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.registro.ChangeControlIMO;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EmploymentService;

public interface DomicilioIMO extends ChangeControlIMO
{
	void setService_employment        (EmploymentService service); 
	void setService_address              (AddressService service);
	
	void setPerson(NaturalPerson person);
	
	void setAddress_type_id(int address_type_id);
	
	void init_zipcode        (AjaxBehaviorEvent event);
	void init_neighborhood_id(AjaxBehaviorEvent event);
	
	void init_lista_neighborhood();
	void init_neighborhood          (ActividadEconomicaDMO employbean);
	void init_change_control_zipcode(ActividadEconomicaDMO employbean);
}
