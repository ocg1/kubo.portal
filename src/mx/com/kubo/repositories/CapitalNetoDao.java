package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.CapitalNeto;
import mx.com.kubo.model.PrecioUdi;

public interface CapitalNetoDao {

	public CapitalNeto getMaxCapitalNeto();
	
	public List<CapitalNeto> getListCapitalNeto();
	
	public PrecioUdi getMaxPrecioUdi();
	
}
