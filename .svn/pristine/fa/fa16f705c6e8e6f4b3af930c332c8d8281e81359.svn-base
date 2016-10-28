package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_study_level")
public class Study_Level implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Study_LevelPK study_levelPK;
	@Column
	private String name;
	
	public Study_Level(){
		
	}
	
	public Study_LevelPK getStudy_levelPK() {
		return study_levelPK;
	}
	public void setStudy_levelPK(Study_LevelPK study_levelPK) {
		this.study_levelPK = study_levelPK;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}