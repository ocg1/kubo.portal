package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ProyeccionGraficaInv;
import mx.com.kubo.repositories.ProyeccionGraficaInvDao;
import mx.com.kubo.services.ProyeccionGraficaInvService;

@Service
public class ProyeccionGraficaInvServiceImp implements Serializable, ProyeccionGraficaInvService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProyeccionGraficaInvDao dao;
	
	
	public List<ProyeccionGraficaInv> getProyeccionGraficaInvLst( int cuentaAhoId ){
		return dao.getProyeccionGraficaInvLst(cuentaAhoId);
	}
	
}
