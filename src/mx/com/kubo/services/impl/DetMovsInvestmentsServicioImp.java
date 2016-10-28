package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.DetalleMovsInvestments;
import mx.com.kubo.repositories.DetMovsInvestmentsDAO;
import mx.com.kubo.services.DetMovsInvestmentsServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetMovsInvestmentsServicioImp implements Serializable,DetMovsInvestmentsServicio{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	protected DetMovsInvestmentsDAO detMovsInvestmentsDAO;
	
	public List<DetalleMovsInvestments> getMovimientosInvestments(Integer clienteID, Integer cuentaAhoID, char tipoConsulta, 
			  String fechaInicio,  String fechaFinal, 	  Integer mes,	Integer Anio){
		
		return detMovsInvestmentsDAO.getMovimientosInvestments(clienteID, cuentaAhoID, tipoConsulta, fechaInicio, fechaFinal, mes, Anio);
	}
}
