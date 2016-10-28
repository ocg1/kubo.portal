package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ExpensesHistory;

public interface ExpensesHistoryDao {

	public List<ExpensesHistory> getExpensesHistoryByProyectLoan(int proyect_loan_id);
	public List<ExpensesHistory> getExpensesHistoryListByProspectus( int prospectus_id, int company_id );
    public ExpensesHistory getLastExpensesHistoryByProspectus( int prospectus_id, int company_id );
	public boolean saveExpensesHistory( ExpensesHistory newExpensesHistory );
	public boolean deleteExpensesHistory( ExpensesHistory newExpensesHistory );
	
}
