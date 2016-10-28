package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FileCategoryPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private int file_category_id;
	@Column
	private int company_id;
	
	public FileCategoryPK(){
		
	}
	public FileCategoryPK(int file_category_id,int company_id){
		this.file_category_id=file_category_id;
		this.company_id=company_id;
	}

	public int getFile_category_id() {
		return file_category_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setFile_category_id(int file_category_id) {
		this.file_category_id = file_category_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	

}
