package mx.com.kubo.repositories;

import mx.com.kubo.model.TableroNormativoDetallado;
import mx.com.kubo.model.TableroNormativoResumen;

public interface TableroNormativoDao {

	public TableroNormativoResumen getResumenTableroNormativo();
	public TableroNormativoDetallado getDetalleTableroNormativo( Integer proyect_loan_id );
	
}
