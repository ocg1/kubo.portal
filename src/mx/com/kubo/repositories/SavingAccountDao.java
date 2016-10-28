package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;

public interface SavingAccountDao {
	public SavingAccount getSavingAccountByID(SavingAccountPK savingAccountPk);
	public SavingAccount getSavingAccountByAccount(String savingAccountStr);
	public boolean addSavingAccount(SavingAccount newSavingAccount,int prospectusID,int companyID);
	public boolean updateSavingAccount(SavingAccount savingAccount);
	public List<SavingAccount> getListAccountByProspect(int prospectID,int companyID);
	public List<SavingAccount> getListAccountActiveByProspect(int prospectID,int companyID);
	public  SavingAccount getSavingAccountByProspectus(int prospectusID,int companyID);
	public abstract boolean verifyAccount(SavingAccount savingAccount);
	public abstract boolean executeSP_Relaciona_Menor(String safi_client_menor, String safi_client_tutor);
}
