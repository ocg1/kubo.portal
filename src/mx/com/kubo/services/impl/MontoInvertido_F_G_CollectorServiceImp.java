package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.MontoInvertido_F_G_Collector;
import mx.com.kubo.repositories.MontoInvertido_F_G_Collector_Dao;
import mx.com.kubo.services.MontoInvertido_F_G_CollectorService;

@Service
public class MontoInvertido_F_G_CollectorServiceImp implements Serializable, MontoInvertido_F_G_CollectorService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MontoInvertido_F_G_Collector_Dao repository;
	
	@Override
	public List<MontoInvertido_F_G_Collector> getMontoInvertido_F_G(String safi_account){
		return repository.getMontoInvertido_F_G(safi_account);
	}
	
}
