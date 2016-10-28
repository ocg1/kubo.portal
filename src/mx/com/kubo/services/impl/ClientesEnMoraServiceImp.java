package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ClientesEnMora;
import mx.com.kubo.repositories.ClientesEnMoraDao;
import mx.com.kubo.services.ClientesEnMoraService;

@Service
public class ClientesEnMoraServiceImp implements Serializable,ClientesEnMoraService {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private ClientesEnMoraDao dao;
	
	@Override
	public List<ClientesEnMora> getClientesEnMora( Integer event_id ){
		return dao.getClientesEnMora(  event_id );
	}
	
	@Override
	public List<ClientesEnMora> getClientesEnMoraByProspect( int prospectus_id ){
		return dao.getClientesEnMoraByProspect( prospectus_id );
	}

	
}
