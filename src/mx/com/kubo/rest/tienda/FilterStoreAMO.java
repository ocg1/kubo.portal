package mx.com.kubo.rest.tienda;

public abstract class FilterStoreAMO extends FilterStoreDMO 
{
	protected void init_filters() 
	{
		filtros = ultimoFiltro.split("\\|\\|");
		
    	termCad = filtros[0] ;    	    	
    	riskCad = filtros[1];    	    	    	
    	genderCadTemp = filtros[2];    	    	    	    	
    	fondPrev = filtros[3];    	    	     	
    	filter_ammount = filtros[4];
    	filter_loan_type = filtros[5];
    	filter_loan_type = filtros[6];
    	
    	termCad = termCad.split("_")[0];    	
    	termCad = termCad.split("T")[1];
    	typeSearch = Integer.parseInt( fondPrev.substring( ("typeSearch:").length() ) );
    	riskCad = riskCad.substring( ("risk:").length() );
    	filter_ammount = filter_ammount.substring( ("between:").length() );    	    	    	    		    	
		previousType   = filter_loan_type.substring( ("previousType:").length() );				    	
		destiny_values = filter_loan_type.substring( ("destinyValues:").length() );				
	}
	
	protected void init_plazo() 
	{
    	Integer intTmp = 0;
    	
    	boolean term_ENABLED = !termCad.equals("12") && !termCad.equals("24") && !termCad.equals("36");
    	
    	if(term_ENABLED)
    	{    		
    		intTmp = Integer.parseInt( termCad );
    		
    		if ( intTmp > 12 &&  intTmp < 24 )
    		{    			
    			intTmp = ( 365 ) + ( 30 * (intTmp-12) );    			
    		}
    		
    		else if ( intTmp > 24 &&  intTmp < 36 ) 
    		{    			
    			intTmp = ( 365*2 ) + ( 30 * (intTmp-24) );
    			
    		} else {
    			
    			intTmp = intTmp * 30;
    		}
    		
    	} else {
    		
    		if(termCad.equals("12"))
    		{
    			intTmp = 365;
    		} 
    		
    		else if(termCad.equals("24"))
    		{
    			intTmp = 365 * 2;
    		}
    		
    		else if(termCad.equals("36"))
    		{
    			intTmp = 365 * 5;
    		}
    	}
    	
    	diasPorTrans = intTmp +"";
	}
	
	protected void init_tipo_fondeo() 
	{
    	switch(typeSearch)
    	{
    		case TODOS:
    			tipo_fondeo = "T";
    		break;
    		
    		case FONDEADOS_PREVIAMENTE:
    			tipo_fondeo = "C";
    		break;
    		
    		case SIN_FONDEO_PREVIO:
    			tipo_fondeo = "S";
    		break;
    	}
	}
	
	protected void init_gender() 
	{
    	genderCadTemp = genderCadTemp.substring( ("gender:").length() );
    	
    	if(genderCadTemp != null && genderCadTemp.trim().length() > 0)
    	{    		
    		if(genderCadTemp.indexOf("H") != (-1) )
    		{
    			genderCad = "1";
    			flagGender = true;
    		}
    		
    		if(genderCadTemp.indexOf("M") != (-1) )
    		{
    			if(flagGender)
    			{
    				genderCad += ",";
    			}
    			
    			genderCad += "2";    			
    		}    		
    	}
	}
	
	protected void init_ammount() 
	{
    	if(filter_ammount.trim().length() > 0)
    	{    		
    		String ammountCadFrom = "";
	    	String ammountCadTo = "";
    		
    		if((filter_ammount.split("_")).length == 2)
    		{    	
    			ammountCadFrom = filter_ammount.split("_")[0];
    			ammountCadTo   = filter_ammount.split("_")[1];
    	
    		} else if ( (filter_ammount.split("_")).length == 1 ) {
    			
    			 ammountCadFrom = filter_ammount.split("_")[0];
    		}
    	
	    	if(ammountCadTo !=null && ammountCadFrom!= null && (!ammountCadFrom.equals("") || !ammountCadTo.equals("")))
	    	{
	    		if(ammountCadFrom.trim().length() == 0)
	    		{
	    			ammountCadFrom = "0";
	    		}
	    		
	    		if( ammountCadTo.trim().length() > 0 )
	    		{
	    		
		    		if(Double.parseDouble(ammountCadFrom)>Double.parseDouble(ammountCadTo))
		    		{		    			
		    			return;
		    		}	    		
	    		}
	    		
	    		if(!concatRiskCad.equals(""))
	    		{	    			
	    			if(ammountCadTo.trim().length() == 0)
	    			{		    			
	    				concatAmmountCad = " >= "+ammountCadFrom+" ";
	    				
		    		} else {
	    			
		    			concatAmmountCad = "  BETWEEN "+ammountCadFrom+" and "+ammountCadTo+" ";		    			
		    		}
	    			
	    		} else {
	    			
	    			concatAmmountCad = " BETWEEN "+ammountCadFrom+" and "+ammountCadTo+" ";				
	    		}
	    			    				    
	    	} else {
	    		
	    		concatAmmountCad = " >= 0";
	    	}	    	
    	}
	}
	
	protected void init_loan_type() 
	{				    	    	
    	if( previousType.equals("0")  )
    	{
    		
    	} else if( previousType.equals("1")  ){
    		
    		concatPrevLoanCad = "  'NVO'  ";
    		
    	} else if( previousType.equals("2")  ){
    		
    		concatPrevLoanCad = " 'ADD','REN','RDC' ";
    	}	
	}
}
