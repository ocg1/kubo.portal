package mx.com.kubo.repositories;

import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryPK;

public interface CreditHistoryDao {
	
	public CreditHistory loadSelectedCreditHistory(CreditHistoryPK pk);
	public void saveCreditHistory(CreditHistory newCreditHistory);
	public void updateCreditHistory(CreditHistory newCreditHistory);
	CreditHistory merge(CreditHistory newCreditHistory);
}
