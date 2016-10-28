package mx.com.kubo.services.investor;

import java.util.List;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.investor.MotivosCancelacion;
import mx.com.kubo.model.investor.MovimientosLog;

public interface ServiceMovimientosIMO 
{
	void setSesion(SessionBean sesion);
	
	void setMovimiento(MovimientosIMO movimiento);
	
	void setSAFI_client_id(String client_id);
	
	void registrar_cancelacion();
	void setupClientSAFIData(List<String> SAFIcuenta);
	
	List<InvestorsAccounts>  getLista_cuentas();
	List<MotivosCancelacion> getLista_motivos_cancelacion(int product_type_id);
	List<MovimientosLog>     getLista_movimientos();
}
