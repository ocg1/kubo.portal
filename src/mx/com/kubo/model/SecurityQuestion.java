package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_security_question")
public class SecurityQuestion 
implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SecurityQuestionPK securityQuestionPK;
	
	@Column
	private String question;
	
	public SecurityQuestion()
	{
		
	}

	public SecurityQuestionPK getSecurityQuestionPK() 
	{
		return securityQuestionPK;
	}

	public void setSecurityQuestionPK(SecurityQuestionPK securityQuestionPK) 
	{
		this.securityQuestionPK = securityQuestionPK;
	}

	public String getQuestion() 
	{
		return question;
	}

	public void setQuestion(String question) 
	{
		this.question = question;
	}	
}
