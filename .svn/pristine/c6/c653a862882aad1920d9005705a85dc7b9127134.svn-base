package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_marital_status")
public class Marital_Status implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Marital_StatusPK maritalStatusPK;
	@Column
	private String description;
	
	public Marital_Status(){
		
	}
	
	public Marital_StatusPK getMaritalStatusPK() {
		return maritalStatusPK;
	}
	public void setMaritalStatusPK(Marital_StatusPK maritalStatusPK) {
		this.maritalStatusPK = maritalStatusPK;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}