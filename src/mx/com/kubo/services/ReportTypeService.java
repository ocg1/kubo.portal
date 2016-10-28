package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.ReportType;

public interface ReportTypeService {
	
	abstract List<ReportType> getReportTypeList();
}
