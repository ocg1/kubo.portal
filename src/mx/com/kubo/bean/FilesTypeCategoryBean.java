package mx.com.kubo.bean;

import java.util.List;


public class FilesTypeCategoryBean {
	private String nameCategory;
	private List<DocumentationDMO> listFiles;
	
	public FilesTypeCategoryBean(){
		
	}
	public FilesTypeCategoryBean(String nameCategory,List<DocumentationDMO> listFiles){
		this.nameCategory=nameCategory;
		this.listFiles=listFiles;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public List<DocumentationDMO> getListFiles() {
		return listFiles;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public void setListFiles(List<DocumentationDMO> listFiles) {
		this.listFiles = listFiles;
	}
	
	
	
}
