package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EconomicActivityPK 
implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column int economic_activity_id;
	@Column int company_id;
	
	public EconomicActivityPK()
	{
		super();
	}
	
	public EconomicActivityPK(int economic_activity_id, int company_id)
	{
		this.economic_activity_id = economic_activity_id;
		this.company_id = company_id;
	}

	public int getEconomic_activity_id() {
		return economic_activity_id;
	}
	
	public void setEconomic_activity_id(int economic_activity_id) {
		this.economic_activity_id = economic_activity_id;
	}
	
	public int getCompany_id() {
		return company_id;
	}
	
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
