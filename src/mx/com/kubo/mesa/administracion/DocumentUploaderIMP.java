package mx.com.kubo.mesa.administracion;

import mx.com.kubo.tools.Utilities;

public class DocumentUploaderIMP extends DocumentUploaderAMO 
implements DocumentUploaderIMO
{	
	public void init() 
	{
		Utilities.createDirectory(realPath + pathDocument);
		Utilities.createDirectory(realPath + pathHistoric);
		
		copy_file();			
	}
}
