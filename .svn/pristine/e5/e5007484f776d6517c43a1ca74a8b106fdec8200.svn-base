package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RelationShipPK implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column
	private int prospectus_origin_id;
	@Column
	private int company_id;
	@Column
	private int prospectus_destiny_id;
	
	public RelationShipPK(){
		
	}
	public RelationShipPK(int prospectus_origin_id,int company_id){
		this.prospectus_origin_id=prospectus_origin_id;
		this.company_id=company_id;
	}
	
	public RelationShipPK(int prospectus_origin_id,int company_id,int prospectus_destiny_id){
		this.prospectus_origin_id=prospectus_origin_id;
		this.company_id=company_id;
		this.prospectus_destiny_id=prospectus_destiny_id;
	}
	public int getProspectus_origin_id() {
		return prospectus_origin_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public int getProspectus_destiny_id() {
		return prospectus_destiny_id;
	}
	public void setProspectus_origin_id(int prospectus_origin_id) {
		this.prospectus_origin_id = prospectus_origin_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public void setProspectus_destiny_id(int prospectus_destiny_id) {
		this.prospectus_destiny_id = prospectus_destiny_id;
	}
}
