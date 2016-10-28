package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;

public interface SavingAccountService {
	
	public abstract SavingAccount getSavingAccountByID(SavingAccountPK savingAccountPk);
	public abstract SavingAccount getSavingAccountByAccount(String savingAccountStr);
	public abstract SavingAccount getSavingAccountByProspectus(int prospectusID,int companyID);
	public abstract boolean addSavingAccount(SavingAccount newSavingAccount,int prospectusID,int companyID);
	public abstract boolean updateSavingAccount(SavingAccount savingAccount);
	public abstract List<SavingAccount> getListAccountByProspect(int prospectID,int companyID);
	public abstract boolean verifyAccount(SavingAccount savingAccount);

	public abstract List<SavingAccount> getListAccountActiveByProspect(int prospectID,int companyID);
	
	public abstract boolean executeSP_Relaciona_Menor(String safi_client_menor, String safi_client_tutor);
	
}
