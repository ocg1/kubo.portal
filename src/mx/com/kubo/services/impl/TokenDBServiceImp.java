package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.TokenDB;
import mx.com.kubo.repositories.TokenDBDao;
import mx.com.kubo.services.TokenDBService;

@Service
public class TokenDBServiceImp implements Serializable, TokenDBService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TokenDBDao repository;
	
	public List<TokenDB> getTokenDBListByTokenId( String token, Integer prospectus_id ){
		return repository.getTokenDBListByTokenId(token, prospectus_id);
	}
	
	public boolean insertToken( TokenDB tokenBean ){
		return repository.insertToken(tokenBean);
	}
	
	public boolean updateTokenUsed( TokenDB tokenBean ){
		return repository.updateTokenUsed(tokenBean);
	}
	
	public TokenDB getTokenDBById( String token_id ){
		return repository.getTokenDBById(token_id);
	}
	
}
