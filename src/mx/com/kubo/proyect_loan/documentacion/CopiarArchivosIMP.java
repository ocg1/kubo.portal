package mx.com.kubo.proyect_loan.documentacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.faces.context.FacesContext;

import mx.com.kubo.model.Files;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.tools.Utilities;

import org.springframework.stereotype.Service;

@Service(value = "copiar_archivos_service")
public final class CopiarArchivosIMP 
{
	private String category;
	private String pathDocument;
	private String pathHistoric;
	private String realPath;
	private String file_location;
	private String file_format;
	private String file_type;
	private String file_name;
	private String file_complete_name;
	
	private int company_id;
	private int prospectus_id;
	private int proyect_loan_id;
	private int proyect_id;
	
	public final String copyFile(Files file, ProyectLoan new_proyect_loan)
	{
		System.out.println("creando documento");
		
		company_id    = file.getFilesPk().getCompany_id();
		prospectus_id = file.getFilesPk().getProspectus_id();
		category      = file.getFileType().getFileCategory().getAbreviation();
		
		pathDocument = "/documents/cia_" + company_id + "/pros_" + prospectus_id + "/" + category +"/";				
		pathHistoric = "/historic/cia_"  + company_id + "/pros_" + prospectus_id + "/" + category + "/";		
				
		realPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//");
		
		file_format = file.getLocation().split("\\.")[1];
		file_type   = file.getFileType().getAbreviation();
		
		proyect_loan_id = new_proyect_loan.getProyectloanPk().getProyect_loan_id();
		proyect_id      = new_proyect_loan.getProyectloanPk().getProyect_id();
		
		file_name = file_type + "_" + proyect_loan_id + "_" + prospectus_id + "_" + proyect_id;
		
		file_complete_name = file_name + "_" + Utilities.getRandomName() + "." + file_format;						
		
		Utilities.deleteFileDirByEqualName(new File(realPath + pathDocument), file_name);
		Utilities.deleteFileDirByEqualName(new File(realPath + pathHistoric), file_name);
		
		Utilities.createDirectory(realPath + pathDocument);
		Utilities.createDirectory(realPath + pathHistoric);
		
		try
		{
			file_location = file.getLocation();
			
			File f = new File (realPath + file_location);
			System.out.println("documento original: " + realPath + file_location);
			
			if(f.exists())
			{				
				InputStream inStr  = new FileInputStream(realPath + file_location);
				InputStream inStr2 = new FileInputStream(realPath + file_location);
				
				System.out.println("InputStream: " + inStr );
				
				Utilities.copyFile(realPath + pathDocument + file_complete_name, inStr);
				Utilities.copyFile(realPath + pathHistoric + file_complete_name, inStr2);
				
				System.out.println("documento creado: " + realPath + pathHistoric + file_complete_name);
				
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			
			return null;
		}
				
		return pathDocument + file_complete_name;			
	}
}
