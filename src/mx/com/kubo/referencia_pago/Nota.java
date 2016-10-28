package mx.com.kubo.referencia_pago;

import java.util.ArrayList;
import java.util.List;

public class Nota 
{
	private String descripcion;
	
	private List<Paso> lista_pasos;
	
	public Nota(String descripcion)
	{
		this.descripcion = descripcion;
		lista_pasos      = new ArrayList <Paso>();
	}

	public final String getDescripcion()
	{
		return descripcion;
	}
	
	public final void add(Paso paso)
	{
		lista_pasos.add(paso);
	}
	
	public final List<Paso> getLista_pasos()
	{
		return lista_pasos;
	}
}
