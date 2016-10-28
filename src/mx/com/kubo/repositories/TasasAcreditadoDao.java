package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.TasasAcreditado;

public interface TasasAcreditadoDao {

	public TasasAcreditado getTasaAcreditadoByRate(Double rate);
	public List<TasasAcreditado> getListTasasAcreditado();
	
}
