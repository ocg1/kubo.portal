package mx.com.kubo.services.investor;

import java.util.List;

import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;

public final class ServiceMovimientosIMP extends ServiceMovimientosAMO
implements ServiceMovimientosIMO
{
	private safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locator_SAFI;
	private safisrv.ws.CuentasServicios.SAFIServicios servCliente;
	
	public final void setupClientSAFIData(List<String> SAFIcuenta)
	{
		try
		{		
			cuenta_SAFI = SAFIcuenta.get(0).toString();
			
			consulta     = new ConsultaCuentasPorClienteRequest(cuenta_SAFI);			
			locator_SAFI = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
			
			servCliente = locator_SAFI.getSAFIServiciosSoap11();
									
			resCliente = servCliente.consultaCuentasPorCliente(consulta);
			
			cuenta_INFO      = resCliente.getInfocuenta();
			codigo_respuesta = resCliente.getCodigoRespuesta();	
			
			init_cuentas_inversionista();
			init_saldo_total();
			 	            					
		} catch (Exception e) {
		
			e.printStackTrace();			
		}
	}
	
	public final void registrar_cancelacion()
	{
		init_movimiento_log();
							
		movimiento_log_OK = dao_movimientos_log.registrar(movimiento_log);
	}
}
