package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.TiendaCreditos;

public interface TiendaCreditosService {

	public List< TiendaCreditos> loadTiendaCreditosItems();
	public TiendaCreditos getTiendaCreditosItemBySolOrCred( Integer safi_solicitud_id , String safi_credit_id );
	public boolean actualizaTienda();
	public boolean update(TiendaCreditos tiendaRegistro); 
	
}
