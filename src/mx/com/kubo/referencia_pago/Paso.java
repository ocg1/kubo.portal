package mx.com.kubo.referencia_pago;

public class Paso 
{
	private String 
		paso_id, paso_id_descripcion;
	
	public Paso(String paso_id, String paso_id_descripcion)
	{
		this.paso_id = paso_id;
		this.paso_id_descripcion = paso_id_descripcion;
	}
	
	public final String getPaso_id()
	{
		return paso_id;
	}

	public final String getPaso_id_descripcion()
	{
		return paso_id_descripcion;
	}
}
