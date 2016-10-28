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

@Entity
@Table(name="ln_public_forum")
public class PublicForum implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "proyect_question_id", referencedColumnName = "proyect_question_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	    })
	private ProyectQuestion proyectQuestion; 
	
	@EmbeddedId
	private PublicForumPK publicForumPK;
	@Column
	private Integer proyect_question_id;
	@Column
	private String free_question;
	@Column
	private String answer;
	@Column
	private Date question_date;
	@Column
	private Date response_date;
	@Column
	private Integer prospectus_id;
	
	public PublicForum(){
		
	}

	public PublicForumPK getPublicForumPK() {
		return publicForumPK;
	}
	public void setPublicForumPK(PublicForumPK publicForumPK) {
		this.publicForumPK = publicForumPK;
	}
	public String getFree_question() {
		return free_question;
	}
	public void setFree_question(String free_question) {
		this.free_question = free_question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getQuestion_date() {
		return question_date;
	}
	public void setQuestion_date(Date question_date) {
		this.question_date = question_date;
	}
	public Date getResponse_date() {
		return response_date;
	}
	public void setResponse_date(Date response_date) {
		this.response_date = response_date;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Integer getProyect_question_id() {
		return proyect_question_id;
	}
	public void setProyect_question_id(Integer proyect_question_id) {
		this.proyect_question_id = proyect_question_id;
	}
	public ProyectQuestion getProyectQuestion() {
		return proyectQuestion;
	}
	public void setProyectQuestion(ProyectQuestion proyectQuestion) {
		this.proyectQuestion = proyectQuestion;
	}
}