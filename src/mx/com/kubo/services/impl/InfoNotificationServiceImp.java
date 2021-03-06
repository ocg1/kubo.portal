package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.InfoNotification;
import mx.com.kubo.repositories.InfoNotificationDao;
import mx.com.kubo.services.InfoNotificationService;

@Service
public class InfoNotificationServiceImp implements Serializable, InfoNotificationService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private InfoNotificationDao dao;
	
	public List<InfoNotification> getInfoNotificationBy( Integer company_id, Integer prospectus_id, Integer proyect_loan_id, int screen_info_id ){
		
		return dao.getInfoNotificationBy(company_id, prospectus_id, proyect_loan_id,screen_info_id);
		
	}
	
	public List<InfoNotification> getInfoNotificationByProspectus( Integer company_id, Integer prospectus_id, int screen_info_id ){
		
		return dao.getInfoNotificationByProspectus(company_id, prospectus_id, screen_info_id);
		
	}
	
	public boolean saveInfoNotificationBy( InfoNotification info  ){
		
		return dao.saveInfoNotificationBy(info);
		
	}
	
	public boolean updateInfoNotificationBy( InfoNotification info  ){
		
		return dao.updateInfoNotificationBy(info);
		
	}
}
