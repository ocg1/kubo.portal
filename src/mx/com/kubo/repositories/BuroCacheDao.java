package mx.com.kubo.repositories;

import mx.com.kubo.model.BuroCache;

public interface BuroCacheDao {

	BuroCache getBuroCache( String burSolNum );
	boolean saveBuroCache( BuroCache cache );
	
}
