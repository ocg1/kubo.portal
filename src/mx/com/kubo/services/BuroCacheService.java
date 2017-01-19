package mx.com.kubo.services;

import mx.com.kubo.model.BuroCache;

public interface BuroCacheService 
{
	BuroCache getBuroCache( String burSolNum );
	
	boolean saveBuroCache( BuroCache cache );
	
	boolean delete(String burSolNum);
}
