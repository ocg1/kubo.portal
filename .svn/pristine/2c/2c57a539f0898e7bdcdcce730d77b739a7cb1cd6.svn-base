package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.SellingDetailHistory;

public interface SellingDetailHistoryDao {
	
	public List<SellingDetailHistory> getSellingDetailHistoryByProyectLoan(int proyect_loan_id);
	public List<SellingDetailHistory> getSellingDetailHistoryListByProspectus( int prospectus_id, int company_id );
    public SellingDetailHistory getLastSellingDetailHistoryByProspectus( int prospectus_id, int company_id );
	public boolean saveSellingDetailHistory( SellingDetailHistory newSellingDetailHistory );
	public boolean deleteSellingDetailHistory( SellingDetailHistory newSellingDetailHistory );
	
}
