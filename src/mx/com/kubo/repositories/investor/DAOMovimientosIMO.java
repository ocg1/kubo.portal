package mx.com.kubo.repositories.investor;

import java.util.List;

import mx.com.kubo.model.investor.MotivosCancelacion;

public interface DAOMovimientosIMO 
{
	List<MotivosCancelacion> getLista_motivos_cancelacion(int product_type_id);		
}
