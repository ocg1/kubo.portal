package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ClientesEnMora;

public interface ClientesEnMoraDao {
	
	public List<ClientesEnMora> getClientesEnMora( Integer event_id );
	public List<ClientesEnMora> getClientesEnMoraByProspect( int prospectus_id );

}
