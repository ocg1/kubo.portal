package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.IdProviderMassive;
import mx.com.kubo.model.TrackingCode;

public interface IdProviderMassiveService {

	public List<IdProviderMassive> getIdProviderMassiveByTrackingCode( String code );
	public boolean saveIdProviderMassive( IdProviderMassive massive ) ;
	public boolean saveTrackingCode( TrackingCode tracking ) ;
	public TrackingCode getTrackingCodeByCode( String  code ) ;
	public boolean updateIdProviderMassive( IdProviderMassive massive );
	
}
