package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.Reca;
import mx.com.kubo.repositories.RecaDao;
import mx.com.kubo.services.RecaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecaServiceImp implements RecaService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RecaDao recarepository;
	
	@Override
	public List<Reca> getRecaList(){
		return recarepository.getRecaList();
	}
	
	
}
