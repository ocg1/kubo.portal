package mx.com.kubo.services.impl;

import mx.com.kubo.model.ReportLog;
import mx.com.kubo.repositories.ReportLogDao;
import mx.com.kubo.services.ReportLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportLogServiceImp implements ReportLogService{

	
	@Autowired	
	ReportLogDao reportLogDao;
	
	@Override
	public boolean add(ReportLog reportLog) {
		return reportLogDao.add(reportLog);
	}
	
}
