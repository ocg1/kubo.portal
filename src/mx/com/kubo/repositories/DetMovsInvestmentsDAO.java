package mx.com.kubo.repositories;

import java.util.List;
import mx.com.kubo.model.DetalleMovsInvestments;

public interface DetMovsInvestmentsDAO{
	
	public List<DetalleMovsInvestments> getMovimientosInvestments(Integer clienteID, Integer cuentaAhoID, char tipoConsulta, 
			  String fechaInicio,  String fechaFinal, 	  Integer mes,	Integer Anio);

}
