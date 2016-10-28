package mx.com.kubo.services.investor;

import java.util.ArrayList;
import java.util.Date;

import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.model.investor.MovimientosLog;
import mx.com.kubo.model.investor.MovimientosLogPK;
import safisrv.ws.InvKuboServicios.ConsultaInverRequest;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

public abstract class ServiceMovimientosAMO extends ServiceMovimientosDMO
{	
	protected void init_cuentas_inversionista()
	{									
		 if(cuenta_INFO != null && codigo_respuesta != null && codigo_respuesta[0].equals("0"))
         {
			 respuestas = resCliente.getInfocuenta()[0]; 
                
             cuentas = respuestas.split("\\&\\|\\&");
                
             listInvAccounts = new ArrayList<InvestorsAccounts>();
                
             investor_accounts = null;
                		
            for (int i = 0; i < cuentas.length; i++) 
            {	                
            	vars = cuentas[i].split("\\&\\;\\&", 3);
            	
            	if(vars.length == 3)
            	{
            		investor_accounts = new InvestorsAccounts();
            		
            		investor_accounts.setAccount    (vars[0]);
            		investor_accounts.setAccountName(vars[1]);
            		
            		investor_accounts.setSaldo(vars[2].equals("null") ? 0.00 : Double.parseDouble(vars[2]));
            		
            		listInvAccounts.add(investor_accounts);
            	}             			                	
			}
         }
	}

	protected void init_saldo_total()
	{
		try
		{
			locator = new SAFIServiciosServiceLocator();
			
			serviceSafi = locator.getSAFIServiciosSoap11();
					
			consulta_request = new ConsultaInverRequest(SAFI_client_id, listInvAccounts.get(0).getAccount());
			
			replyB = serviceSafi.consultaInver(consulta_request);
			
			if(replyB != null)
			{			
				saldoTotal = new Double(replyB.getSaldoTotal() == null ? "0.0" : replyB.getSaldoTotal());				
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}

	}
	
	protected void init_movimiento_log() 
	{
		movimiento_log    = new MovimientosLog();
		movimiento_log_PK = new MovimientosLogPK();
		
		log_id = dao_movimientos_log.getLog_id();
		
		movimiento_log_PK.setLog_id(log_id);
		movimiento_log_PK.setCompany_id(company_id);
		movimiento_log_PK.setProspectus_id(prospectus_id);
		movimiento_log_PK.setMotive_id(motive_id);
		movimiento_log_PK.setSafi_account_id(cuenta);
		
		movimiento_log.setPk         (movimiento_log_PK);		
		movimiento_log.setFolio      (folio);
		movimiento_log.setDescription(descripcion);
		movimiento_log.setFecha(new Date());
		movimiento_log.setStatus(DEFAULT_STATUS);
	}
}
