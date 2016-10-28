package mx.com.kubo.mesa.autenticacion;

public enum Autenticacion 
{
	BIRTH_DATE  		 (1),
	BIRTH_PLACE          (2),
	DELEGACION_MUNICIPIO (3),
	CODIGO_POSTAL		 (4),
	TELEFONO_CELULAR	 (5),
	CORREO_ELECTRONICO	 (6);
	
	private int authentication_id;
	
	private static Autenticacion instance;
	
	private Autenticacion(int id)
	{
		this.authentication_id = id;
	}
	
	public static Autenticacion getInstance(int authentication_id)
	{
		switch(authentication_id)
		{
			case 1:
				instance = BIRTH_DATE;
			break;
			
			case 2:
				instance = BIRTH_PLACE;
			break;
			
			case 3:
				instance = DELEGACION_MUNICIPIO;
			break;
			
			case 4:
				instance = CODIGO_POSTAL;
			break;
			
			case 5:
				instance = TELEFONO_CELULAR;
			break;
			
			case 6:
				instance = CORREO_ELECTRONICO;
			break;
			
			default:
				instance = null;
			break;
		}
		
		return instance;
	}
	
	public int getId()
	{
		return authentication_id;
	}
}
