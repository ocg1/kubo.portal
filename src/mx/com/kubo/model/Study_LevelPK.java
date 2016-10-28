package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Study_LevelPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Study_LevelPK(){
		
	}
	
	public Study_LevelPK(int study_level_id, int company_id){
		this.study_level_id = study_level_id;
		this.company_id = company_id;
	}
	
	@Column
	int study_level_id;
	@Column
	int company_id;

	public int getStudy_level_id() {
		return study_level_id;
	}
	public void setStudy_level_id(int study_level_id) {
		this.study_level_id = study_level_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
