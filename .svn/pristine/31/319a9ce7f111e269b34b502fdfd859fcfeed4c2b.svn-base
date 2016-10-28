package mx.com.kubo.constantes;

public enum ActividadEconomica 
{
	COMERCIANTE(1),
	EMPLEADO(2),
	EMPLEADO_Y_COMERCIANTE(3),
	COMERCIANTE_Y_EMPLEADO(4),
	EMPRESARIO_SOCIO(5),
	JUBILADO(6),
	DEPENDIENTE(7);
	
	private int id;
	
	private static ActividadEconomica instance;
	
	private ActividadEconomica(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public static ActividadEconomica getInstance(int id)
	{
		switch (id) 
		{
			case 1:
				instance = COMERCIANTE;
			break;
			
			case 2:
				instance = EMPLEADO;
			break;
				
			case 3:
				instance = EMPLEADO_Y_COMERCIANTE;
			break;
				
			case 4:
				instance = COMERCIANTE_Y_EMPLEADO;
			break;
				
			case 5:
				instance = EMPRESARIO_SOCIO;
			break;
			
			case 6:
				instance = JUBILADO;
			break;
				
			case 7:
				instance = DEPENDIENTE;
			break;
	
			default:
				instance = null;
			break;
		}
		
		return instance;
	}
}
