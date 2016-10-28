package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.AmortizacionInversionista;
import mx.com.kubo.repositories.AmortizacionInversionistaDao;
import mx.com.kubo.services.AmortizacionInversionistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmortizacionInversionistaServiceImp implements Serializable, AmortizacionInversionistaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AmortizacionInversionistaDao repository;
	
	@Override
	public List<AmortizacionInversionista> getAmortizacionInversionistaListByIdInver(Integer id){
		
		return repository.getAmortizacionInversionistaListByIdInver( id );
		
	}
	
}
