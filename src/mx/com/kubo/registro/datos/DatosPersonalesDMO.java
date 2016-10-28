package mx.com.kubo.registro.datos;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.registro.ChangeControlDMO;
import mx.com.kubo.services.NaturalPersonService;

public abstract class DatosPersonalesDMO extends ChangeControlDMO
implements DatosPersonalesIMO
{
	protected NaturalPersonService service_natural_person;
		
	protected NaturalPerson person;
	
	public final void setService_natural_person(NaturalPersonService service)
	{
		service_natural_person = service;			
	}
		
	public void setPerson(NaturalPerson person) 
	{
		this.person = person;
	}
}
