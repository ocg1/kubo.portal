package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProyectPK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private int proyect_id;
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	
	public ProyectPK(){
		
	}
	public ProyectPK(int prospectus_id,int company_id){
		super();
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
	}
	
	public ProyectPK(int proyect_id,int prospectus_id,int company_id){
		super();
		this.proyect_id=proyect_id;
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
	}

	public int getProyect_id() {
		return proyect_id;
	}

	public void setProyect_id(int proyect_id) {
		this.proyect_id = proyect_id;
	}

	public int getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}	

}
