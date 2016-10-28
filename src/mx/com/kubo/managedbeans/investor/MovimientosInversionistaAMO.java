package mx.com.kubo.managedbeans.investor;

import java.util.ArrayList;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.movimientos.CancelacionCuentaIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.services.investor.ServiceMovimientosIMP;

public abstract class MovimientosInversionistaAMO extends MovimientosInversionistaDAO	
{
	public final void init_catalogos()
	{	
		SAFI_cuenta = new ArrayList<String>();
		SAFI_cuenta.add(SAFI_client_id);
			
		service_movimientos = new ServiceMovimientosIMP();
		
		service_movimientos.setSesion(sesion);
		service_movimientos.setSAFI_client_id(SAFI_client_id);
		service_movimientos.setupClientSAFIData(SAFI_cuenta);
		
		lista_cuentas                 = service_movimientos.getLista_cuentas();		
		lista_motivos_cancelacion     = service_movimientos.getLista_motivos_cancelacion(PRODUCT_TYPE);
		lista_movimientos_cancelacion = service_movimientos.getLista_movimientos();
	}
	
	protected void procesar_movimiento() 
	{
		if(movimiento.equals("deposito"))
		{		
			movimientoActual = HTML_DEPOSITO;			
			menuSelActual    = MENU_DEPOSITO;		
						
			registrar_bitacora_acceso(DEPOSITO_EFECTIVO);
			
		}else if(movimiento.equals("retiro")) {	
			
			movimientoActual = HTML_DISPOSICION;			
			menuSelActual    = MENU_DISPOSICION;		
			
			registrar_bitacora_acceso(DISPOSICION_EFECTIVO);
			
		}else if(movimiento.equals("detalle")){
			
			System.out.println("entro a la opcion de detalle de movimiento");
			
			movimientoActual = HTML_DETALLEMOVS;			
			menuSelActual    = MENU_DETALLEMOVS;
			
			registrar_bitacora_acceso(DETALLE_MOVIMIENTOS);
			
		}else if(movimiento.equals("cancelacion")) {
			
			movimientoActual = HTML_CANCELACION;			
			menuSelActual    = MENU_CANCELACION;
			
			cancelacion = new CancelacionCuentaIMP();
			cancelacion.setSesion(sesion);
			cancelacion.setInversionista(membership);
			cancelacion.setLista_cuentas(lista_cuentas);
			cancelacion.setLista_motivos(lista_motivos_cancelacion);
			
			lista_movimientos_cancelacion = cancelacion.getLista_movimientos();
			
			if(lista_movimientos_cancelacion != null && lista_movimientos_cancelacion.size() > 0)
			{
				lista_movimientos_OK = true;
				
			} else {
				
				lista_movimientos_OK = false;
			}
			
			requestContext.addCallbackParam("lista_movimientos_OK", lista_movimientos_OK);

			registrar_bitacora_acceso(CANCELACION_CUENTA);
		}		
	}	
	
	private void registrar_bitacora_acceso(int screen_id) 
	{		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		
		access = new Access();
		
		access.setCompany_id(company_id);
		access.setProspectus_id(prospectus_id);
		access.setProspectus_id_viewed(prospectus_id);
		access.setScreen_id(screen_id);
		access.setPercentage(0);
		access.setWeb_browser        (sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system          (sesion.getOsbrawser());
		access.setHorizontal_size    (sesion.getBrowser_width());
		access.setVertical_size      (sesion.getBrowser_height());
		access.setIpaddress          (sesion.getIP_address_client());
		access.setVersion_description (sesion.getVersion_description());
		access.setUrl_access		  (sesion.getUrl_access());
		
		service_access.add(access, true);
	}
}
