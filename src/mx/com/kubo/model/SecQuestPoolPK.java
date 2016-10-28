package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SecQuestPoolPK 
implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	@Column //	"int(10) unsigned"	NO	PRI
	private int prospectus_id; 
	
	@Column //	"tinyint(3) unsigned"	NO	PRI
	private int company_id; 
	
	@Column //	"tinyint(3) unsigned"	NO	PRI
	private int security_question_id;
		
	public void setProspectus_id(int id) 
	{
		prospectus_id = id;
	}
	
	public void setCompany_id(int id) 
	{
		company_id = id;
	}
	
	public void setSecurity_question_id(int id) 
	{
		security_question_id = id;
	}
	
	public int getCompany_id() 
	{
		return company_id;
	}
	
	public int getProspectus_id() 
	{
		return prospectus_id;
	}
	
	public int getSecurity_question_id() 
	{
		return security_question_id;
	}
	
}
