package mx.com.kubo.test;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import mx.com.kubo.model.Reca;

public abstract class TestDashboardAMO extends TestDashboardDMO
{
	protected void init_lista_RECA() 
	{
		date_format = new SimpleDateFormat("dd/MM/yyyy");
		
		recaitems = service_reca.getRecaList();				
		
		for(Reca rec : recaitems)
		{			
			if(rec.getValid_to() == null || (rec.getStatus() == 1) )
			{
				rec.setReca_number( rec.getReca_number() +" VIGENTE" );
				reca_id = rec.getPk().getReca_id();
				
			} else {
				
				rec.setReca_number( rec.getReca_number() +" DEL " + date_format.format( rec.getValid_from() ) + " AL "+date_format.format( rec.getValid_to() ) );				
			}			
		}
	}
	
	protected void init_document_loader() 
	{
		loader = new DocumentLoaderIMP();
		loader.setService_change_control(service_change_control);
		loader.setService_files         (service_files);
		loader.setService_file_type     (service_file_type);
		loader.setService_proyect       (service_proyect);
		loader.setService_proyect_loan(  service_proyect_loan);
		loader.setSesion(sesion);
		loader.setReal_PATH(real_PATH);
		loader.setReca_id(reca_id);
	}

	protected void init_safi_credit()
	{
		endIndex = file_name.length() - 4; 
		
		beginIndex = endIndex - 9;
		
		safi_credit_id = file_name.substring(beginIndex, endIndex);				
	}
	
	protected void init_proyect_loan() 
	{		
		proyect_loan = service_proyect_loan.getProyectLoanListBySafiCreditID(safi_credit_id);
		
		if(proyect_loan != null)
		{
			loader.setProyect_loan(proyect_loan);			
		}
	}

	protected void init_contrato() 
	{							
		try 
		{
			contrato = new File(folder_path + "/"  + file_name);
			
			input_stream = new FileInputStream(contrato);
			
			loader.setInputStream(input_stream);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
