package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "gn_security_question_pool")
public class SecurityQuestionPool 
implements Serializable 
{
	private static final long serialVersionUID = -3015863918307546893L;

	@EmbeddedId
	private SecQuestPoolPK pk;
	
	@Column private String answer; //	text	NO
	
	@Column private Date date_used; //	datetime	YES	
	
	@ManyToOne @JoinColumns
	(value = {
	@JoinColumn(name = "company_id",           referencedColumnName = "company_id",           insertable = false, updatable = false),
	@JoinColumn(name = "security_question_id", referencedColumnName = "security_question_id", insertable = false, updatable = false)
	}) private SecurityQuestion pregunta;
	
	
	public SecQuestPoolPK getPk() 
	{
		return pk;
	}
	
	public void setPk(SecQuestPoolPK pk) 
	{
		this.pk = pk;
	}
	
	public String getAnswer() 
	{
		return answer;
	}
	
	public void setAnswer(String answer) 
	{
		this.answer = answer;
	}
	
	public Date getDate_used() 
	{
		return date_used;
	}
	
	public void setDate_used(Date date_used) 
	{
		this.date_used = date_used;
	}

	public final SecurityQuestion getPregunta() 
	{
		return pregunta;
	}

	public final void setPregunta(SecurityQuestion pregunta) 
	{
		this.pregunta = pregunta;
	}
}
