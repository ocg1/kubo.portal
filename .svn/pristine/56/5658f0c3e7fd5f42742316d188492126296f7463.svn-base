package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.FullName;
import mx.com.kubo.model.FullNamePK;
import mx.com.kubo.repositories.FullNameDao;
import mx.com.kubo.services.FullNameService;

@Service
public class FullNameServiceImp implements FullNameService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FullNameDao dao;
	
	
	public boolean saveFullName( FullName full_name ){
		return dao.saveFullName(full_name);
	}
	
	public boolean updateFullName( FullName full_name ){
		return dao.updateFullName(full_name);
	}
	
	public FullName getFullName( FullNamePK pk ){
		return dao.getFullName(pk);
	}
	
}
