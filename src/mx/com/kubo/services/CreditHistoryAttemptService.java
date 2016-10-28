package mx.com.kubo.services;

import java.util.Date;
import java.util.List;

import mx.com.kubo.model.CreditHistoryAttempt;

public interface CreditHistoryAttemptService 
{
	boolean add(CreditHistoryAttempt newConsultation);
	boolean borrar_consulta(CreditHistoryAttempt consulta);
	
	Integer getConsultas_anteriores(CreditHistoryAttempt objCredit, Date fechaLimite);
	Integer getCreditHistoryAttemptByDate(Date fechaLimite, Integer prospectus_id, Integer company_id);	
	Integer getCreditHistoryAttemptByCheck(Date fechaLimite, Integer prospectus_id, Integer company_id);
	
	CreditHistoryAttempt getUltima_consulta(int prospectus_id, int company_id);
	
	List<CreditHistoryAttempt> getAttemptsByProspectus(int prospectus_id);
}
