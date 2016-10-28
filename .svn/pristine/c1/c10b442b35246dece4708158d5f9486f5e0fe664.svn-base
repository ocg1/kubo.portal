package mx.com.kubo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import mx.com.kubo.tools.Utilities;

public final class DocumentLoaderIMP extends DocumentLoaderAMO
implements DocumentLoaderIMO
{
	public final void fileUpload() 
	{
		try
		{					
			nameFileHist = "";
			
			upload_OK = false;
			
			init_file_type();
			
			createDirectory(real_PATH + pathDocument + category);				
			createDirectory(real_PATH + pathHistoric + category);	
			
			deleteFileDirByEqualName(new File(real_PATH + pathDocument + category), nameFile + "_" + proyect_loan_id);
		
			if(deleteHistory)
			{		
				deleteFileDirByEqualName(new File(real_PATH + pathHistoric + category), nameFile + "_" + proyect_loan_id);
				
			} else {
				
				//String thisPath = pathHistoric + category;
				
				//nameFile = getNewName(thisPath,  nameFile);
			}
			
			pathFile = category + "/";
			
			if(!deleteHistory)
			{		
				nameFileHist = getNewName(pathHistoric + pathFile,  nameFile);
				
				sb = new StringBuilder();
				sb.append("_");
				sb.append(proyect_loan_id).append("_");
				sb.append(prospectus_id).append("_");
				sb.append(proyect != null ? proyect.getProyectoPk().getProyect_id() : "").append("_");
				sb.append(Utilities.getRandomName());
				sb.append(formatFile);
												
				nameFileHist += sb.toString();
				
			} else {
				
				nameFileHist = nameFile + formatFile;
			}			
		
			sb = new StringBuilder();
			sb.append("_").append(proyect_loan_id).append("_");
			sb.append(prospectus_id).append("_");
			sb.append(proyect != null ? proyect.getProyectoPk().getProyect_id(): "").append("_");
			sb.append(Utilities.getRandomName());
			sb.append(formatFile);
			
			nameFile += sb.toString();
			
			boolean bool1 = saveFileByType(real_PATH + pathDocument + pathFile + nameFile, CONTRATO_CREDITO_FRIMADO, pathDocument + pathFile+nameFile, stream);
			
			InputStream stream2 =new FileInputStream(real_PATH + pathDocument + pathFile + nameFile);
			
			boolean bool2 = Utilities.copyFile(real_PATH + pathHistoric + pathFile + nameFileHist, stream2);
			
			if( bool1 && bool2)
			{
				upload_OK = true;
				
			} else {
				
				upload_OK = false;
			}									
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}						
	}
}
