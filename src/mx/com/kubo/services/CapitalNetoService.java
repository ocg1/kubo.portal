package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.CapitalNeto;
import mx.com.kubo.model.PrecioUdi;

public interface CapitalNetoService {

	public CapitalNeto getMaxCapitalNeto();
	public List<CapitalNeto> getListCapitalNeto();
	
	public PrecioUdi getMaxPrecioUdi();
	
}
