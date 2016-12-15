package mx.com.kubo.rest.saldos;

import java.rmi.RemoteException;

import mx.com.kubo.model.InfoCalifPorc;
import mx.com.kubo.rest.ResumenSaldos;

import safisrv.ws.InvKuboServicios.ConsultaInverRequest;
import safisrv.ws.InvKuboServicios.SAFIServicios;
import safisrv.ws.InvKuboServicios.SAFIServiciosServiceLocator;

public class ResumenSaldosIMP extends ResumenSaldosAMO
implements ResumenSaldosIMO
{
	public void init()
	{		
		try
		{	
			//init_sesion();
			
			//key = new gnNaturalPersonPK( Integer.parseInt( prospectus_id ) , Integer.parseInt( company_id ) );
			
			//persona = personaNaturalService.getNaturalPersonById(key);			
			
			numCuentas.clear();
			numCuentas.add("Todas las cuentas");
			
			consultaCte();	
			
            if(numCuentas.size() == 2)
            {
            	selectedAccount = numCuentas.get(1);
            }
			
			if ((selectedAccount == null) || (selectedAccount == "Todas las cuentas"))
			{
				selectedAccountAux = "0";
				
			}else{
				
				selectedAccountAux = selectedAccount;
			}						
			
			init_saldos_estado_cuenta();						
			
			SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
			SAFIServicios serviceSafi = locator.getSAFIServiciosSoap11();			
			
			request_investment = new ConsultaInverRequest(safi_client_id, selectedAccountAux);
			
			replyB = serviceSafi.consultaInver(request_investment);			
			
			
			lstinfo =  servicioProyecto.getInfoCalifPorc(safi_client_id, cuentaAhoID);						
			
		    init_grafica_1();												
		    init_grafica_2();		    		    
		    init_grafica_actividades();

			for(InfoCalifPorc info : lstinfo )
			{
				infoCalEst.add(info.getCalificacion());
				infoCalPorc.add(info.getPorcCalificacion()+"");		             
            }			
			
			init_response();
			init_active_resume();	
			
			getInvestmentsbyLink();
									
			payments_received_number = 0; 

/*			
			generaGraficaProyecccion( cuentaAhoID );
			
			generaDatosRendimientos( cuentaAhoID );
			
			initRetornoAnual( cuentaAhoID );
			
			service_access.add(15, 0, sesion, true);
*/			
			
		} catch(RemoteException ce) {
			
			System.out.println( ce.getMessage() );
			
			String url = ("connectionOut.jsf");
/*			
			
			faces = FacesContext.getCurrentInstance();
			
			external = faces.getExternalContext();
			
			try 
			{
				external.redirect(url);
				
			} catch (IOException ex) {
				
				ex.printStackTrace();
			}
*/			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
/*		
		initScreenNotificacion(  );
*/						
	}
	
	public void init_resumen_saldos() 
	{
		saldos = new ResumenSaldos();
		saldos.setSaldo_disponible(balances_cash);
		saldos.setIntereses_cobrados(interest_charged);
		saldos.setSaldo_total(total_balance);
		saldos.setDepositos_mes(depositos);
		
		saldos.setGlobalEfectivoDisponible(balances_cash); 
		saldos.setGlobalInversionesProceso(balances_investment_process);  
		saldos.setNotes_investment_process(notes_investment_process + "");
		saldos.setImpulsoInversionesActivas(balances_active_investments); 
		saldos.setNumber_notes_active(number_notes_active + "");
		saldos.setImpulsoInteresesCobrar(balances_accrued_interest);  
		saldos.setFijoInversionesActivas(montoInversiones);    
		saldos.setFijoInteresesDevengados(intARecibir);   
		saldos.setCuentasSaldoTotal(total_balance);       
		
	    saldos.setGlobalIntereses(saldoIntGlobal);
	    saldos.setImpulsoCapital(capital_amount);
	    saldos.setImpulsoInteresesRecibidos(ordinary_interests_amount);
	    saldos.setImpulsoInteresesMoratorios(interest_on_arrears_amount);
	    saldos.setFijoIntereses(saldoIntPlazoFijo);
	    saldos.setPagosRecibidos(payments_received_amount);
	    
	    saldos.setEnProcesoVigente(in_process_amount);
	    saldos.setEnProcesoAtrasado("$0.00");
	    saldos.setEnProcesoClientes(in_process_number + "");
	    saldos.setActivasVigente(saldoVigente0);
	    saldos.setActivasAtrasado(saldoAtrasado0);
	    saldos.setActivasClientes(number_NoDelinquent + "");
	    saldos.setSixteenDaysVigente(saldoVigente16a30);
	    saldos.setSixteenDaysAtrasado(saldoAtrasado16a30);
	    saldos.setSixteenDaysClientes(number_backward16_30 + "");
	    saldos.setThirtyoneDaysVigente(saldoVigente31a90);
	    saldos.setThirtyoneDaysAtrasado(saldoAtrasado31a90);
	    saldos.setThirtyoneDaysClientes(number_backward31_90 + "");
	    saldos.setNinetyoneDaysVigente(saldoVigente91a120);
	    saldos.setNinetyoneDaysAtrasado(saldoAtrasado91a120);
	    saldos.setNinetyoneDaysClientes(number_backward91_120 + "");
	    saldos.setMorethan120DaysVigente(saldoVigente120mas);
	    saldos.setMorethan120DaysAtrasado(saldoAtrasado120mas);
	    saldos.setMorethan120DaysClientes(number_backward121_180 + "");
	    saldos.setInversionesActivasVigente(saldoCapVigTotal);
	    saldos.setInversionesActivasAtrasado(saldoCapAtrTotal);
	    saldos.setInversionesActivasClientes(number_notes_active + "");
	    saldos.setInteresesPorCobrarVigente(saldoIntVigente);
	    saldos.setInteresesPorCobrarAtrasado(saldoIntAtrasado);
	    saldos.setImpulsoSaldoTotal("");
	    saldos.setPagadasTotalmente(fully_paid_amount);
	    saldos.setPagadasTotalmenteClientes(fully_paid_number + "");
	    saldos.setCreditosEnVenta("$0.00");
	    saldos.setCreditosEnVentaClientes("0");
	    saldos.setGarantiasAplicadasCapital(saldoCapitalCtaOrden);
	    saldos.setGarantiasAplicadasInteres(saldoInteresCtaOrden);
	    saldos.setGarantiasSaldoTotal(saldoTotalCtaOrden);
	    saldos.setGarantiasSaldoTotalClientes(number_under_warranty + "");	    	    
	}
}
