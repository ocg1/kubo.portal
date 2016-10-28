package mx.com.kubo.repositories.investor;

import mx.com.kubo.model.investor.MovimientosLog;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("dao_movimientos_log")
public class DAOMovimientosLogIMP extends DAOMovimientosLogDMO
implements DAOMovimientosLogIMO
{		
	@Transactional
	public final boolean registrar(MovimientosLog movimiento_log)
	{
		registrar_OK = false;
		
		try
		{
			em.persist(movimiento_log);
			
			registrar_OK = true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return registrar_OK;
	}
}
