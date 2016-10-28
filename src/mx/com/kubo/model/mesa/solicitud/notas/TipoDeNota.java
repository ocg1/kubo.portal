package mx.com.kubo.model.mesa.solicitud.notas;

public enum TipoDeNota 
{
	DEFAULT             (0),
	RECHAZO             (7),
	VISITA_DOMICILIARIA (9),
	POSPUESTO          (10),
	DESISTIDO          (11),
	PREAPROBADO        (12);
	
	
	private static TipoDeNota instance;
	
	private int note_type_id;
	
	private TipoDeNota(int id)
	{
		note_type_id = id;
	}
	
	public static TipoDeNota getInstance(int note_type_id)
	{
		switch (note_type_id) 
		{
			case 7:
				instance = RECHAZO;
			break;
			
			case 9:
				instance = VISITA_DOMICILIARIA;
			break;
			
			case 10:
				instance = POSPUESTO;
			break;
			
			case 11:
				instance = DESISTIDO;
			break;		
			
			case 12:
				instance = PREAPROBADO;
			break;

			default: 
				instance = DEFAULT;
			break;
		}
		
		return instance;
	}

	public int getNote_type_id() 
	{
		return note_type_id;
	}
}
