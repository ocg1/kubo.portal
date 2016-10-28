package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "view_home_statistics")
public class ViewHomeStatistics implements Serializable
{
	private static final long serialVersionUID = -3753901990895722345L;

	@Id
	@Column(name = "Clientes")
	private Long clientes;
	
	@Column(name = "TasaPromedio") 
	private Double tasa_promedio;
	
	@Column(name = "PrestamosOtorgados")
	private Long prestamos_otorgados;
		
	@Column(name = "MillonesPrestados")
	private Double millones_prestados;

	public final String getTasa_promedio() 
	{
		String parte_entera = "";
		
		try
		{
			parte_entera = String.valueOf(tasa_promedio).split("[^0-9]")[0];
									
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return parte_entera;
	}
	
	public final String getTasa_promedio_parte_decimal() 
	{
		String decimales = "";
		
		try
		{
			decimales = "." + String.valueOf(tasa_promedio).split("[^0-9]")[1] + "%";
									
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return decimales;
	}

	public final Long getPrestamos_otorgados() {
		return prestamos_otorgados;
	}

	public final Long getClientes() {
		return clientes;
	}

	public final Double getMillones_prestados() {
		return millones_prestados;
	}

	public final void setTasa_promedio(Double tasa_promedio) {
		this.tasa_promedio = tasa_promedio;
	}

	public final void setPrestamos_otorgados(Long prestamos_otorgados) {
		this.prestamos_otorgados = prestamos_otorgados;
	}

	public final void setClientes(Long clientes) {
		this.clientes = clientes;
	}

	public final void setMillones_prestados(Double millones_prestados) {
		this.millones_prestados = millones_prestados;
	}
}
