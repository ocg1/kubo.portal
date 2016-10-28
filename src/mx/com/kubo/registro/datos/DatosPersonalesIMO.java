package mx.com.kubo.registro.datos;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.registro.ChangeControlIMO;
import mx.com.kubo.services.NaturalPersonService;

public interface DatosPersonalesIMO extends ChangeControlIMO
{
	void setService_natural_person (NaturalPersonService service);
	
	void setPerson(NaturalPerson person);	
}
