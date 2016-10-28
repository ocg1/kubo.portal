package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.RendimientosInv;

public interface RendimientosInvDao {

	public List<RendimientosInv> getRendimientosInvLst( int cuentaAhoId );
	
}
