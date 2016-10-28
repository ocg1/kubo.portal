package mx.com.kubo.managedbeans.investor.movimientos;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.investor.MotivosCancelacion;
import mx.com.kubo.model.investor.MovimientosLog;

public interface CancelacionCuentaIMO 
{
	void setSesion(SessionBean sesion);
	
	void setInversionista(Membership inversionista);
	
	void setLista_cuentas    (List<InvestorsAccounts>  lista_cuentas);
	void setLista_motivos    (List<MotivosCancelacion> lista_motivos);
	
	void listener_motivo(AjaxBehaviorEvent evento);
	
	void notificar();
	
	List<MotivosCancelacion> getLista_motivos();
	List<InvestorsAccounts>  getLista_cuentas();
	List<MovimientosLog>     getLista_movimientos();
}
