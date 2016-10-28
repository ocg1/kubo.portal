package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_occupation")
public class Occupation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OccupationPK occupationPK;
	@Column
	private String name;
	
	public Occupation(){
		
	}
	
	public OccupationPK getOccupationPK() {
		return occupationPK;
	}
	public void setOccupationPK(OccupationPK occupationPK) {
		this.occupationPK = occupationPK;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}