package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.CapitalNeto;
import mx.com.kubo.model.PrecioUdi;
import mx.com.kubo.repositories.CapitalNetoDao;
import mx.com.kubo.services.CapitalNetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapitalNetoServiceImp implements CapitalNetoService, Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CapitalNetoDao capitalnetodao ;
	
	@Override
	public CapitalNeto getMaxCapitalNeto(){
		return capitalnetodao.getMaxCapitalNeto();
	}
	
	@Override
	public List<CapitalNeto> getListCapitalNeto(){
		return capitalnetodao.getListCapitalNeto();
	}
	
	public PrecioUdi getMaxPrecioUdi(){
		
		return capitalnetodao.getMaxPrecioUdi();
		
	}
	
}
