package mx.com.kubo.mesa.autenticacion.fabrica;

import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;
import mx.com.kubo.mesa.autenticacion.preguntas.CodigoPostalIMP;
import mx.com.kubo.mesa.autenticacion.preguntas.CorreoElectronicoIMP;
import mx.com.kubo.mesa.autenticacion.preguntas.DelegacionMunicipioIMP;
import mx.com.kubo.mesa.autenticacion.preguntas.FechaNacimientoIMP;
import mx.com.kubo.mesa.autenticacion.preguntas.LugarNacimientoIMP;
import mx.com.kubo.mesa.autenticacion.preguntas.TelefonoCelularIMP;

public abstract class AutenticacionFactoryAMO extends AutenticacionFactoryDMO
{	
	protected void init_random_list() 
	{								
		for(int index = 0; index < MAX_NUMBER_QUESTIONS; index++)
		{		
			do
			{
				random = generator.nextInt(pool_autenticacion.size());	
				
				random_ENABLED = true;
			
				if(index > 0)
				{
					for(int reverse_index = index; reverse_index >= 0; reverse_index--)
					{
						if(random == random_list[reverse_index]) 
						{
							random_ENABLED = false;
							break;
						}
					}
				}	
				
			} while (!random_ENABLED);
		
			random_list[index] = random;
		}
	}
	
	protected AutenticacionIMO init_autenticacion() 
	{
		switch(autenticacion_id)
		{	
			case BIRTH_DATE:
				autenticacion = new FechaNacimientoIMP();
			break;
			
			case BIRTH_PLACE:
				autenticacion = new LugarNacimientoIMP();
			break;
			
			case DELEGACION_MUNICIPIO:
				autenticacion = new DelegacionMunicipioIMP();
			break;
			
			case CODIGO_POSTAL:					
				autenticacion = new CodigoPostalIMP();
			break;
			
			case TELEFONO_CELULAR:
				autenticacion = new TelefonoCelularIMP();
			break;
			
			case CORREO_ELECTRONICO:	
				autenticacion = new CorreoElectronicoIMP();
			break;
		}
		
		return autenticacion;
	}
}
