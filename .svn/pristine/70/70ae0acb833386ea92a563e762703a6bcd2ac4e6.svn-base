package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FilesPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column
	private int file_id;
	@Column
	private int prospectus_id;
	@Column
	private int company_id;
	
	@Column
	private int file_type_id;
	
	@Column
	private int proyect_loan_id;
	
	public FilesPK(){
		
	}
	
	public int getProyect_loan_id() {
		return proyect_loan_id;
	}
	public Integer getFile_type_id() {
		return file_type_id;
	}

	public void setFile_type_id(Integer file_type_id) {
		this.file_type_id = file_type_id;
	}

	public void setProyect_loan_id(int proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	
	public FilesPK(int prospectus_id,int company_id, int proyectLoanID, int fileTypeID){		
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		this.proyect_loan_id = proyectLoanID;
		this.file_type_id = fileTypeID;
		
	}
	public FilesPK(int file_id,int prospectus_id,int company_id, int proyectLoanID, int fileTypeID){
		this.file_id=file_id;
		this.prospectus_id=prospectus_id;
		this.company_id=company_id;
		this.proyect_loan_id = proyectLoanID;
		this.file_type_id = fileTypeID;
	}
	public int getFile_id() {
		return file_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	

}
