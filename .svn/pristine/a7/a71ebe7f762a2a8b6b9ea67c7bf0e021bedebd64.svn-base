package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.DetSaldosKuboGlobal;
import mx.com.kubo.repositories.DetSaldosKuboGlobalDAO;
import mx.com.kubo.services.DetSaldosKuboGlobalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetSaldosKuboGlobalServiceImp implements DetSaldosKuboGlobalService, Serializable{
	private static final long serialVersionUID = 1L;
	
	// @Autowired
	protected DetSaldosKuboGlobalDAO detSaldosKuboGlobalDAO;
	
	public DetSaldosKuboGlobal consultaSaldoKuboGlobal(Integer clienteID, Integer cuentaAhoID, char tipoConsulta,
														String fechaInicio, String fechaFin, int mes, int anio){
		
		return detSaldosKuboGlobalDAO.consultaSaldoKuboGlobal(clienteID, cuentaAhoID, tipoConsulta, fechaInicio, fechaFin, mes, anio); 
	}
}
