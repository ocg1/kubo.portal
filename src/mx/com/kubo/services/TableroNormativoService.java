package mx.com.kubo.services;

import mx.com.kubo.model.TableroNormativoDetallado;
import mx.com.kubo.model.TableroNormativoResumen;

public interface TableroNormativoService {

	public TableroNormativoResumen getResumenTableroNormativo();
	public TableroNormativoDetallado getDetalleTableroNormativo( Integer proyect_loan_id );
	
}
