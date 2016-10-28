package mx.com.kubo.repositories;

import java.util.Date;

import mx.com.kubo.model.CreditHistoryAttempt;

public interface DAOCreditHistoryAttemptIMO 
{
	Integer getCreditHistoryAttemptByAll(CreditHistoryAttempt objCredit, Date fechaLimite);
}
