package mx.com.kubo.managedbeans.perfil;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SystemNotificationLog;
import mx.com.kubo.services.impl.FilesServiceImp;
import mx.com.kubo.services.impl.MailLogServiceImp;

public abstract class SystemNotificationDMO 
{
	@ManagedProperty("#{mailLogServiceImp}")
	protected MailLogServiceImp service_bitacoras;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesServiceImp service_file;
	
	protected FacesContext   faces;
	protected ELContext      context;
	protected ELResolver     resolver;
	
	protected SessionBean sesion;
	
	protected ProyectLoan proyect_loan;
	
	protected List<SystemNotificationLog> bitacora;
	
	protected List<Files> contratos;
	
	protected Integer prospectus_id;
	protected Integer company_id;

	public void setService_bitacoras(MailLogServiceImp service) 
	{
		service_bitacoras = service;
	}

	public void setService_file(FilesServiceImp service) 
	{
		service_file = service;
	}

	public final List<SystemNotificationLog> getBitacora() 
	{
		return bitacora;
	}
	
	public final List<Files> getContratos()
	{
		return contratos;
	}
}
