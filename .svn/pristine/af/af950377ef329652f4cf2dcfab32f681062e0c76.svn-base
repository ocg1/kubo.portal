package mx.com.kubo.managedbeans.investor.movimientos;

import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.investor.MotivosCancelacion;
import mx.com.kubo.model.investor.MovimientosLog;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.investor.ServiceMovimientosIMO;
import mx.com.kubo.services.investor.ServiceMovimientosIMP;
import mx.com.kubo.tools.Utilities;

public abstract class CancelacionCuentaDMO 
implements CancelacionCuentaIMO
{	
	protected NotificadorIMO        service_notificador;
	protected ServiceMovimientosIMO service_movimientos;
	
	protected RequestContext requestContext;
	
	protected HtmlSelectOneMenu select_one_menu;
	
	protected SessionBean sesion;
	protected Membership inversionista;
	
	protected MovimientosIMO cancelacion;
	protected MotivosCancelacion motivo;
	
	protected List<InvestorsAccounts>  lista_cuentas;
	protected List<MotivosCancelacion> lista_motivos;
	protected List<MovimientosLog>     lista_movimientos;
	
	protected StringBuilder sb;
		
	protected String motivo_id;	
	protected String cuenta;
	protected String folio;
	protected String descripcion;
	
	protected Integer prospectus_id;
	protected Integer company_id;			
	
	protected boolean notificacion_OK;	
	
	protected CancelacionCuentaDMO()
	{
		service_notificador = Utilities.findBean("notificadorImp");
		service_movimientos = new ServiceMovimientosIMP();						
	}

	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		
		service_movimientos.setSesion(sesion);
		
		lista_movimientos = service_movimientos.getLista_movimientos();
	}
	
	public final void setInversionista(Membership inversionista) 
	{
		this.inversionista = inversionista;
	}

	public final void setLista_cuentas(List<InvestorsAccounts> lista_cuentas) 
	{
		this.lista_cuentas = lista_cuentas;
	}
	
	public final void setLista_motivos(List<MotivosCancelacion> lista_motivos) 
	{
		this.lista_motivos = lista_motivos;
	}
	
	public final void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	
	public final List<InvestorsAccounts> getLista_cuentas() 
	{
		return lista_cuentas;
	}

	public final List<MotivosCancelacion> getLista_motivos() 
	{
		return lista_motivos;
	}
	
	public final List<MovimientosLog> getLista_movimientos() 
	{
		return lista_movimientos;
	}

	public final String getCuenta() 
	{
		return cuenta;
	}

	public final String getMotivo() 
	{
		if(motivo != null)
		{
			return motivo.getDescription();
			
		} else {
			
			return null;
		}
	}

	public final String getFolio() 
	{
		return folio;
	}

	public final String getDescripcion() 
	{
		return descripcion;
	}
}
