package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.TokenDB;

public interface TokenDBDao {

	public List<TokenDB> getTokenDBListByTokenId( String token, Integer prospectus_id );
	public boolean insertToken( TokenDB tokenBean );
	public boolean updateTokenUsed( TokenDB tokenBean );
	public TokenDB getTokenDBById( String token_id );
		   
}
