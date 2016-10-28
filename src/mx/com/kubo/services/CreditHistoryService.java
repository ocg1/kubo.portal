package mx.com.kubo.services;

import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryPK;

public interface CreditHistoryService {
	
	CreditHistory getCreditHistoryById(CreditHistoryPK pk);
	void add(CreditHistory newCreditHistory);
	void update(CreditHistory newCreditHistory);
	CreditHistory merge(CreditHistory newCreditHistory);
	
}
