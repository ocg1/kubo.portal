package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.PagareCollector;

public interface PagareCollectorDao {

	public List<PagareCollector> getPagareCollectorList( String safi_credit_id );
	
}
