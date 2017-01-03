package mx.com.kubo.repositories;

import mx.com.kubo.model.LegalLimit;
import mx.com.kubo.model.LegalLimitPK;

public interface LegalLimitDao {

	public LegalLimit getLegalLimitByPK( LegalLimitPK lpk );
	
}
