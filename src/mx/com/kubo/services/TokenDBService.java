package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.TokenDB;

public interface TokenDBService {

	public List<TokenDB> getTokenDBListByTokenId( String token, Integer prospectus_id );
	public boolean insertToken( TokenDB tokenBean );
	public boolean updateTokenUsed( TokenDB tokenBean );
	public TokenDB getTokenDBById( String token_id );
	
}
