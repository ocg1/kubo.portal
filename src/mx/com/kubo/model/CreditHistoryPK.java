package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CreditHistoryPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer prospectus_id;//	INT UNSIGNED
	private Integer company_id;//	TINYINT UNSIGNED
	
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
}
