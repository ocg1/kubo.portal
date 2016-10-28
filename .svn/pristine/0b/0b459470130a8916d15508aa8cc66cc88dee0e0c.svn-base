package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.FormAnalytics;
import mx.com.kubo.repositories.FormAnalyticsDao;
import mx.com.kubo.services.FormAnalyticsService;

@Service
public class FormAnalyticsServiceImp implements Serializable, FormAnalyticsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private FormAnalyticsDao dao;
	
	public boolean addFormAnalytics( FormAnalytics analytic ){
		return dao.addFormAnalytics(analytic);
	}
	
}
