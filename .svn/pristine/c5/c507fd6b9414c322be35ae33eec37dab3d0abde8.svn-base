package mx.com.kubo.test;

import java.io.InputStream;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;

public interface DocumentLoaderIMO 
{	
	void setService_files    (FilesService    service);
	void setService_file_type(FileTypeService service);
	void setService_proyect_loan(ProyectLoanService service);
	void setService_proyect(ProyectService service);
	void setService_change_control(Change_controlService service);
	
	void setSesion(SessionBean sesion);
	void setProyect_loan(ProyectLoan proyect_loan);
	void setInputStream(InputStream input_stream);
	void setReal_PATH(String real_PATH);
	void setReca_id(Integer reca_id);
	
	void fileUpload();
	
	boolean isUpload_OK();	
}
