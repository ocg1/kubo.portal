package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.Alert;
import mx.com.kubo.repositories.AlertDao;
import mx.com.kubo.services.AlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImp implements Serializable, AlertService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AlertDao alertdao;
	
	public List<Alert> getAlertListSelectable(){
		return alertdao.getAlertListSelectable();
	}
	
}
