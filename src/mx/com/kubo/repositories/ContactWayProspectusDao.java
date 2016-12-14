package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ContactWay;
import mx.com.kubo.model.ContactWayProspectus;

public interface ContactWayProspectusDao {
	
	public List<ContactWayProspectus> getContactWayProspectusList( int company_id, int propsectus_id );
	public List<ContactWay> getContactWayCatEnabled();
	public List<ContactWay> getAllContactWayCat();
	
	public boolean saveContactWayProspectus( ContactWayProspectus contact );
	public boolean removeContactWayProspectus( ContactWayProspectus contact );
	
}
