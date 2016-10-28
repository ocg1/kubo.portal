package mx.com.kubo.tools;

import java.util.Calendar;

public class GeneradorCodigos 
{
	public static String get_activation_code(int prospectus_id, int company_id)
	{
		String active = "";
		String temp   = "";
		
		for(int i = (prospectus_id + "").length(); i < 6; i++)
		{
			temp += "0";
		}
		
		active += temp+prospectus_id + "";
		temp = "";
		
		for(int i = (company_id + "").length(); i < 3; i++)
		{
			temp += "0";
		}
		
		active += temp + company_id + "";
		temp = "";
		
		Calendar cal2 = Calendar.getInstance();
		String time =(((cal2.getTimeInMillis()) / 1000) + "");
		
		for(int i = (time + "").length(); i < 3; i++)
		{
			temp += "0";
		}
		
		active += temp + time + "";
		temp = "";
		
		System.out.println("cadena: "+active);
		
		int dig = digitoVerificador(active);
		System.out.println("digito: " + dig);
		
		active += "" + dig;
		
		System.out.println("cadena completa: " + active);
		
		return active;
	}
	
	public static String get_tracking_id(int company_id, int prospectus_id)
	{
		String tracking = "" + prospectus_id;
		
		while((tracking).length() < 5)
		{
			tracking = "0"+tracking;
		}
		
		tracking = company_id + tracking;
		
		tracking = tracking + digitoVerificador(tracking);
		
		return tracking;
	}
	
	public static int digitoVerificador(String cadena)
	{
		int digitoVer = 0;
		int i= 0;
		int j= cadena.length();

		while( i < cadena.length()) 
		{
			 digitoVer =  digitoVer +
			(Integer.parseInt(cadena.charAt(i)+"") * j);
			
			    j = j - 1;
			    i = i + 1;
		    
		}

		int mod = digitoVer % (cadena.length());

		if (mod == 0)
		{
		     digitoVer = 1;
		     
		} else {
			
		    if (mod == 1)
		    { 
		        digitoVer = 0;
		        
		    } else {
		    	
		    	if(mod >9)
		    	{
		    		digitoVer = (cadena.length()) - mod;
		    		
		    	} else {
		    		
		    		digitoVer = mod;
		    	}
		    }
		}
		
		return digitoVer;
	}
}
