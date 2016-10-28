package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TownCatPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column
	private int town_id;
	@Column
	private int company_id;
	
	public TownCatPK(){
		
	}
	
	public TownCatPK(int town_id,int company_id ){
		this.town_id=town_id;
		this.company_id=company_id;
		
	}

	public int getTown_id() {
		return town_id;
	}

	public void setTown_id(int town_id) {
		this.town_id = town_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

}
