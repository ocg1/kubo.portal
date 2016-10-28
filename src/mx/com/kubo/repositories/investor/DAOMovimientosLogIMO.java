package mx.com.kubo.repositories.investor;

import java.util.List;

import mx.com.kubo.model.investor.MovimientosLog;

public interface DAOMovimientosLogIMO 
{
	Integer getLog_id();
	
	boolean registrar(MovimientosLog movimiento_log);
	
	List<MovimientosLog> getLista_movimientos(int prospectus_id, int company_id);
}
