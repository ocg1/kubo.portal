package mx.com.kubo.repositories.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.SystemParamPK;

public abstract class AccessIPCheckAMO extends AccessIPCheckDMO
{
	protected void init_change_control() 
	{
		change_control   = new Change_control();
		change_controlPK = new Change_controlPK();
		
		change_controlPK.setProspectus_id(prospectus_id);
		change_controlPK.setCompany_id(company_id);
		
		change_control.setChange_controlPK(change_controlPK);
		change_control.setAfected_table("pr_access");
		change_control.setField("ipaddress");
		
		if(change_prospectus_id != null)
		{
			change_control.setChange_prospectus_id(change_prospectus_id);
			
		} else {
			
			change_control.setChange_prospectus_id(prospectus_id);
		}
		
		change_control.setOriginal_value(ip_address_LAST);
		change_control.setNew_value(ip_address_NEW);		
		change_control.setComments(comments);
		change_control.setIp(ip_address_NEW);
		
		change_control_OK = dao_change_control.addChangeControl(change_control, prospectus_id, company_id);		
	}
	
	@SuppressWarnings("unchecked")
	protected void init_baja_usuario_firmado() 
	{		
		http_session = (HttpSession) external.getSession(false);
		
		servlet = http_session.getServletContext();
		
		usuarios_firmados = (Hashtable<String, Hashtable<String, HttpSession>>) servlet.getAttribute("usuariosFirmados");
		
		lista_http_session_keys = usuarios_firmados.keys();		
		
		prospectus_PK = new ProspectusPK(prospectus_id, company_id);
		
		prospectus = dao_prospectus.loadSelectedProspectus(prospectus_PK);
		
		tracking_id = prospectus.getTracking_id();
		
		while(lista_http_session_keys.hasMoreElements()) 
		{					
			http_session_key = lista_http_session_keys.nextElement();
			
			usuario_http_session = usuarios_firmados.get(http_session_key);
			
			http_session = usuario_http_session.get(tracking_id);
			
			if(http_session != null)
			{
				http_session.invalidate();
//				
//				usuario_http_session = usuarios_firmados.remove(http_session.getId());
				
				
				
//				servlet.setAttribute("usuariosFirmados", usuarios_firmados);
				
				remove_OK = true;
				
				break;
			}		
		}
	}
		
	protected void init_sesion_expirada() 
	{
		if(change_control_OK)
		{
			try 
			{
				external.redirect(XHTML_SESION_EXPIRADA);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	protected void init_system_param() 
	{
		system_param_PK = new SystemParamPK(TIEMPO_INACTIVAD_SESION, company_id);
		
		system_param = dao_system_param.loadSelectedSystemParam(system_param_PK);
		
		tiempo_inactividad_MAX = Integer.parseInt(system_param.getValue());
	}
	
	protected void init_elapsed_time()
    {
		fecha_final   = Calendar.getInstance();
		fecha_inicial = Calendar.getInstance();
        
		fecha_inicial.setTime(access_date_LAST); 
        
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
                
        System.out.println("AccessIPCheckAMO.init_elapsed_time(): " + sb.toString());       
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
}
