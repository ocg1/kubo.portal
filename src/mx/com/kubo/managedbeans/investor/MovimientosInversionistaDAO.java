package mx.com.kubo.managedbeans.investor;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.movimientos.CancelacionCuentaIMO;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.investor.MotivosCancelacion;
import mx.com.kubo.model.investor.MovimientosLog;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.investor.ServiceMovimientosIMO;

public class MovimientosInversionistaDAO 
{
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{accessServiceImp}")	
	protected AccessService service_access; 
	
	protected ServiceMovimientosIMO service_movimientos;
	
	protected RequestContext requestContext;
	protected FacesContext faces;
	protected ELContext context;
	protected ELResolver resolver;
	
	protected SessionBean sesion;
	
	protected Membership membership;
	protected MembershipPK membership_PK;
	
	protected Access access;
	
	protected CancelacionCuentaIMO cancelacion;
	
	protected List<InvestorsAccounts>  lista_cuentas;
	protected List<MotivosCancelacion> lista_motivos_cancelacion;
	protected List<MovimientosLog>     lista_movimientos_cancelacion;
	
	protected String movimientoActual;
	protected String menuSelActual;
	protected String movimiento;
	protected String SAFI_client_id;
	
	protected List<String> SAFI_cuenta;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	
	protected boolean lista_movimientos_OK;
	
	protected final String MENU_DEPOSITO    = "1"; 
	protected final String MENU_DISPOSICION = "2";
	protected final String MENU_DETALLEMOVS	= "3";
	protected final String MENU_CANCELACION = "4";
	
	
	protected final String HTML_DEPOSITO    = "depositoEfectivo.xhtml" ;
	protected final String HTML_DISPOSICION = "disposicionEfectivo.xhtml" ;
	protected final String HTML_CANCELACION = "cancelacion-cuenta.xhtml";
	protected final String HTML_DETALLEMOVS	= "detalleMovimientos.xhtml";
	
	protected final int DEPOSITO_EFECTIVO    = 43;
	protected final int DISPOSICION_EFECTIVO = 44;
	protected final int CANCELACION_CUENTA   = 45;
	protected final int DETALLE_MOVIMIENTOS	 = 46;
	
	protected final int PRODUCT_TYPE = 1;	
	
	public final void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}	
	
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}

	public String getMovimientoActual() 
	{
		return movimientoActual;
	}
	
	public void setMovimientoActual(String movimientoActual) 
	{
		this.movimientoActual = movimientoActual;
	}
	
	public String getMenuSelActual() 
	{
		return menuSelActual;
	}
	
	public final CancelacionCuentaIMO getCancelacion() 
	{
		return cancelacion;
	}

	public void setMenuSelActual(String menuSelActual) 
	{
		this.menuSelActual = menuSelActual;
	}
}
