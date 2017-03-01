package mx.com.kubo.mesa.administracion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import mx.com.kubo.tools.Utilities;

public abstract class DocumentUploaderAMO extends DocumentUploaderDMO 
{	
	protected void copy_file() 
	{
		try
		{		
			InputStream in = file.getInputstream();
			
			//System.out.println( "component_id: " + component_id + " formatFile: " + formatFile );
			
			nameFile += "_" + fecha + formatFile;
			
			file_uploaded_path = realPath + pathDocument + nameFile;
			
			OutputStream out = new FileOutputStream(new File(file_uploaded_path));
            
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
