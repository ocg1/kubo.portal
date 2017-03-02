package mx.com.kubo.mesa.administracion;

import java.io.File;
import java.io.FileOutputStream;

import mx.com.kubo.tools.Utilities;

public abstract class DocumentUploaderAMO extends DocumentUploaderDMO 
{	
	protected void copy_file() 
	{
		try
		{		
			in = file_uploaded.getInputstream();		
			
			file_uploaded_path = realPath + pathDocument + nameFile;
			
			File file = new File(file_uploaded_path);
			
			out = new FileOutputStream(file);
            
            int read = 0;
            byte[] bytes = new byte[1024];
         
            while ((read = in.read(bytes)) != -1) 
            {
                out.write(bytes, 0, read);
            }
         
            out.flush(); 
            out.close();
            
            String fileNameHist = realPath + pathHistoric + nameFile;
            
            Utilities.copyFile(fileNameHist, in);
            
            in.close();
            
		} catch(Exception e) {
			
			e.printStackTrace();
			
			file_uploaded_path = null;
		}
	}
}
