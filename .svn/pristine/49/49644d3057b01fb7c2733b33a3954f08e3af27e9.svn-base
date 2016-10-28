package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_economic_activity")
public class Economic_Activity 
implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId 
	private EconomicActivityPK economicActivityPK;
	
	@Column private String description;
	
	@Column private Integer is_enabled;
	
	@Column private Integer sequency_field;
	
	public Economic_Activity()
	{
		super();
	}
	
	public EconomicActivityPK getEconomicActivityPK() {
		return economicActivityPK;
	}
	
	public void setEconomicActivityPK(EconomicActivityPK economicActivityPK) {
		this.economicActivityPK = economicActivityPK;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(Integer is_enabled) {
		this.is_enabled = is_enabled;
	}

	public Integer getSequency_field() {
		return sequency_field;
	}

	public void setSequency_field(Integer sequency_field) {
		this.sequency_field = sequency_field;
	}
	
	
}