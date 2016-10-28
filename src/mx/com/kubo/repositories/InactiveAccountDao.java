package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.InactiveAccount;

public interface InactiveAccountDao {

	public List<InactiveAccount> getInactiveAccountList();
	
}
