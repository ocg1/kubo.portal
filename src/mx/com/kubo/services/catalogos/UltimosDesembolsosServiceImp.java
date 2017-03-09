package mx.com.kubo.services.catalogos;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.UltimosDesembolsos;
import mx.com.kubo.repositories.UltimosDesembolsosDao;
import mx.com.kubo.services.UltimosDesembolsosService;

@Service
public class UltimosDesembolsosServiceImp implements Serializable, UltimosDesembolsosService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	UltimosDesembolsosDao dao;
	
	public List<UltimosDesembolsos> getUltimosDesembolsos(){
		return dao.getUltimosDesembolsos();
	}
	

}
