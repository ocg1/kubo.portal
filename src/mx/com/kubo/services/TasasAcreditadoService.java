package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.TasasAcreditado;

public interface TasasAcreditadoService {

	public TasasAcreditado getTasaAcreditadoByRate(Double rate);
	public List<TasasAcreditado> getListTasasAcreditado();
	
}
