package mx.com.kubo.repositories;
import java.util.List;

import mx.com.kubo.model.IncomeHistory;

public interface IncomeHistoryDao {

	public List<IncomeHistory> getIncomeHistoryByProyectLoan(int proyect_loan_id);
	public List<IncomeHistory> getIncomeHistoryListByProspectus( int prospectus_id, int company_id );
    public IncomeHistory getLastIncomeHistoryByProspectus( int prospectus_id, int company_id );
	public boolean saveIncomeHistory( IncomeHistory newIncomeHistory );
	public boolean deleteIncomeHistory( IncomeHistory newIncomeHistory );
	
}
