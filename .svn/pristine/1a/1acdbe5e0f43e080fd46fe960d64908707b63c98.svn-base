package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SecurityQuestionPK 
implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	@Column
	int security_question_id;
	
	@Column
	int company_id;
	
	public SecurityQuestionPK()
	{
		
	}
	
	public SecurityQuestionPK(int security_question_id, int company_id)
	{
		this.security_question_id=security_question_id;
		this.company_id=company_id;
	}

	public int getSecurity_question_id() 
	{
		return security_question_id;
	}

	public void setSecurity_question_id(int security_question_id) 
	{
		this.security_question_id = security_question_id;
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
