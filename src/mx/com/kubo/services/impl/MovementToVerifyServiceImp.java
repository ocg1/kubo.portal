package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.MovementToVerify;
import mx.com.kubo.repositories.MovementToVerifyDao;
import mx.com.kubo.services.MovementToVerifyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementToVerifyServiceImp implements Serializable,MovementToVerifyService   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MovementToVerifyDao repository;
	
	@Override
	public List<MovementToVerify> getMovementToVerifyActiveList(){
		return repository.getMovementToVerifyActiveList();
	}
	
	@Override
	public boolean updateMovementToVerify( MovementToVerify movement ){
		return repository.updateMovementToVerify( movement );
	}
	
}
