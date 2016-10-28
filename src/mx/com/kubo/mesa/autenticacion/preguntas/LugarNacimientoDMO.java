package mx.com.kubo.mesa.autenticacion.preguntas;

import java.util.List;

import mx.com.kubo.mesa.autenticacion.AutenticacionDMO;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.services.StateService;
import mx.com.kubo.tools.Utilities;

public abstract class LugarNacimientoDMO extends AutenticacionDMO
{
	protected StateService service_estados;
	
	protected List<StateCat> lista_estados;
	
	protected String value_SELECTED;
	
	protected Integer state_id_ORIGINAL;
	protected Integer state_id_NEW;
	
	protected boolean birthplace_ENABLED;
	
	protected LugarNacimientoDMO()
	{
		service_estados = Utilities.findBean("stateServiceImp");
		
		lista_estados = service_estados.getStateList();
	}
	
	public final void setNatural_person(NaturalPerson natural_person) 
	{
		this.natural_person = natural_person;
		
		state_id_ORIGINAL = natural_person.getState_id();
	}

	public final boolean isValue_ENABLED() 
	{
		return birthplace_ENABLED;
	}

	public final String getValue_ORIGINAL() 
	{		
		return state_id_ORIGINAL.toString();
	}

	public final String getValue_NEW() 
	{
		if(state_id_NEW != null)
		{
			return state_id_NEW.toString();
		} 
		
		return "";		
	}

	public final List<StateCat> getLista_estados() 
	{
		return lista_estados;
	}
}
