package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Simulation_Cache_PK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int simulation_cache_id; //	"int(10) unsigned"
	@Column
	private int company_id; //	"tinyint(3) unsigned"
	
	
	
	public Simulation_Cache_PK() {
		
	}
	public int getSimulation_cache_id() {
		return simulation_cache_id;
	}
	public void setSimulation_cache_id(int simulation_cache_id) {
		this.simulation_cache_id = simulation_cache_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

}
