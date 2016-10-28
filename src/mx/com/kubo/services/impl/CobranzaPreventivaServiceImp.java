package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.CobranzaPreventiva;
import mx.com.kubo.repositories.CobranzaPreventivaDao;
import mx.com.kubo.services.CobranzaPreventivaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CobranzaPreventivaServiceImp implements Serializable, CobranzaPreventivaService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CobranzaPreventivaDao dao;
	
	
	public List<CobranzaPreventiva> getCobranzaPreventivaLst( String fecha ){
		return dao.getCobranzaPreventivaLst( fecha );
	}
	
	public CobranzaPreventiva getCobranzaPreventivaProspecto( String fecha, Integer prospectus_id ){
		return dao.getCobranzaPreventivaProspecto(fecha, prospectus_id); 
	}
	
}
