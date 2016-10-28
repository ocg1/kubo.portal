package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReportLogPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	Integer report_log_id;
	@Column
	Integer company_id;
	
	public ReportLogPK(){
	}
	
	public ReportLogPK(Integer report_log_id, Integer company_id){
		this.report_log_id = report_log_id;
		this.company_id = company_id;
	}

	public Integer getReport_log_id() {
		return report_log_id;
	}

	public void setReport_log_id(Integer report_log_id) {
		this.report_log_id = report_log_id;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
}
