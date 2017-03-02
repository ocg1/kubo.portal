package mx.com.kubo.managedbeans.mesa.administracion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import mx.com.kubo.model.BlockedPerson;

public abstract class BlockedPersonAMO extends BlockedPersonDMO
{	
	protected void parse_blocked_person_ROW() 
	{
		try
		{					
			String mx_oficio_CNBV   = blocked_person_ROW[0];
			String mx_expediente    = blocked_person_ROW[1];
			String blocked_date_STR = blocked_person_ROW[2];
			String mx_oficio_UIF    = blocked_person_ROW[3];
			String full_name        = blocked_person_ROW[4];
			String mx_rfc = null;
			
			if(blocked_person_ROW.length > 5)
			{
				mx_rfc = blocked_person_ROW[5];
			}			
			
			blocked_date = format.parse(blocked_date_STR);
			
			person = new BlockedPerson();
			person.setBlocked_date(blocked_date);
			person.setFull_name(full_name);
			person.setMx_expediente(mx_expediente);
			person.setMx_oficio(mx_oficio_CNBV);
			person.setMx_rfc(mx_rfc);
			
			b_error = false;
		
		} catch(Exception e) {								
			
			sb_log.append("X X X X X   F O R M A T  E R R O R   X X X X X").append("\n");
						
			b_error = true;
		}
	}
	
	protected void save_blocked_person()
	{										
		if( !b_error )
		{						
			boolean save_OK = service_blocked_person.save(person);
			
			if (save_OK)
			{					
				sb_log.append("OK").append("\n");
				
				counter_insert++;
				
			} else {
				
				sb_log.append("ERROR").append("\n");			
			}			
		}
	}

	protected void init_blocked_person_LOG()
	{		
		try
		{							        		
			file_LOG = new File(path_file_LOG);	        
	        
			fw = new FileWriter(file_LOG);
            bw = new BufferedWriter(fw);
            
            bw.write( sb_log.toString() );	       
	        bw.close();
	        
		    int index = path_file_LOG.indexOf("/Kubo/resources/");
		    
		    path_file_LOG = path_file_LOG.substring(index);		        	        
			
		} catch(Exception e) {
		
			e.printStackTrace();
			
			path_file_LOG = "X X X X X   LOG  E R R O R   X X X X X";
		}
	}
}
