package mx.com.kubo.tools;

import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.SystemParamService;

public class FormatoCadenas 
{
	private static SystemParamService systemparamservice;
	
	public static String formatoNombre(String cadena)
	{        
        if(cadena == null || cadena.length() == 0)
        {
            return cadena;      
        }
        
        cadena = reemplazaCaracteresExtra( cadena );
        
        char[] caracteres = cadena.toCharArray();
        
        caracteres[0] = Character.toUpperCase(caracteres[0]);

        for (int i = 0; i < cadena.length() - 1; i++)
        {
        	if (caracteres[i] == ' ')
        	{
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
                
        	} else {
        		
        		caracteres[i + 1] = Character.toLowerCase(caracteres[i + 1]);
        	}
        }

        return cadena = new String(caracteres) ;
        
    }
	
	public static String reemplazaCaracteresExtra(String cadena ) 
	{
		
		if( cadena != null ){
		
			SystemParamPK sysPK = new SystemParamPK();
			
			sysPK.setCompany_id(1);
			sysPK.setSystem_param_id(66);
			
			SystemParam sysparam;
			
			if(systemparamservice == null )
			{
				
				systemparamservice = Utilities.findBean("systemParamServiceImp");
				
			}
			
			sysparam = systemparamservice.loadSelectedSystemParam(sysPK);
			
			if(sysparam.getValue() != null){
				
				String[] val = sysparam.getValue().split("88");
				
				if( val.length > 0 ){
					
					for ( String pareja : val  ) {
						
						String par[] = pareja.split("22");
						
						cadena =  reemplaza( cadena, par[0] , par[1] );
						
					}
					
				}
				
			}
		
		}else{
			
			return "";
			
		}
		
		return cadena.toLowerCase();
		
	}
	
	public static String reemplaza( String cadena, String comodin , String valor_a_sustituir )
	{	
		while(cadena.indexOf(comodin) > -1)
		{
			System.out.println(cadena +"  - "+comodin +" - "+valor_a_sustituir);
		    int pointer_comodin = cadena.indexOf(comodin);
		    int pointer = 0;
		   		        	 	    		    			    	
    		String text_BEFORE = cadena.substring(pointer, pointer_comodin);
    		
    		pointer = pointer_comodin + comodin.length();
    		
    		String text_AFTER  = cadena.substring(pointer, cadena.length());
    		
    		StringBuilder sb = new StringBuilder();
    		sb.append(text_BEFORE).append(valor_a_sustituir).append(text_AFTER);
    		
    		cadena = sb.toString();	    		    			    			       
		    		    	
		}
		
		return cadena;
	}
}
