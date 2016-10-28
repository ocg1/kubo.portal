package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.repositories.FilesDao;
import mx.com.kubo.services.FilesService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilesServiceImp 
implements FilesService 
{
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private FilesDao dao;
	
	public Files getFilebyID(FilesPK filessPk) {		
		return dao.getFilebyID(filessPk);
	}

	public boolean addFile(Files newFile, int prospectusID, int companyID) {		
		return dao.addFile(newFile, prospectusID, companyID);
	}
	
	public boolean updateFile(Files file) { 
		return dao.updateFile(file);
	}
	
	public Files getFileByTypeID(int prospectusID, int companyID, int proyectLoanID, int typeFileID) {		
		return dao.getFileByTypeID(prospectusID, companyID, proyectLoanID, typeFileID);
	}
	
	public List<Files> getListFiles() {		
		return dao.getListFiles();
	}
	
	public List<Files> getListaContratos(int prospectusID, int companyID) 
	{
		return dao.getListaContratos(prospectusID, companyID);
	}
	
	public List<Files> getListFilesByProspect(int prospectusID, int companyID, int proyectLoanID) {
		return dao.getListFilesByProspect(prospectusID, companyID, proyectLoanID);
	}
		
	public List<Files> getListFilesByProspectOrderByCategory(int prospectusID, int companyID, int proyectLoanID){
		return dao.getListFilesByProspectOrderByCategory(prospectusID, companyID, proyectLoanID);
	}

	public boolean removeFile(FilesPK filesPk) {
		return dao.removeFile(filesPk);
	}

	public boolean removeFileByProyectLoanID(int prospectusID, int companyID, int proyectLoanID, int typeFileID) 
	{
		return dao.removeFileByProyectLoanID(prospectusID, companyID, proyectLoanID, typeFileID);
	}
}
