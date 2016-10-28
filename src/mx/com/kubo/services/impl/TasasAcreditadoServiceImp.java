package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.TasasAcreditado;
import mx.com.kubo.repositories.TasasAcreditadoDao;
import mx.com.kubo.services.TasasAcreditadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasasAcreditadoServiceImp implements TasasAcreditadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TasasAcreditadoDao tasasAcreditadorepository;
	
	@Override
	public TasasAcreditado getTasaAcreditadoByRate(Double rate){
		return tasasAcreditadorepository.getTasaAcreditadoByRate(rate);
	}
	
	@Override
	public List<TasasAcreditado> getListTasasAcreditado(){
		
		return tasasAcreditadorepository.getListTasasAcreditado();
		
	}
	
	
}
