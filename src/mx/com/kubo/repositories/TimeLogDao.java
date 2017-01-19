package mx.com.kubo.repositories;

import mx.com.kubo.model.TimeLog;

public interface TimeLogDao {

	public boolean saveTimeLog( TimeLog timelog );
	
}
