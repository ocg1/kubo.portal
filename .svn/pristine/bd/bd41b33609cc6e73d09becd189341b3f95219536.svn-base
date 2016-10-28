package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_operating_cost_type")
public class Operating_cost_type implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private Operating_cost_typePK operatingCostTypePk;
	@Column
	private String name;
	@Column
	private String hs_name;
	
	public Operating_cost_type(){
		
	}

	public Operating_cost_typePK getOperatingCostTypePk() {
		return operatingCostTypePk;
	}

	public void setOperatingCostTypePk(Operating_cost_typePK operatingCostTypePk) {
		this.operatingCostTypePk = operatingCostTypePk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHs_name() {
		return hs_name;
	}

	public void setHs_name(String hs_name) {
		this.hs_name = hs_name;
	}

	
}
