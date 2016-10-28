package mx.com.kubo.mesa.autenticacion.fabrica;

import java.util.ArrayList;

import mx.com.kubo.mesa.autenticacion.Autenticacion;
import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;

public class AutenticacionFactory extends AutenticacionFactoryAMO
implements AutenticacionFactoryIMO
{	
	public final void init()
	{
		pool_autenticacion = service_catalogos.getAuthentication_pool();
				
		lista_preguntas = new ArrayList<AutenticacionIMO>(MAX_NUMBER_QUESTIONS);
		
		init_random_list();				
		
		for(int index = 0; index < MAX_NUMBER_QUESTIONS; index++)
		{								
			authentication = pool_autenticacion.get(random_list[index]);
			
			authentication_id = authentication.getPk().getAuthentication_id();
			
			autenticacion_id = Autenticacion.getInstance(authentication_id);
			
			autenticacion = init_autenticacion();	
			
			autenticacion.setMembership(membership);
			autenticacion.setNatural_person(natural_person);		
			autenticacion.setAuthentication(authentication);
			
			lista_preguntas.add(autenticacion);								
		} 
	}
}
