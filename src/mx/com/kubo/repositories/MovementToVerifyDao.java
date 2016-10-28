package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.MovementToVerify;

public interface MovementToVerifyDao {

	public List<MovementToVerify> getMovementToVerifyActiveList();
	
	public boolean updateMovementToVerify( MovementToVerify movement );
	
	
}
