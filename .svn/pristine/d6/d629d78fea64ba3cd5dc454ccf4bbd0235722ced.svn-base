package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class CreditHistoryAttemptPK 
implements Serializable
{
	private static final long serialVersionUID = 841392524996846336L;
	
	@Column int attempt_id;
	@Column int company_id;
	
	public CreditHistoryAttemptPK(int attempt_id, int company_id)
	{
		this.attempt_id = attempt_id;
		this.company_id = company_id;
	}
	
	public CreditHistoryAttemptPK()
	{
		super();
	}
	
	public int getAttempt_id() 
	{
		return attempt_id;
	}
	
	public void setAttempt_id(int attempt_id) 
	{
		this.attempt_id = attempt_id;
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
