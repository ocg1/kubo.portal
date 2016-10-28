package mx.com.kubo.repositories.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.repositories.Change_controlDAO;
import mx.com.kubo.repositories.ProspectusDao;
import mx.com.kubo.repositories.SystemParamDao;

public abstract class AccessIPCheckDMO 
implements AccessIPCheckIMO
{
	protected Change_controlDAO dao_change_control;
	protected SystemParamDao    dao_system_param;
	protected ProspectusDao     dao_prospectus;
	
	protected FacesContext    faces;
	protected ExternalContext external;
	protected ServletContext  servlet;
	
	protected HttpSession http_session;
	
	protected Enumeration<String> lista_http_session_keys;
	protected Enumeration<String> lista_traking_id;
	
	protected Hashtable<String, HttpSession> usuario_http_session;
	protected Hashtable<String, Hashtable <String, HttpSession>> usuarios_firmados;
	
	protected Prospectus   prospectus;
	protected ProspectusPK prospectus_PK;
	
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	
	protected Access access_NEW;
	protected Access access_LAST;
	
	protected Change_control   change_control;
	protected Change_controlPK change_controlPK;
	
	protected Calendar clone_fecha_inicial;
	protected Calendar fecha_inicial;
	protected Calendar fecha_final;
	
	protected Date access_date_LAST;
	
	protected StringBuilder sb;
	
	protected String ip_address_LAST;
	protected String ip_address_NEW;
	protected String comments;
	protected String http_session_key;
	protected String tracking_id;	
	
	protected Integer change_prospectus_id;
	protected Integer prospectus_id;
	protected Integer company_id;
	protected Integer tiempo_inactividad_MAX;
	
	protected int elapsed_time;
	protected int elapsed_dias;
	protected int elapsed_meses;
	protected int elapsed_years;
	protected int elapsed_horas;
	protected int elapsed_mins;
	protected int elapsed_secs;
	
	protected boolean ip_address_CHANGED;
	protected boolean change_control_OK;	
	protected boolean tiempo_inactividad_ENABLED;
	protected boolean remove_OK;
	
	protected final String XHTML_SESION_EXPIRADA;
	
	protected final int TIEMPO_INACTIVAD_SESION;
	
	protected AccessIPCheckDMO()
	{
		comments = "Se expira la sesión por cambio de dirección IP";
		
		XHTML_SESION_EXPIRADA = "/Kubo/Portal/sesion-expirada.xhtml";		
		
		TIEMPO_INACTIVAD_SESION = 78;
	}
	
	public final void setAccess_LAST(Access access) 
	{
		access_LAST = access;
		
		if(access_LAST != null)
		{					
			ip_address_LAST  = access_LAST.getIpaddress();
			access_date_LAST = access_LAST.getAccess_datetime();	
			
			company_id = access_LAST.getCompany_id();
			
			init_system_param();		
			init_elapsed_time();
							
			if(elapsed_years < 1 && elapsed_meses < 1 && elapsed_dias < 1 && elapsed_horas < 1 && elapsed_mins < tiempo_inactividad_MAX)
			{
				tiempo_inactividad_ENABLED = true;
			}			
		}
	}

	abstract void init_system_param();
	abstract void init_elapsed_time();

	public final void setAccess_NEW(Access access) 
	{
		access_NEW = access;	
		
		if(access_NEW != null)
		{
			company_id           = access_NEW.getCompany_id();
			prospectus_id        = access_NEW.getProspectus_id();
			change_prospectus_id = access_NEW.getProspectus_id_viewed();	
			
			ip_address_NEW = access_NEW.getIpaddress();
			
			if(ip_address_NEW != null && ip_address_LAST != null)
			{
				ip_address_CHANGED = !ip_address_NEW.equals(ip_address_LAST);
			}
		}
	}
	
	public final void setDAO_change_control(Change_controlDAO dao) 
	{
		dao_change_control = dao;		
	}

	public final void setDAO_system_param(SystemParamDao dao) 
	{
		dao_system_param = dao;
	}

	public final void setDAO_prospectus(ProspectusDao dao) 
	{
		dao_prospectus = dao;
	}			
}
