package mx.com.kubo.managedbeans.normatividad;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.TableroNormativoResumen;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.TableroNormativoService;

public class ResumenTableroNormativoDMO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{tableroNormativoServiceImp}")
	protected TableroNormativoService tableronormativoservice;
	
	protected TableroNormativoResumen tableronormativoresumen;
	
	protected SessionBean sesion;
	
	protected Locale locale = new Locale("es","mx");
	
	protected NumberFormat num = NumberFormat.getNumberInstance(locale);

	

	public TableroNormativoService getTableronormativoservice() {
		return tableronormativoservice;
	}

	public void setTableronormativoservice(TableroNormativoService tableronormativoservice) {
		this.tableronormativoservice = tableronormativoservice;
	}

	public TableroNormativoResumen getTableronormativoresumen() {
		return tableronormativoresumen;
	}

	public void setTableronormativoresumen(TableroNormativoResumen tableronormativoresumen) {
		this.tableronormativoresumen = tableronormativoresumen;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	
	
	
}
