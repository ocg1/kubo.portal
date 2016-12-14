package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.TSafiCreditosMovs;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.model.TSafiPosicionInt;
import mx.com.kubo.model.TSafiCuentasAhoMovDep;

public interface EstadoCuentaService {

 List<TSafiPosicionInt>	getTSafiPosicionInt( Integer prospectus_id );
 List<TSafiPagosCuota> getTSafiPagosCuota( Integer prospectus_id );
 
 List<TSafiCreditosMovs> getTSafiCreditosMovs( Integer crdt, Integer amrt, Integer prospectus_id);
 
 List<TSafiCuentasAhoMovDep> getTSafiCuentasAhoMovDep( Integer cuentaID );
 
 java.util.Calendar getFechaCorte();
	
}
