package mx.com.kubo.repositories;

import mx.com.kubo.model.Referred;
import mx.com.kubo.model.ReferredPK;

public interface ReferredDao {

	public boolean addReferred( Referred referred );
	public boolean removeReferred( Referred referred );
	public boolean updateReferred( Referred referred );
	public Referred getReferred( ReferredPK pk );
	public Referred getQuienLoRecomendo( int prospectus_id_destiny,int company_id );
	
}
