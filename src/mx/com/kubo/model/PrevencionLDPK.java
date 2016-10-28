package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrevencionLDPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private Integer prospectus_id;//	INT UNSIGNED
	@Column
	private Integer company_id;//	TINYINT UNSIGNED
	
	
	public PrevencionLDPK(){
		
	}
	public PrevencionLDPK(int prospectus_id,int company_id){
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
	}
	
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
