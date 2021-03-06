package mx.com.kubo.files;

public enum CatalogoFileType 
{
	CREDENCIAL_ELECTOR_FRENTE  	(1),
	CREDENCIAL_ELECTOR_REVERSO 	(42),
	FM2_FRENTE                 	(2),
	FM3_REVERSO                	(43),
	PASAPORTE                  	(76),
	CARTILLA                  		(131),
	LICENCIA                  		(132),
	LICENCIA_REVERSO             	(134),
	CEDULA                  		(133),
	SELFIE_IDENTIFICACION			(119);
	
	private static CatalogoFileType instance;
	
	private int file_type_id;	
	
	CatalogoFileType(int id)
	{
		this.file_type_id = id;
	}
	
	public static CatalogoFileType getInstance(int file_type_id)
	{
		switch (file_type_id) 
		{
			case 1:
				instance = CREDENCIAL_ELECTOR_FRENTE;
			break;				
			
			case 2: 
				instance = FM2_FRENTE;
			break;
			
			case 42:
				instance = CREDENCIAL_ELECTOR_REVERSO;
			break;
			
			case 43:
				instance = FM3_REVERSO;
			break;
			
			case 76: 
				instance = PASAPORTE;
			break;
			
			case 119: 
				instance = SELFIE_IDENTIFICACION;
			break;
			
			case 131: 
				instance = CARTILLA;
			break;
			
			case 132: 
				instance = LICENCIA;
			break;
			
			case 133: 
				instance = CEDULA;
			break;
			
			case 134: 
				instance = LICENCIA_REVERSO;
			break;

			default: 
				instance = null;
			break;
		}
		
		return instance;
	}

	public int getFile_type_id() 
	{
		return file_type_id;
	}

	public void setFile_type_id(int id) 
	{
		file_type_id = id;
	}
	
	
}
