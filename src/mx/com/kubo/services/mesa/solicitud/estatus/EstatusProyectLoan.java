package mx.com.kubo.services.mesa.solicitud.estatus;

public enum EstatusProyectLoan 
{
	BORRADOR     			(0),
	COMITE       			(1),	
	AUTORIZADO   			(2),
	DESEMBOLSADO 			(3),
	CANCELADO    			(4),
	POSPUESTO   			(5),
	LIQUIDADO   		    (6),
	DESISTIDO               (7),
	DUPLICADO               (8),
	EN_PROCESO_AUTORIZACION (9),
	PRE_AUTORIZADO          (10),
	CANCELADO_AUTOMATICO    (11);
		
	private static EstatusProyectLoan instance;
	
	private int estatus_id;
	
	private EstatusProyectLoan(int id)
	{
		estatus_id = id;
	}
	
	public static EstatusProyectLoan getInstance(int id)
	{				
		switch(id)
		{
			case 0: 
				instance = BORRADOR;
			break;
			
			case 1: 
				instance = COMITE;
			break;
			
			case 2: 
				instance = AUTORIZADO;
			break;
			
			case 3: 
				instance = DESEMBOLSADO;
			break;
			
			case 4: 
				instance = CANCELADO;
			break;
			
			case 5: 
				instance = POSPUESTO;
			break;
			
			case 6: 
				instance = LIQUIDADO;
			break;
			
			case 7: 
				instance = DESISTIDO;
			break;
			
			case 8: 
				instance = DUPLICADO;
			break;
			
			case 9: 
				instance = EN_PROCESO_AUTORIZACION;
			break;
			
			case 10: 
				instance = PRE_AUTORIZADO;
			break;
			
			case 11: 
				instance = CANCELADO_AUTOMATICO;
			break;
			
			default:
				instance = null;
			break;
		}			
		
		return instance;
	}
	
	public int getId()
	{
		return estatus_id;
	}
}
