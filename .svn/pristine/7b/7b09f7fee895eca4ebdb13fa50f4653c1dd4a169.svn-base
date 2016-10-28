package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReportTypePK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private int report_type_id;
	@Column
	private int company_id;
	
	public ReportTypePK(){
		
	}
	
	public ReportTypePK(int report_type_id, int company_id) {
		super();
		this.report_type_id = report_type_id;
		this.company_id = company_id;
	}

	public int getReport_type_id() {
		return report_type_id;
	}

	public void setReport_type_id(int report_type_id) {
		this.report_type_id = report_type_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
}
