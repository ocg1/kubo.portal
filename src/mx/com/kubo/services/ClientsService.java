package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.Clients;
import mx.com.kubo.model.ClientsPK;

public interface ClientsService 
{
	List<Clients> getListClients();
	
	Clients getClientById( ClientsPK pk );
	
	boolean saveClient( Clients client );
	
}
