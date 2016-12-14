package mx.com.kubo.repositories;

import java.util.Calendar;
import java.util.List;

import mx.com.kubo.model.TSafiCreditosMovs;
import mx.com.kubo.model.TSafiCuentasAhoMovDep;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.model.TSafiPosicionInt;

public interface EstadoCuentaDao {

	public List<TSafiPosicionInt>	getTSafiPosicionInt( Integer prospectus_id);
	public List<TSafiPagosCuota> getTSafiPagosCuota( Integer prospectus_id );
	public List<TSafiCreditosMovs> getTSafiCreditosMovs( Integer crdt, Integer amrt, Integer prospectus_id);
	public List<TSafiCuentasAhoMovDep> getTSafiCuentasAhoMovDep( Integer cuentaID );
	public Calendar getFechaCorte();
	
}
