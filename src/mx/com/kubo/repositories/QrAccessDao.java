package mx.com.kubo.repositories;

import mx.com.kubo.model.QrAccess;
import mx.com.kubo.model.ViewHomeStatistics;

public interface QrAccessDao 
{	
	boolean saveQrAccess(QrAccess newQrAccess);

	ViewHomeStatistics getHomeStatistics();
	
}
