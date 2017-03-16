package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.MovementNotification;
import mx.com.kubo.model.SafiDepositoRefere;
import mx.com.kubo.repositories.MovementNotificationDao;
import mx.com.kubo.services.MovementNotificationService;

@Service
public class MovementNotificationServiceImp implements MovementNotificationService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MovementNotificationDao movementnotificationdao;
	
	@Override
	public boolean saveMovementNotification( MovementNotification newMovementNotification ){
		return movementnotificationdao.saveMovementNotification(newMovementNotification);
	}
	
	@Override
	public boolean updateMovementNotification( MovementNotification newMovementNotification ){
		return movementnotificationdao.updateMovementNotification( newMovementNotification );
	}
	
	@Override
	public List< MovementNotification > getMovementNotificationInStatusCeroList(){
		return movementnotificationdao. getMovementNotificationInStatusCeroList();
	}
	
	@Override
	public MovementNotification getMaxMovementNotification(){
		return movementnotificationdao.getMaxMovementNotification();
	}
	
	@Override
	public List<SafiDepositoRefere> getMovementToSafiByFolioCarga( BigInteger folioCarga ){
		return movementnotificationdao.getMovementToSafiByFolioCarga( folioCarga );
	}
	
	@Override
	public List<SafiDepositoRefere> getMovementToSafiByFecha( Date fecha_de_origen ){
		return movementnotificationdao.getMovementToSafiByFecha( fecha_de_origen );
	}
	
	public void insertaMovementToSafi( String tipoOper ){
		movementnotificationdao.insertaMovementToSafi(  tipoOper );
	}
	
}
