package mx.com.kubo.registro.datos.nombre;

import mx.com.kubo.registro.datos.DatosPersonalesDMO;

public abstract class PersonNameDMO extends DatosPersonalesDMO
implements PersonNameIMO
{		
	protected String first_name;
	protected String middle_name;
	protected String father_last_name;
	protected String mother_last_name;		
			
	public void setFirst_name(String first_name) 
	{
		this.first_name = first_name;
	}
	
	public String getFirst_name() 
	{
		return first_name;
	}

	public String getMiddle_name() 
	{
		return middle_name;
	}

	public String getFather_last_name() 
	{
		return father_last_name;
	}
	
	public String getMother_last_name() 
	{
		return mother_last_name;
	}
}
