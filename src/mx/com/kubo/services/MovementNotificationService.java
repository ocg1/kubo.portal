package mx.com.kubo.services;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import mx.com.kubo.model.MovementNotification;
import mx.com.kubo.model.SafiDepositoRefere;

public interface MovementNotificationService {

	public boolean saveMovementNotification( MovementNotification newMovementNotification );
	public boolean updateMovementNotification( MovementNotification newMovementNotification );
	public List< MovementNotification > getMovementNotificationInStatusCeroList();
	public MovementNotification getMaxMovementNotification();
	public List<SafiDepositoRefere> getMovementToSafiByFolioCarga( BigInteger folioCarga );
	public List<SafiDepositoRefere> getMovementToSafiByFecha( Date fecha_de_origen );
	
}
