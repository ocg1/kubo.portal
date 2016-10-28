package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.TiendaCreditos;
import mx.com.kubo.repositories.TiendaCreditosDao;
import mx.com.kubo.services.TiendaCreditosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaCreditosServiceImp implements TiendaCreditosService {
	
	@Autowired
	private TiendaCreditosDao tiendacreditosdao;
	
	@Override
	public List< TiendaCreditos> loadTiendaCreditosItems(){
		return tiendacreditosdao.loadTiendaCreditosItems();
	}
	
	@Override
	public TiendaCreditos getTiendaCreditosItemBySolOrCred( Integer safi_solicitud_id , String safi_credit_id ){
		return tiendacreditosdao.getTiendaCreditosItemBySolOrCred( safi_solicitud_id ,  safi_credit_id );
	}
	
	@Override
	public boolean actualizaTienda(){
		return tiendacreditosdao.actualizaTienda();
	}
	
	@Override
	public boolean update(TiendaCreditos tiendaRegistro){
		return tiendacreditosdao.update(tiendaRegistro);
	}
	
}
