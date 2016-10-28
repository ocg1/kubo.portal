package mx.com.kubo.mesa.autenticacion.preguntas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import mx.com.kubo.model.SystemParamPK;

public abstract class FechaNacimientoAMO extends FechaNacimientoDMO 
{
	protected void init_system_param() 
	{		
		system_param_PK = new SystemParamPK();		
		system_param_PK.setCompany_id(company_id);
		
		system_param_PK.setSystem_param_id(PARAM_EDAD_MIN_ACREDITADO);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		EDAD_MIN_ACREDITADO = Integer.parseInt(system_param.getValue());
		
				
		system_param_PK.setSystem_param_id(PARAM_EDAD_MIN_INVERSIONISTA);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		EDAD_MIN_INVERSIONISTA = Integer.parseInt(system_param.getValue());
		
		
		system_param_PK.setSystem_param_id(PARAM_EDAD_MIN_IS_AGE_VALID);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		EDAD_MIN_IS_AGE_VALID = Integer.parseInt(system_param.getValue());
		
		
		system_param_PK.setSystem_param_id(PARAM_EDAD_MAX_ACREDITADO);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		EDAD_MAX_ACREDITADO = Integer.parseInt(system_param.getValue());
		
		
		system_param_PK.setSystem_param_id(PARAM_EDAD_MAX_INVERSIONISTA);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		EDAD_MAX_INVERSIONISTA = Integer.parseInt(system_param.getValue());
		
		
		system_param_PK.setSystem_param_id(PARAM_EDAD_MAX_IS_AGE_VALID);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		EDAD_MAX_IS_AGE_VALID = Integer.parseInt(system_param.getValue());
	}
	
	protected void init_lista_dias_meses()
	{
		lista_dias  = new ArrayList<String>();
		lista_meses = new ArrayList<String>();
		lista_years = new ArrayList<String>();
		
		day   = "0";
		month = "0";
		year  = "0";
		
		for (int i = 1; i <= 31; i++) 
		{
			lista_dias.add(i + "");
		}
		
		for (int i = 0; i < 12; i++) 
		{
			lista_meses.add(catalogo_meses_nombres[i]);
		}
	}
	
	protected void init_lista_years() 
	{		
		init_edad_MAX();
		init_edad_MIN();
					
		for (int i = year_edad_MIN; i >= year_edad_MAX; i--) 
		{
			lista_years.add(i + "");
		}
	}
	
	private void init_edad_MIN() 
	{
		fecha = Calendar.getInstance();
		
		if(area.equals("I"))
		{		
			fecha.add(Calendar.YEAR,-(EDAD_MIN_INVERSIONISTA));
			
		} else {
			
			fecha.add(Calendar.YEAR,-(EDAD_MIN_ACREDITADO));			
		}
		
		date = fecha.getTime();
		
		year_edad_MIN = Integer.parseInt(date_format.format(date).split("/")[2]);
	}
	
	private void init_edad_MAX() 
	{
		fecha = Calendar.getInstance();
		
		if(area.equals("I"))
		{
			fecha.add(Calendar.YEAR,-(EDAD_MAX_INVERSIONISTA));
			
		} else {
			
			fecha.add(Calendar.YEAR,-(EDAD_MAX_ACREDITADO));
		}
		
		date = fecha.getTime();
		
		year_edad_MAX = Integer.parseInt(date_format.format(date).split("/")[2]);
	}
		
	protected void init_birthdate_TOKEN() 
	{
		sb = new StringBuilder();
		
		init_dia_SELECTED();
		init_mes_SELECTED();
		init_year_SELECTED();
				
		birthdate_TOKEN = sb.toString();
		
		System.out.println("FechaNacimientoAMO.init_birthday_TOKEN(): " + birthdate_TOKEN);
		
		init_birthdate();	
		init_fecha_ENABLED();
	}
	
    private void init_birthdate() 
    {
    	if(fecha_OK)
    	{
	    	try
	    	{
	    		birthdate_NEW = date_format.parse(birthdate_TOKEN);
	    		
	    	} catch (ParseException e) {
	    		
	    		fecha_OK = false;
	    	}   
    	}
	}

	private void init_year_SELECTED() 
    {
		if (year != null && year.length() > 0 && !year.equals("0")) 
		{
			sb.append(year);
			
			fecha_OK = true;
			
		} else {
			
			year = "0";
			
			fecha_OK = false;
		}
	}

	private void init_mes_SELECTED() 
    {
		if (month == null || month.equals("0")) 
		{
			month = "0";
			
			fecha_OK = false;
		}
		
		if (month.equals("Febrero") && Integer.parseInt(day) > 29) 
		{
			day = "0";
			
			fecha_OK = false;
		}

		if (month.equals("Febrero") && Integer.parseInt(day) == 29 && (Integer.parseInt(year) % 4 != 0)) 
		{
			day = "0";
			
			fecha_OK = false;
		}
		
		if (month != null && month.length() > 0 && !month.equals("0")) 
		{
			for (int i = 0; i < 12; i++) 
			{
				if ((catalogo_meses_nombres[i]).equals(month)) 
				{
					if ((i + 1) < 10)
					{
						mes_TOKEN = "0" + (i + 1);
						
					} else {
						
						mes_TOKEN = (i + 1) + "";
					}
				}
			}
			
			sb.append(mes_TOKEN).append("/");
			
			fecha_OK = true;			
		}
	}

	private void init_dia_SELECTED() 
    {
		if (day != null && day.length() > 0 && !day.equals("0")) 
		{
			sb.append(day).append("/");
			
			fecha_OK = true;
			
		} else {
			
			day = "0";
			
			fecha_OK = false;
		}

		if (day.equals("31")) 
		{
			if (month.equals("Febrero") 
			 || month.equals("Abril")
			 || month.equals("Junio")
			 || month.equals("Septiembre")
			 || month.equals("Noviembre")) 
			{
				day = "0";
				
				fecha_OK = false;
			}
		}
	}

	protected void init_fecha_ENABLED()
    {
    	fecha_ENABLED = false;
    	
    	if(fecha_OK)
    	{
    		fecha_ENABLED = birthdate_NEW.equals(birthdate_ORIGINAL);    	    		
    	}
    	
    	request.addCallbackParam("fecha_ENABLED", fecha_ENABLED);
    }
}
