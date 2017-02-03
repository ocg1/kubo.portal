package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.PendingNotification;

public interface PendingNotificationDao {

	public boolean savePendingNotification( PendingNotification pending );
	
	public boolean updatePendingNotification( PendingNotification pending );
	
	public List<PendingNotification> getPendingNotificationStatusCero( );
	
	public boolean existPendingNotification( int company_id, int prospectus_id, int event_id );
	
}
