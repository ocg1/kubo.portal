package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.IncomeDetailHistory;

public interface IncomeDetailHistoryDao {
	
	public List<IncomeDetailHistory> getIncomeDetailHistoryByProyectLoan(int proyect_loan_id);
	public List<IncomeDetailHistory> getIncomeDetailHistoryListByProspectus( int prospectus_id, int company_id );
    public IncomeDetailHistory getLastIncomeDetailHistoryByProspectus( int prospectus_id, int company_id );
	public boolean saveIncomeDetailHistory( IncomeDetailHistory newIncomeDetailHistory );
	public boolean deleteIncomeDetailHistory( IncomeDetailHistory newIncomeDetailHistory );
	
}
