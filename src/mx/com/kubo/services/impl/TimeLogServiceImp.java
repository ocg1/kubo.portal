package mx.com.kubo.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.TimeLog;
import mx.com.kubo.repositories.TimeLogDao;
import mx.com.kubo.services.TimeLogService;

@Service
public class TimeLogServiceImp implements Serializable, TimeLogService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	TimeLogDao dao;
	
	public boolean saveTimeLog( TimeLog timelog ){
		return dao.saveTimeLog(timelog);
	}
	
}
