package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.ClientesEnMora;

public interface ClientesEnMoraService {

	public List<ClientesEnMora> getClientesEnMora( Integer event_id );
	public List<ClientesEnMora> getClientesEnMoraByProspect( int prospectus_id );

	
}
