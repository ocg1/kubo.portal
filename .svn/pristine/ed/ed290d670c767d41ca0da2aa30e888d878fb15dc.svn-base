package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.IncomeDetailHistory;
import mx.com.kubo.repositories.IncomeDetailHistoryDao;
import mx.com.kubo.services.IncomeDetailHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeDetailHistoryServiceImp  implements IncomeDetailHistoryService {
	
	@Autowired
	private IncomeDetailHistoryDao repository;
	
	@Override
	public List<IncomeDetailHistory> getIncomeDetailHistoryByProyectLoan(int proyect_loan_id){
		return repository.getIncomeDetailHistoryByProyectLoan(proyect_loan_id);
	}
	
	@Override
	public List<IncomeDetailHistory> getIncomeDetailHistoryListByProspectus( int prospectus_id, int company_id ){
		return repository.getIncomeDetailHistoryListByProspectus(prospectus_id, company_id);
	}
	
	@Override
    public IncomeDetailHistory getLastIncomeDetailHistoryByProspectus( int prospectus_id, int company_id ){
		return repository.getLastIncomeDetailHistoryByProspectus(prospectus_id, company_id);
	}
	
	@Override
	public boolean saveIncomeDetailHistory( IncomeDetailHistory newIncomeDetailHistory ){
		return repository.saveIncomeDetailHistory(newIncomeDetailHistory);
	}
	
	@Override
	public boolean deleteIncomeDetailHistory( IncomeDetailHistory newIncomeDetailHistory ){
		return repository.deleteIncomeDetailHistory(newIncomeDetailHistory);
	}
	
}
