package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_report_log")
public class ReportLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private ReportLogPK reportLogPK;
	@Column
	Integer report_type_id;
	@Column
	Integer proyect_loan_id;
	@Column
	Integer proyect_id;
	@Column
	Integer prospectus_id;
	@Column
	String safi_credit_id;
	@Column
	Integer create_prospectus_id;
	@Column
	char status;
	@Column
	Date creation_date;
	
	public ReportLog(){
		
	}
	
	
	public ReportLog(ReportLogPK reportLogPK, Integer report_type_id,
			Integer proyect_loan_id, Integer proyect_id, Integer prospectus_id,
			String safi_credit_id, Integer create_prospectus_id, char status,
			Date creation_date) {
		super();
		this.reportLogPK = reportLogPK;
		this.report_type_id = report_type_id;
		this.proyect_loan_id = proyect_loan_id;
		this.proyect_id = proyect_id;
		this.prospectus_id = prospectus_id;
		this.safi_credit_id = safi_credit_id;
		this.create_prospectus_id = create_prospectus_id;
		this.status = status;
		this.creation_date = creation_date;
	}


	public Integer getReport_type_id() {
		return report_type_id;
	}
	public void setReport_type_id(Integer report_type_id) {
		this.report_type_id = report_type_id;
	}
	public ReportLogPK getReportLogPK() {
		return reportLogPK;
	}
	public void setReportLogPK(ReportLogPK reportLogPK) {
		this.reportLogPK = reportLogPK;
	}
	public Integer getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(Integer proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	public Integer getProyect_id() {
		return proyect_id;
	}
	public void setProyect_id(Integer proyect_id) {
		this.proyect_id = proyect_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(String safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public Integer getCreate_prospectus_id() {
		return create_prospectus_id;
	}
	public void setCreate_prospectus_id(Integer create_prospectus_id) {
		this.create_prospectus_id = create_prospectus_id;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	
}
