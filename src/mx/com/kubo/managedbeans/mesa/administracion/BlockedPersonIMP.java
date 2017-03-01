package mx.com.kubo.managedbeans.mesa.administracion;

import java.io.BufferedReader;
import java.io.FileReader;

public class BlockedPersonIMP extends BlockedPersonAMO implements BlockedPersonIMO 
{
	public void init()
	{
		if(file_uploaded!= null)
		{
			init_blocked_person();	    			
			init_blocked_person_LOG();		    	    		    		    		   
		}
	}

	private void init_blocked_person() 
	{
	    try 
	    {	    		    	
	    	fr = new FileReader (file_uploaded);
	    	br = new BufferedReader(fr);	    		    			    		    	
	    	
	    	counter_row = counter_insert = 0;	    		    		    		    	
	    	
		    while((row_DATA = br.readLine()) != null)
		    {		        	 		    			    			    	
		    	blocked_person_ROW = row_DATA.split(SEPARADOR, 11);		    			    	
		    	
		    	sb_log.append(counter_row + " > " + row_DATA + " > ");
		    	
		    	if(blocked_person_ROW.length > 4) 
		    	{		    				    				    
		    		parse_blocked_person_ROW();		    	
		    		
		    		save_blocked_person();	
		    	}		    			 		    			    	
		    	
		    	counter_row++;
		    }		    	    	
	    	
		    sb_log.append("-----------------------------------------------\n");
		    sb_log.append("> insert into <gn_blocked_person> = " + counter_insert);
		    sb_log.append("\n-----------------------------------------------");
	    	
	    } catch(Exception e) {
	    	
	    	e.printStackTrace();	
	    	
	    } finally {	   
			
	    	try
	    	{
	    		if( null != fr )
	    		{
					 fr.close();	
					 br.close();
				}	
	    		
			 } catch (Exception e) {
				 
				 e.printStackTrace();				 
			 }
		}	
	} 
}
