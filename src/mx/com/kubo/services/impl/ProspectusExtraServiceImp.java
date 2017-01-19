package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.ProspectusExtra;
import mx.com.kubo.repositories.ProspectusExtraDao;
import mx.com.kubo.services.ProspectusExtraService;

@Service
public class ProspectusExtraServiceImp implements Serializable, ProspectusExtraService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ProspectusExtraDao dao;
	
	public boolean saveProspectusExtra( ProspectusExtra extra ){
		return dao.saveProspectusExtra( extra );
	}
	
	public boolean updateProspectusExtra( ProspectusExtra extra ){
		return dao.updateProspectusExtra( extra );
	}
	
}
