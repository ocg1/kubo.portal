package mx.com.kubo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="gn_file_category")
public class FileCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FileCategoryPK fileCategoryPk;
	
	@Column
	private String name;
	
	@Column
	private String abreviation;
	
	@Column
	private Integer validity;
	
	@OneToMany(mappedBy="fileCategory")
	private List<FileType> listFileType;
	
	public FileCategory(){
		
	}

	public FileCategoryPK getFileCategoryPk() {
		return fileCategoryPk;
	}

	public String getName() {
		return name;
	}

	public void setFileCategoryPk(FileCategoryPK fileCategoryPk) {
		this.fileCategoryPk = fileCategoryPk;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FileType> getListFileType() {
		return listFileType;
	}

	public void setListFileType(List<FileType> listFileType) {
		this.listFileType = listFileType;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	public Integer getValidity() 
	{
		return validity;
	}

	public void setValidity(Integer validity) 
	{
		this.validity = validity;
	}

}
