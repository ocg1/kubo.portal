package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_economic_dependent")
public class Economic_Dependent_Type implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EconomicDependentPK economicdependentPK;
	@Column
	private String description;
	

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public EconomicDependentPK getEconomicdependentPK() {
		return economicdependentPK;
	}
	public void setEconomicdependentPK(EconomicDependentPK economicdependentPK) {
		this.economicdependentPK = economicdependentPK;
	}
	
	
}