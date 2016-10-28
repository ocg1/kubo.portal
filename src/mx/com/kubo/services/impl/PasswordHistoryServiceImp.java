package mx.com.kubo.services.impl;

import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.repositories.PasswordHistoryDao;
import mx.com.kubo.services.PasswordHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordHistoryServiceImp implements PasswordHistoryService {

	@Autowired
	private PasswordHistoryDao passwordhistoryRepository;
	
	@Override
	public boolean savePasswordHistory( PasswordHistory passwordhistory){
		
		return passwordhistoryRepository.savePasswordHistory(passwordhistory);
		
	}
	
	@Override
	public boolean existPassword ( String password , int prospectus_id, int company_id ){
		
		return passwordhistoryRepository.existPassword(password, prospectus_id, company_id);
		
	}
	
	@Override
	public PasswordHistory getPasswordHistorySelec ( String password , int prospectus_id, int company_id ){
		
		return passwordhistoryRepository.getPasswordHistorySelec(password, prospectus_id, company_id);
		
	}
	
}
