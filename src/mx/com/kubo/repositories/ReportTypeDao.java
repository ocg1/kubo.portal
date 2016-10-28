package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ReportType;

public interface ReportTypeDao {
	abstract List<ReportType> getReportTypeList(); 
}
