package mx.com.kubo.mesa.administracion;

import org.primefaces.model.UploadedFile;

import mx.com.kubo.managedbeans.SessionBean;

public interface DocumentUploaderIMO 
{
	void setSesion(SessionBean sesion);
	
	void setFile(UploadedFile file);
	
	void setRealPath(String realPath);	
	
	void init();
	
	String getFile_uploaded_path();
}
