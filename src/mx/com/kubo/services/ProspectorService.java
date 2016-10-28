package mx.com.kubo.services;

import mx.com.kubo.model.Prospector;

public interface ProspectorService {

	public boolean saveProspector( Prospector prospector );
	public boolean updateProspector( Prospector prospector );
	public Prospector getMaxProspector( int prospectus_id, int company_id );
	
	
}
