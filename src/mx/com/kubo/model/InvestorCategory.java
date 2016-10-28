package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "ln_investor_category")
public class InvestorCategory implements Serializable 
{
	private static final long serialVersionUID = 6772368071943860924L;

	@EmbeddedId private InvestorCategoryPK pk;
	
	@Column private String name;
	@Column private String is_enabled;
	
	public InvestorCategoryPK getPk() {
		return pk;
	}
	
	public void setPk(InvestorCategoryPK pk) {
		this.pk = pk;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIs_enabled() {
		return is_enabled;
	}
	
	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}
}
