package mx.com.kubo.services.investor;

import java.util.List;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.investor.MotivosCancelacion;
import mx.com.kubo.model.investor.MovimientosLog;
import mx.com.kubo.model.investor.MovimientosLogPK;
import mx.com.kubo.repositories.investor.DAOMovimientosIMO;
import mx.com.kubo.repositories.investor.DAOMovimientosLogIMO;
import mx.com.kubo.tools.Utilities;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;
import safisrv.ws.InvKuboServicios.ConsultaInverRequest;
import safisrv.ws.InvKuboServicios.ConsultaInverResponse;
import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

public abstract class ServiceMovimientosDMO 
implements ServiceMovimientosIMO
{
	protected DAOMovimientosIMO    dao_movimientos;
	protected DAOMovimientosLogIMO dao_movimientos_log;
	
	protected SessionBean sesion;
	
	protected SAFIServiciosServiceLocator locator;
	protected SAFIServicios serviceSafi;
	
	protected ConsultaCuentasPorClienteResponse resCliente;
	protected ConsultaCuentasPorClienteRequest consulta;
	protected ConsultaInverRequest consulta_request;
	protected ConsultaInverResponse replyB;
	
	protected InvestorsAccounts investor_accounts;
	
	protected MovimientosIMO   movimiento;
	protected MovimientosLog   movimiento_log;
	protected MovimientosLogPK movimiento_log_PK;
	
	protected List<InvestorsAccounts>  listInvAccounts;
	protected List<MotivosCancelacion> lista_motivos_cancelacion;
	protected List<MovimientosLog>     lista_movimientos;
	
	protected String cuenta_SAFI;
	protected String respuestas;
	protected String SAFI_client_id;		
	protected String cuenta;
	protected String folio;	
	protected String descripcion;
	
	protected String[] vars;
	protected String[] cuentas;
	protected String[] cuenta_INFO;
	protected String[] codigo_respuesta;
	
	protected Double saldoTotal;
	
	protected Integer log_id;
	protected Integer motive_id;
	protected Integer prospectus_id;
	protected Integer company_id;		
	
	protected boolean movimiento_log_OK;
	
	protected final String DEFAULT_STATUS = "0";
	
	protected ServiceMovimientosDMO()
	{
		dao_movimientos     = Utilities.findBean("dao_movimientos");
		dao_movimientos_log = Utilities.findBean("dao_movimientos_log");
	}
	
	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
	}

	public final void setSAFI_client_id(String client_id) 
	{
		SAFI_client_id = client_id;
	}
	
	public final void setMovimiento(MovimientosIMO movimiento)
	{
		try
		{
			this.movimiento = movimiento;
			
			cuenta      = movimiento.getCuenta();
			folio       = movimiento.getFolio();
			descripcion = movimiento.getMotivo() + " - " + movimiento.getDescripcion();
		
			motive_id = Integer.parseInt(movimiento.getMotivo_id());		
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}

	public final List<InvestorsAccounts> getLista_cuentas() 
	{
		return listInvAccounts;
	}

	public final List<MotivosCancelacion> getLista_motivos_cancelacion(int product_type_id) 
	{
		lista_motivos_cancelacion = dao_movimientos.getLista_motivos_cancelacion(product_type_id);
		
		return lista_motivos_cancelacion;
	}
	
	public final List<MovimientosLog> getLista_movimientos()
	{
		lista_movimientos = dao_movimientos_log.getLista_movimientos(prospectus_id, company_id);
		
		return lista_movimientos;
	}
}
