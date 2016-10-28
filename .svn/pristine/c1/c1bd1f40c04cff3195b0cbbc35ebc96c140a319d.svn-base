package mx.com.kubo.managedbeans.generales;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.ProyectService;

import org.apache.log4j.Logger;

public abstract class DocumentUploadDMO 
{
	Logger log = Logger.getLogger(getClass());

	@ManagedProperty("#{filesServiceImp}")
	protected FilesService filesService;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	protected FileTypeService fileTypeService;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectService;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService changeControlService;
	
	protected String nameFile="";
	protected String category="";

	public void setProyectService(ProyectService service) 
	{
		proyectService = service;
	}
	
	public void setFilesService(FilesService service) 
	{
		filesService = service;
	}

	public void setChangeControlService(Change_controlService service) 
	{
		changeControlService = service;
	}

	public void setFileTypeService(FileTypeService service) 
	{
		fileTypeService = service;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
