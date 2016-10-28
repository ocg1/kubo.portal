package mx.com.kubo.services.fondeo.seguro;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.Clients;
import mx.com.kubo.model.ClientsPK;
import mx.com.kubo.repositories.ClientsDao;
import mx.com.kubo.services.ClientsService;


@Service
public class ClientsServiceImp implements ClientsService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ClientsDao repository;
	
	public List<Clients> getListClients(){
		return repository.getListClients();
	}
	public Clients getClientById( ClientsPK pk ){
		return repository.getClientById(pk);
	}
	public boolean saveClient( Clients client ){
		return repository.saveClient(client);
	}
	
}
