package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NeighborhoodCatPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column
	private int neighborhood_id;
	@Column
	private int company_id;
	
	public NeighborhoodCatPK() {
		
	}
	public NeighborhoodCatPK(int neighborhood_id,int company_id ) {
		this.neighborhood_id=neighborhood_id;
		this.company_id=company_id;
	}
	public int getNeighborhood_id() {
		return neighborhood_id;
	}
	public void setNeighborhood_id(int neighborhood_id) {
		this.neighborhood_id = neighborhood_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	

}
