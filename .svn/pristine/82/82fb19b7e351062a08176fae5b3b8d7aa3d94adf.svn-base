package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FileTypePK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private int file_type_id;
	@Column
	private int company_id;
	
	public FileTypePK(){
		
	}
	public FileTypePK(int  file_type_id,int company_id ){
		this.file_type_id=file_type_id;
		this.company_id=company_id;
		
	}

	public int getFile_type_id() {
		return file_type_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setFile_type_id(int file_type_id) {
		this.file_type_id = file_type_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	
}
