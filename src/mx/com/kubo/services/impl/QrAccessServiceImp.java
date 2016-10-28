package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.QrAccess;
import mx.com.kubo.model.ViewHomeStatistics;
import mx.com.kubo.repositories.QrAccessDao;
import mx.com.kubo.services.QrAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QrAccessServiceImp 
implements QrAccessService, Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private QrAccessDao dao;
	
	public boolean saveQrAccess(QrAccess newQrAccess)
	{
		return dao.saveQrAccess(newQrAccess);
	}

	public ViewHomeStatistics getHomeStatistics() 
	{
		return dao.getHomeStatistics();
	}
	
}
