package mx.com.kubo.registro.consulta.historial;

import mx.com.kubo.registro.ChangeControlIMO;
import mx.com.kubo.services.CreditHistoryService;

public interface HistorialIMO extends ChangeControlIMO
{
	void setService_credit_history(CreditHistoryService service);
}
