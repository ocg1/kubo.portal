package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.RendimientosInv;
import mx.com.kubo.repositories.RendimientosInvDao;
import mx.com.kubo.services.RendimientosInvService;

@Service
public class RendimientosInvServiceImp implements Serializable, RendimientosInvService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RendimientosInvDao dao;
	
	public List<RendimientosInv> getRendimientosInvLst( int cuentaAhoId ){
		return dao.getRendimientosInvLst( cuentaAhoId );
	}
	
}
