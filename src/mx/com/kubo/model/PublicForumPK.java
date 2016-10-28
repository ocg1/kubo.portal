package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PublicForumPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PublicForumPK(){
		
	}
	
	public PublicForumPK(int public_forum_id, int proyect_id, int company_id){
		this.public_forum_id = public_forum_id;
		this.proyect_id = proyect_id;
		this.company_id = company_id;
	}
	
	@Column
	int public_forum_id;
	@Column
	int proyect_id;
	@Column
	int company_id;
	
	public int getPublic_forum_id() {
		return public_forum_id;
	}
	public void setPublic_forum_id(int public_forum_id) {
		this.public_forum_id = public_forum_id;
	}
	public int getProyect_id() {
		return proyect_id;
	}
	public void setProyect_id(int proyect_id) {
		this.proyect_id = proyect_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
}
