package mx.com.kubo.model.catalogos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UnusualBehaviorPK 
implements Serializable
{
	private static final long serialVersionUID = 972813942514402728L;

	@Column private int unusual_behavior_id;
	@Column private int company_id;
	
	public int getUnusual_behavior_id() 
	{
		return unusual_behavior_id;
	}

	public void setUnusual_behavior_id(int unusual_behavior_id) 
	{
		this.unusual_behavior_id = unusual_behavior_id;
	}
	
	public int getCompany_id() 
	{
		return company_id;
	}
	
	public void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}
}
