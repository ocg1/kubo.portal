package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.TSafiCreditosMovs;
import mx.com.kubo.model.TSafiCuentasAhoMovDep;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.model.TSafiPosicionInt;
import mx.com.kubo.repositories.EstadoCuentaDao;
import mx.com.kubo.services.EstadoCuentaService;

@Service
public class EstadoCuentaServiceImp implements Serializable, EstadoCuentaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	EstadoCuentaDao dao;
	
	public List<TSafiPosicionInt>	getTSafiPosicionInt( Integer prospectus_id){
		
		return dao.getTSafiPosicionInt( prospectus_id );
		
	}
	
	public List<TSafiPagosCuota> getTSafiPagosCuota( Integer prospectus_id ){
		return dao.getTSafiPagosCuota( prospectus_id );
	}
	
	public List<TSafiCreditosMovs> getTSafiCreditosMovs( Integer crdt, Integer amrt, Integer prospectus_id){
		return dao.getTSafiCreditosMovs(  crdt,  amrt,  prospectus_id);
	}
	
	public List<TSafiCuentasAhoMovDep> getTSafiCuentasAhoMovDep( Integer cuentaID ){
		return dao.getTSafiCuentasAhoMovDep(  cuentaID );
	}
	
	public Calendar getFechaCorte(){
		return dao.getFechaCorte();
	}
	
}
