package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProyectQuestionPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProyectQuestionPK(){
		
	}
	
	public ProyectQuestionPK(int proyect_question_id, int company_id){
		this.proyect_question_id = proyect_question_id;
		this.company_id = company_id;
	}
	
	@Column
	int proyect_question_id;
	@Column
	int company_id;

	public int getProyect_question_id() {
		return proyect_question_id;
	}
	public void setProyect_question_id(int proyect_question_id) {
		this.proyect_question_id = proyect_question_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
