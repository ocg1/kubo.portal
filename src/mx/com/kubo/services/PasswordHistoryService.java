package mx.com.kubo.services;

import mx.com.kubo.model.PasswordHistory;

public interface PasswordHistoryService {
	
	public boolean savePasswordHistory( PasswordHistory passwordhistory);
	public boolean existPassword ( String password , int prospectus_id, int company_id );
	public PasswordHistory getPasswordHistorySelec ( String password , int prospectus_id, int company_id );
	
}
