package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="gn_file_type")
public class FileType 
implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FileTypePK fileTypePk;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "file_category_id", referencedColumnName = "file_category_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",       referencedColumnName = "company_id",       insertable = false, updatable = false)
	    })
	private FileCategory fileCategory;
	
	@Column
	private String name;
	
	@Column
	private int file_category_id;
	
	@Column
	private String required;
	
	@Column
	private String uploaded_text;
	
	@Column
	private String abreviation;
	
	public FileType(){
		
	}

	public FileTypePK getFileTypePk() {
		return fileTypePk;
	}

	public String getName() {
		return name;
	}

	public int getFile_category_id() {
		return file_category_id;
	}

	public String getRequired() {
		return required;
	}

	public void setFileTypePk(FileTypePK fileTypePk) {
		this.fileTypePk = fileTypePk;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFile_category_id(int file_category_id) {
		this.file_category_id = file_category_id;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public FileCategory getFileCategory() {
		return fileCategory;
	}

	public void setFileCategory(FileCategory fileCategory) {
		this.fileCategory = fileCategory;
	}

	public String getUploaded_text() {
		return uploaded_text;
	}

	public void setUploaded_text(String uploaded_text) {
		this.uploaded_text = uploaded_text;
	}

	public String getAbreviation() {
		return abreviation;
	}
	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}	
	
	public static Comparator<FileType> fileTypeIdComp = new Comparator<FileType>() {

		public int compare(FileType s1, FileType s2) {

		   int rollno1 = s1.getFileTypePk().getFile_type_id();
		   int rollno2 = s2.getFileTypePk().getFile_type_id();

		   return rollno1 - rollno2;

	   }};
	
}
