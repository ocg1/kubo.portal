package mx.com.kubo.registro.datos.birthdate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
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
		
		year_edad_MIN = Integer.parseInt(frm.format(date).split("/")[2]);
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
		
		year_edad_MAX = Integer.parseInt(frm.format(date).split("/")[2]);
	}
	
	protected void init_fecha_nacimiento() 
	{
		birthdate = person.getDate_of_birth();
		
		if (birthdate != null) 
		{		
			birthdate_TOKEN = frm.format(birthdate);
			
			mes_INDEX = Integer.parseInt(birthdate_TOKEN.split("/")[1]) - 1;
			
			dd = birthdate_TOKEN.split("/")[0];			
			dd = Integer.parseInt(birthdate_TOKEN.split("/")[0]) + "";
			
			day   = dd;
			month = catalogo_meses_nombres[mes_INDEX];
			year  = birthdate_TOKEN.split("/")[2];
					
			verificar_reglas_edad();			
		}
	}
	
	protected void asignar_fecha_nacimiento() 
	{
		sb = new StringBuilder();
		
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
		
		if (year != null && year.length() > 0 && !year.equals("0")) 
		{
			sb.append(year);
			fecha_OK = true;
			
		} else {
			year = "0";
			fecha_OK = false;
		}
		
		birthday_TOKEN = sb.toString();
		
    	try
    	{
    		birthdate = frm.parse(birthday_TOKEN);
    		
    	} catch (ParseException e) {
    		
    		rango_edad_OK = false;
    	}  
		
		System.out.println("FechaNacimientoAMO.init_birthday_TOKEN(): " + birthday_TOKEN);
	}
	
	protected void guardar_fecha_nacimiento() 
	{						
		verificar_reglas_edad();
			
		if(rango_edad_OK)
		{					
			value_ORIGINAL = "";
			
			if(person.getDate_of_birth() != null)
			{
				value_ORIGINAL =  frm.format(person.getDate_of_birth());
			}
			
			init_change_control("date_of_birth", value_ORIGINAL, birthday_TOKEN);
			
			person.setDate_of_birth(birthdate);						
			
			if(rango_age_OK)
			{
				person.setIs_age_valid('S');
				
			} else {
				
				person.setIs_age_valid('N');
			}						
			
			person = service_natural_person.update(person);
		}
		
		request = RequestContext.getCurrentInstance();
		request.addPartialUpdateTarget("pnlMsg");
	}
	
	protected void init_change_control(String field, String original_value, String new_value) 
	{			
		change_control_ENABLED = ! new_value.equalsIgnoreCase(original_value);
		
		if(change_control_ENABLED)
		{	
			change_control_PK = new Change_controlPK();
			change_control    = new Change_control();		
			
			change_control_PK.setProspectus_id(prospectus_id);
			change_control_PK.setCompany_id(company_id);
			
			change_control.setChange_controlPK(change_control_PK);		
			change_control.setChange_prospectus_id(change_prospectus_id);
			
			change_control.setAfected_table("gn_natural_person");	
			change_control.setField(field);
			
			change_control.setOriginal_value(original_value);
			change_control.setNew_value(new_value);
			
			change_control.setComments(comments);
			change_control.setIp(IP_address_client);
			change_control.setFocus_date(focus_date);

			change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
		}
	}
	
    private void verificar_reglas_edad()  
    {        
    	init_elapsed_time();    	  
		
        if(area.equals("I"))
        {
        	init_reglas_inversionista();
        	
        } else {
        	
        	init_reglas_acreditado();  
        }
    }
     
	private void init_elapsed_time()
    {
		fecha_final   = Calendar.getInstance();
		fecha_inicial = Calendar.getInstance();
        
		fecha_inicial.setTime(birthdate); 
        
        clone_fecha_inicial = (Calendar) fecha_inicial.clone(); // Otherwise changes are been reflected.
        
        elapsed_years = getElapsed_time(clone_fecha_inicial, fecha_final, Calendar.YEAR);
        
        clone_fecha_inicial.add(Calendar.YEAR,   elapsed_years);
        
        
        elapsed_meses = getElapsed_time(clone_fecha_inicial, fecha_final, Calendar.MONTH);
        
        clone_fecha_inicial.add(Calendar.MONTH,  elapsed_meses);
        
        
        elapsed_dias  = getElapsed_time(clone_fecha_inicial, fecha_final, Calendar.DATE);
        
        clone_fecha_inicial.add(Calendar.DATE,   elapsed_dias);
        
        
        elapsed_horas = (int) (fecha_final.getTimeInMillis() - clone_fecha_inicial.getTimeInMillis()) / 3600000;
        
        clone_fecha_inicial.add(Calendar.HOUR,   elapsed_horas); 
        
        
        elapsed_mins  = (int) (fecha_final.getTimeInMillis() - clone_fecha_inicial.getTimeInMillis()) / 60000;;
        
        clone_fecha_inicial.add(Calendar.MINUTE, elapsed_mins);
        
      
        elapsed_secs  = (int) (fecha_final.getTimeInMillis() - clone_fecha_inicial.getTimeInMillis()) / 1000;
                                                                                                                       
        sb = new StringBuilder();        
        sb.append(elapsed_years).append(" years, ");
        sb.append(elapsed_meses).append(" months, ");
        sb.append(elapsed_dias).append( " days, ");
        sb.append(elapsed_horas).append(" hours, ");
        sb.append(elapsed_mins).append( " minutes, ");
        sb.append(elapsed_secs).append( " seconds, ");
                
        System.out.println("FechaNacimientoAMO.init_elapsed_time(): " + sb.toString());       
	}
	
	private int getElapsed_time(Calendar fecha_inicial, Calendar fecha_final, int escala_de_tiempo) 
    {
		Calendar clone_fecha_inicial = (Calendar) fecha_inicial.clone(); // Otherwise changes are been reflected.
        
        elapsed_time = -1;
        
        while (!clone_fecha_inicial.after(fecha_final)) 
        {
        	clone_fecha_inicial.add(escala_de_tiempo, 1);
            
        	elapsed_time++;
        }
        
        return elapsed_time;
    }
	
	private void init_reglas_inversionista() 
    {            	
    	renderMsg      = false;
    	renderMsgTutor = false;
    	rango_edad_OK  = true; 
        
    	if (elapsed_years < EDAD_MIN_INVERSIONISTA)
    	{
    		rango_edad_OK = false;
    		renderMsg     = true; 
    		
    		msg = "No cumples la edad mínima";
    		msg = MSG_EDAD_MIN_INVERSIONISTA;
    	}
    		
    	if(elapsed_years >= EDAD_MAX_INVERSIONISTA)
    	{
    		rango_edad_OK = false;
    		renderMsg     = true; 
    		
    		msg = "Superas la edad máxima aceptada";
    		msg = MSG_EDAD_MAX_INVERSIONISTA;
    	}
    	
    	if(rango_edad_OK)
    	{
    		if(elapsed_years < 18)
    		{        			        		
    			msgTutor = MSG_EDAD_18_INVERSIONISTA;
        		renderMsgTutor = true;        			
    		}
    	} 
	}
	
    private void init_reglas_acreditado() 
    {
    	renderMsg      = false;
    	renderMsgTutor = false;
    	rango_age_OK   = false;
    	edad_min_OK    = false;
    	edad_max_OK    = false;
    	rango_edad_OK  = true;     	
        
/*    	
    	if(elapsed_years < EDAD_MIN_ACREDITADO)
    	{
    		rango_edad_OK = false;
    		renderMsg = true;
    		
    		msg = MSG_EDAD_MIN_ACREDITADO;    			    		
    	}
	    	
    	if(elapsed_years >= EDAD_MAX_ACREDITADO)
    	{
    		rango_edad_OK = false;
    		renderMsg = true;
    		
    		msg = MSG_EDAD_MAX_ACREDITADO;
    	}
*/    	
    	
    	if(EDAD_MIN_IS_AGE_VALID <= elapsed_years)
    	{
    		edad_min_OK = true;
    	}
    	
    	if(elapsed_years <= EDAD_MAX_IS_AGE_VALID)
    	{
    		 edad_max_OK = true;
    	}
    	 
    	rango_age_OK = edad_min_OK && edad_max_OK;
	}
}
