package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.BuroCache;
import mx.com.kubo.repositories.BuroCacheDao;
import mx.com.kubo.services.BuroCacheService;

@Service
public class BuroCacheServiceImp implements Serializable,BuroCacheService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BuroCacheDao repository;
	
	public BuroCache getBuroCache( String burSolNum ){
		return repository.getBuroCache(burSolNum);
	}
	public boolean saveBuroCache( BuroCache cache ){
		return repository.saveBuroCache(cache);
	}
	
}
