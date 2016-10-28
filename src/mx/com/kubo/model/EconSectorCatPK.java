package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EconSectorCatPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column
	private int econ_sector_id;
	@Column
	private int company_id;
	
	public EconSectorCatPK(){
		
	}
	public EconSectorCatPK(int econ_sector_id,int company_id){
		this.econ_sector_id=econ_sector_id;
		this.company_id=company_id;
		
	}
	public int getEcon_sector_id() {
		return econ_sector_id;
	}
	public void setEcon_sector_id(int econ_sector_id) {
		this.econ_sector_id = econ_sector_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
