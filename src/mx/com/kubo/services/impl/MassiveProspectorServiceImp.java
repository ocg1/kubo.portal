package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.MassiveProspector;
import mx.com.kubo.repositories.MassiveProspectorDao;
import mx.com.kubo.services.MassiveProspectorService;

@Service
public class MassiveProspectorServiceImp implements Serializable, MassiveProspectorService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MassiveProspectorDao dao;
	
	public boolean massiveInsert( String insertquery ){
		return dao.massiveInsert(insertquery);
	}
	public boolean addElement( MassiveProspector element ){
		return dao.addElement(element);
	}
	public List<MassiveProspector> getMassiveLstByFileName( String filename ){
		return dao.getMassiveLstByFileName(filename);
	}
	public boolean updateElement( MassiveProspector element ){
		return dao.updateElement(element);
	}
	
}
