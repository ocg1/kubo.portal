package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.TableroNormativoDetallado;
import mx.com.kubo.model.TableroNormativoResumen;
import mx.com.kubo.repositories.TableroNormativoDao;
import mx.com.kubo.services.TableroNormativoService;

@Service
public class TableroNormativoServiceImp implements Serializable,TableroNormativoService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TableroNormativoDao dao;
	
	public TableroNormativoResumen getResumenTableroNormativo(){
		
		return dao.getResumenTableroNormativo();
		
	}
	
	public TableroNormativoDetallado getDetalleTableroNormativo( Integer proyect_loan_id ){
	
		return dao.getDetalleTableroNormativo(proyect_loan_id);
		
	}
	
}
