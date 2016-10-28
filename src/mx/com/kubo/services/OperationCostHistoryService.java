package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.OperationCostHistory;

public interface OperationCostHistoryService  {
	
	public List<OperationCostHistory> getOperationCostHistoryByProyectLoan(int proyect_loan_id);
	public List<OperationCostHistory> getOperationCostHistoryListByProspectus( int prospectus_id, int company_id );
    public OperationCostHistory getLastOperationCostHistoryByProspectus( int prospectus_id, int company_id );
	public boolean saveOperationCostHistory( OperationCostHistory newOperationCostHistory );
	public boolean deleteOperationCostHistory( OperationCostHistory newOperationCostHistory );
	
}
