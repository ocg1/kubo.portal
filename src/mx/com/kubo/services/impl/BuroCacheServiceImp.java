package mx.com.kubo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.BuroCache;
import mx.com.kubo.repositories.BuroCacheDao;
import mx.com.kubo.services.BuroCacheService;

@Service
public class BuroCacheServiceImp implements BuroCacheService 
{
	@Autowired
	private BuroCacheDao dao;
	
	public BuroCache getBuroCache( String burSolNum )
	{
		return dao.getBuroCache(burSolNum);
	}
	
	public boolean saveBuroCache( BuroCache cache )
	{
		return dao.saveBuroCache(cache);
	}

	public boolean delete(String burSolNum) 
	{
		return dao.delete(burSolNum);
	}
	
}
