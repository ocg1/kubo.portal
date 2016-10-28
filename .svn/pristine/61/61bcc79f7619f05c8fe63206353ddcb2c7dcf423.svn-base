package mx.com.kubo.managedbeans.mesa.solicitud.estatus;

import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.model.SelectItem;

public abstract class CasosPospuestosAMO extends CasosPospuestosDMO
{					
	protected void init_fecha_POSPUESTO()
	{
		System.out.printf("\nCasosPospuestosAMO.init_fecha_POSPUESTO(): ");				
		
		calendar = Calendar.getInstance();	
		
		if(fecha_ORIGINAL_pospuesta == null)
		{
			calendar.add(Calendar.DAY_OF_YEAR, getDias_default_POSPUESTO());
			
		} else {
			
			calendar.setTime(fecha_ORIGINAL_pospuesta);
		}
		
		fecha_ORIGINAL_pospuesta = calendar.getTime();
		
		System.out.printf(formatter_date.format(fecha_ORIGINAL_pospuesta));
	}
	
	protected void init_fecha_PRE_AUTORIZACION()
	{
		System.out.printf("\nCasosPospuestosAMO.init_fecha_PRE_AUTORIZACION(): ");
		
		calendar = Calendar.getInstance();	
		
		if(fecha_ORIGINAL_pre_autorizacion == null)
		{
			calendar.add(Calendar.DAY_OF_YEAR, getDias_default_PRE_APROBACION());
			
		} else {
			
			calendar.setTime(fecha_ORIGINAL_pre_autorizacion);
		}
		
		fecha_ORIGINAL_pre_autorizacion = calendar.getTime();
		
		System.out.printf(formatter_date.format(fecha_ORIGINAL_pre_autorizacion) + "\n");
	}
		
	protected void init_list_days()
	{
		list_of_days   = new ArrayList<SelectItem>();
		
		for (int i = 1; i <= 31; i++) 
		{
			list_of_days.add(new SelectItem(new Integer(i)));
		}
	}
	
	protected void init_list_of_months()
	{
		list_of_months = new ArrayList<SelectItem>();		
		
		for (int i = 0; i < lista_months.length; i++) 
		{
			list_of_months.add(new SelectItem(i + 1, lista_months[i]));			
		}
	}
	
	protected void init_list_of_years()
	{				
		calendar = Calendar.getInstance();
		yyyy_actual = calendar.getTime();
		
		calendar.add(Calendar.YEAR, 1);
		yyyy_posterior = calendar.getTime();
		
		calendar.add(Calendar.YEAR, -2);
		yyyy_anterior = calendar.getTime();

		list_of_years  = new ArrayList<SelectItem>();
						
		list_of_years.add(new SelectItem(formatter_yyyy.format(yyyy_anterior)));
		list_of_years.add(new SelectItem(formatter_yyyy.format(yyyy_actual)));
		list_of_years.add(new SelectItem(formatter_yyyy.format(yyyy_posterior)));

	}
						
	protected final boolean asignar_fecha_posterior_to_ORIGINAL()
	{		
		calendar 	      = Calendar.getInstance();
		selected_calendar = Calendar.getInstance();
		
		selected_calendar.setTime(fecha_NEW);
		
		fecha_posterior_to_ORIGINAL = selected_calendar.after(calendar);
		
		return fecha_posterior_to_ORIGINAL;
	}
	
	protected final String asignar_fecha_SELECTED() 
	{
		sb = new StringBuilder();
		
		sb.append(selected_day).append("/");
		sb.append(selected_month).append("/");
		sb.append(selected_year);
		
		return sb.toString();
	}
	
	protected boolean validar_fecha()
	{
		boolean fecha_valida = false;
		
		if(selected_day != null && selected_month != null && selected_year != null)
		{
			Integer day   = Integer.valueOf(selected_day);
			Integer month = Integer.valueOf(selected_month);
			Integer year  = Integer.valueOf(selected_year);
			
			fecha_valida = true;
			
			if(month == 2 && day > 29)
			{
				fecha_valida = false;
			}
			
			if(month == 2 && day > 29 && year % 4 != 0)
			{
				fecha_valida = false;
			}
			
			if(day == 31)
			{
				if (month == 2
				||  month == 4
				||  month == 6
				||  month == 9
				||  month == 11) 
				{
					fecha_valida = false;
				}
			}							
		}
		
		return fecha_valida;
	}
	
	/*	
	private void init_selected_day()
	{				
		selected_day = formatter_dd.format(fecha_ORIGINAL);
		
		selected_day = selected_day.trim();
		
		if(selected_day.charAt(0) == '0')
		{
			selected_day = selected_day.substring(1);
		}
	}
		
	private void init_selected_month()
	{		
		selected_month = formatter_MM.format(fecha_ORIGINAL);
		
		selected_month = selected_month.trim();
		
		if(selected_month.charAt(0) == '0')
		{
			selected_month = selected_month.substring(1);
		}		
	}
	
	private void init_selected_year()
	{		
		selected_year = formatter_yyyy.format(fecha_ORIGINAL);
	}
*/	
}
