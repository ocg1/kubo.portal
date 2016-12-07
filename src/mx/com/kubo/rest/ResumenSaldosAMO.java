package mx.com.kubo.rest;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.el.ELContext;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.gnNaturalPersonPK;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteRequest;
import safisrv.ws.CuentasServicios.ConsultaCuentasPorClienteResponse;

public abstract class ResumenSaldosAMO extends ResumenSaldosDMO
{
/*	
	public void consultaCte()
    {
		
		Calendar wsAT_TotCargacliente = Calendar.getInstance();
		wsAT_TotCargacliente.setTime(new Date());
		
		gnNaturalPersonPK key = null ; 
		
		FacesContext facesContext   = FacesContext.getCurrentInstance();	
		ExternalContext external  	= facesContext.getExternalContext();
		
		ELContext elContext 		= facesContext.getELContext();
		
		String prospectus_id        = (String) external.getRequestParameterMap().get("prospectus_id");
		String company_id        	= (String) external.getRequestParameterMap().get("company_id");
		
		setSesion((SessionBean) facesContext
                .getApplication().getELResolver()
                .getValue(elContext, null, "sessionBean"));
		
		
		
		if( prospectus_id != null && company_id != null ){
			
			key = new gnNaturalPersonPK( Integer.parseInt( prospectus_id ) , Integer.parseInt( company_id ) );
			
		}else{
		
			if(sesion != null && sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
			
			key = new gnNaturalPersonPK(sesion.getProspectus_id(), sesion.getCompany_id());
			
			}else{
				
				try 
				{
			        external.redirect("expired.jsf");
			        return;
				} catch (IOException ex) {
				       ex.printStackTrace();
				}
				
			}
		
		}
        persona = personaNaturalService.getNaturalPersonById(key);
        System.out.println("SAFI Client ID = "+persona.getSafi_client_id());
		//@SuppressWarnings("unused")
		//int valid=0;
		numCuentas.clear();
		numCuentas.add("Todas las cuentas");
		try{
			
			Calendar wsAT = Calendar.getInstance();
			wsAT.setTime(new Date());
			
			safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator locatorA = new safisrv.ws.CuentasServicios.SAFIServiciosServiceLocator();
			safisrv.ws.CuentasServicios.SAFIServicios serviceA =  locatorA.getSAFIServiciosSoap11();
			
			
			ConsultaCuentasPorClienteResponse replyA = serviceA.consultaCuentasPorCliente(new ConsultaCuentasPorClienteRequest(persona.getSafi_client_id()));
			
			Calendar wsAT_2 = Calendar.getInstance();
			wsAT_2.setTime(new Date());
			
			long difWSA = wsAT_2.getTimeInMillis() - wsAT.getTimeInMillis();
			
			System.out.println("Tiempo en cargar WSA: "+difWSA+" milisegundos");
			
			if(replyA.getInfocuenta()!=null && replyA.getCodigoRespuesta()!=null && replyA.getCodigoRespuesta()[0].equals("0"))
            {
                String respuestas = replyA.getInfocuenta()[0];
//                if(respuestas ==null || respuestas.isEmpty())
//                    valid=-1;
                   
                String[] cuentas = respuestas.split("\\&\\|\\&");
                
                for(int i =0; i<cuentas.length;i++)
                {
                    String[] vars = cuentas[i].split("\\&\\;\\&");
                    
                    if(vars.length==3){
                    	
                    	numCuentas.add(vars[0]);
                    	
                    }else{
                        System.out.println("Cuenta inválida: " + cuentas[i]);
                        //valid=1;
                    }                    
                }
                
                if(numCuentas.size()==2){
                	selectedAccount = numCuentas.get(1);
                }
            }
//            else
//            {
//                valid=-1;
//            }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Calendar wsAT_TotCargacliente_2 = Calendar.getInstance();
		wsAT_TotCargacliente_2.setTime(new Date());
		
		long difWSATOTCARGACLIENTE = wsAT_TotCargacliente_2.getTimeInMillis() - wsAT_TotCargacliente.getTimeInMillis();
		
		System.out.println("Tiempo Total consultaCte: "+difWSATOTCARGACLIENTE+" milisegundos");
		
    }
*/    
}
