package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ProyeccionGraficaInv;

public interface ProyeccionGraficaInvDao {

	public List<ProyeccionGraficaInv> getProyeccionGraficaInvLst( int cuentaAhoId );
	
}
