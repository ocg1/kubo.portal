package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.ExpensesHistory;
import mx.com.kubo.repositories.ExpensesHistoryDao;
import mx.com.kubo.services.ExpensesHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesHistoryServiceImp implements ExpensesHistoryService {

	@Autowired
	private ExpensesHistoryDao repository;
	
	@Override
	public List<ExpensesHistory> getExpensesHistoryByProyectLoan(int proyect_loan_id){
		return repository.getExpensesHistoryByProyectLoan(proyect_loan_id);
	}
	
	@Override
	public List<ExpensesHistory> getExpensesHistoryListByProspectus( int prospectus_id, int company_id ){
		return repository.getExpensesHistoryListByProspectus(prospectus_id, company_id);
	}
	
	@Override
    public ExpensesHistory getLastExpensesHistoryByProspectus( int prospectus_id, int company_id ){
		return repository.getLastExpensesHistoryByProspectus(prospectus_id, company_id);
	}
	
	@Override
	public boolean saveExpensesHistory( ExpensesHistory newExpensesHistory ){
		return repository.saveExpensesHistory(newExpensesHistory);
	}
	
	@Override
	public boolean deleteExpensesHistory( ExpensesHistory newExpensesHistory ){
		return repository.deleteExpensesHistory(newExpensesHistory);
	}
	
}
