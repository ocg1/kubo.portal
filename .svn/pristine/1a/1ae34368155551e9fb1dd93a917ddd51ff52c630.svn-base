package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.ClientCarteraKiva;
import mx.com.kubo.repositories.ClientCarteraKivaDao;
import mx.com.kubo.services.ClientCarteraKivaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientCarteraKivaServiceImp implements ClientCarteraKivaService {
	
	@Autowired
	private ClientCarteraKivaDao  clientCarteraKivaDao;
	
	@Override
	public List<ClientCarteraKiva> getClientCarteraKivaListByIsKuboPropertyByIsNotReportLog(Integer isKuboProperty) {
		return clientCarteraKivaDao.getClientCarteraKivaListByIsKuboPropertyByIsNotReportLog(isKuboProperty);
	}
}
