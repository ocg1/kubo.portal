package mx.com.kubo.repositories;

import mx.com.kubo.model.AccountType;
import mx.com.kubo.model.AccountTypePK;

public interface AccountTypeDao {
	public abstract AccountType getAccountTypeByID(AccountTypePK accountTypePK);
}
