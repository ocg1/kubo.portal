package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.CobranzaPreventiva;

public interface CobranzaPreventivaService {

	public List<CobranzaPreventiva> getCobranzaPreventivaLst( String fecha );
	public CobranzaPreventiva getCobranzaPreventivaProspecto( String fecha, Integer prospectus_id );
	
}
