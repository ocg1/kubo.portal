package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.AlertMotive;
import mx.com.kubo.repositories.AlertMotiveDao;
import mx.com.kubo.services.AlertMotiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertMotiveServiceImp implements Serializable, AlertMotiveService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AlertMotiveDao alertmotivedao;
	
	
	public List<AlertMotive> getAlertMotiveListByAlert(int alert_id,int company_id){
		return alertmotivedao.getAlertMotiveListByAlert(alert_id, company_id);
	}
	
	public List<AlertMotive> getAllAlertMotiveList(){
		return alertmotivedao.getAllAlertMotiveList();
	}
	
}
