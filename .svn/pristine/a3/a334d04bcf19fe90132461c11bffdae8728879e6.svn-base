package mx.com.kubo.services;
import java.util.List;

import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;

public interface FilesService 
{	
	Files getFilebyID(FilesPK filessPk);
	Files getFileByTypeID(int prospectusID,int companyID, int proyectLoanID, int typeFileID);
	
	List<Files> getListFiles();
	List<Files> getListFilesByProspect(int prospectusID,int companyID, int proyectLoanID);
	List<Files> getListFilesByProspectOrderByCategory(int prospectusID, int companyID, int proyectLoanID);
	
	List<Files> getListaContratos(int prospectusID, int companyID);
	
	boolean addFile(Files newFile,int prospectusID,int companyID);
	boolean updateFile(Files file);
	boolean removeFile(FilesPK filesPk);
	boolean removeFileByProyectLoanID(int prospectusID, int companyID, int proyectLoanID, int typeFileID);

}
