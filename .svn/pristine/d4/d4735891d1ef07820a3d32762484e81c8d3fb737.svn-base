package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.ReportType;
import mx.com.kubo.repositories.ReportTypeDao;
import mx.com.kubo.services.ReportTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportTypeServiceImp implements ReportTypeService{

	@Autowired
	ReportTypeDao reportTypeDao;
	
	@Override
	public List<ReportType> getReportTypeList() {
		return reportTypeDao.getReportTypeList();
	}
	
}
