package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_proyect_question")
public class ProyectQuestion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProyectQuestionPK proyectQuestionPK;
	@Column
	private String question;
	@Column
	private String open;
	@Column
	private String mandatory;
	
	public ProyectQuestion(){
		
	}

	public ProyectQuestionPK getProyectQuestionPK() {
		return proyectQuestionPK;
	}
	public void setProyectQuestionPK(ProyectQuestionPK proyectQuestionPK) {
		this.proyectQuestionPK = proyectQuestionPK;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getMandatory() {
		return mandatory;
	}
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
}
