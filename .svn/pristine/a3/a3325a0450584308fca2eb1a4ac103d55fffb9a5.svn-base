package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.repositories.EstadoCuentaDatosDao;
import mx.com.kubo.services.EstadoCuentaDatosService;

@Service
public class EstadoCuentaDatosServiceImp implements EstadoCuentaDatosService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EstadoCuentaDatosDao dao;
	
	public Double getMontoExigible( String safi_credit_id ){
		return dao.getMontoExigible(safi_credit_id);
	}
	
	public Double getMontoParaLiquidar( String safi_credit_id ){
		return dao.getMontoParaLiquidar(safi_credit_id);
	}
	
}
